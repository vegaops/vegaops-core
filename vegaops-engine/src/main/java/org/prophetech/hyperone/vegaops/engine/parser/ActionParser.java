package org.prophetech.hyperone.vegaops.engine.parser;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.hswebframework.web.bean.FastBeanCopier;
import org.prophetech.hyperone.vegaops.engine.utils.*;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.exception.TimeoutException;
import org.prophetech.hyperone.vegaops.engine.model.*;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j(topic = "vegaops")
public class ActionParser {
    private static final FastBeanCopier.DefaultConverter converter = new FastBeanCopier.DefaultConverter();
    private static final Map<String, Method> clientMethodCache = new ConcurrentHashMap<>();

    public static ActionResult parse(CloudAction action) {
        SecretMaskJsonSerializer.addSecretMaskKeys(action.getMaskKeys());
        ActionResult actionResult = new ActionResult();
        Map before = (Map) action.getVariables().remove("before");
        if (before != null) {
            parseBefore(before, action);
        }
        if (StringUtils.isBlank(action.getClient())) {
            action.setOutput(MapVariablesParser.parser(action.getOutput(), action.getVariables()));
            actionResult.setOutput(action.getOutput());
            return actionResult;
        }
        Assert.notNull(action.getAction(), "action must not be null");
        Assert.notNull(action.getMethod(), "method must not be null");
        // 执行方法调用，并将返回结果写入到output中
        actionResult = invoke(action);
        LinkedHashMap<String, Object> output = action.getOutput();
        if (actionResult != null && actionResult.isInvokeSuccess()) {
            for (String key : output.keySet().toArray(new String[0])) {
                if (key.startsWith("[") && key.endsWith("]")) {
                    output.remove(key);
                }
            }
            output.remove("loop");
            output.remove("after");
            output.remove("trigger");
            output.remove("handler");
            output.put("success", true);
        }
        if (action.isOutputLog()) {
            log.info("client result:{}", SecretMaskJsonSerializer.toJSONString(output));
        } else {
            log.info("client invoke:{},***其他内容不显示***", actionResult.isInvokeSuccess());
        }
        actionResult.setOutput(output);
        return actionResult;


    }

    public static ActionResult invoke(CloudAction action) {
        int times = 0;
        InvokeResult result;
        ActionResult actionResult;
        do {
            times++;
            result = parseArgValues(action);
            actionResult = parseIfSuccess(action, result);
            if (actionResult.isInvokeSuccess()) {
                Map resultMap = new LinkedHashMap(action.getVariables());
                resultMap.put("result", result.getResult());
                Map after = (Map) action.getOutput().remove("after");
                Map trigger = (Map) action.getOutput().remove("trigger");
                String handler = (String) action.getOutput().remove("handler");
                parseResult(action, resultMap);
                action.getOutput().put("retry", false);
                if (after != null) {
                    parseAfter(after, action);
                }
                if (trigger != null) {
                    parseTrigger(trigger, action);
                }
                if (handler != null) {
                    Map vars = new HashMap();
                    vars.put("output", action.getOutput());
                    vars.put("vendor", action.getCloudTemplate().getVendor());
                    vars.put("nodeType", action.getCloudTemplate().getNodeType());
                    vars.put("resourceType", action.getCloudTemplate().getNodeType());
                    vars.put("action", action.getAction());
                    try {
                        log.info("执行handler:{}", handler);
                        ELUtils.getSpelValue(handler, vars);
                    } catch (Throwable e) {
                        log.error("执行handler出错", e);
                    }
                }
                break;
            }
            actionResult.setThrowable(result.getThrowable());
            if (!actionResult.isNeedRetry()) {
                break;
            }
            log.warn("第{}次{}{}执行失败", times, action.getCloudTemplate().getComponentId(), action.getAction());
            if (times > action.getRetryTimes()) {
                throw new TimeoutException(action.getCloudTemplate().getComponentId() + "," + action.getAction() + "重试" + times + "次后执行失败！", result.getThrowable());
            } else {
                try {
                    Thread.sleep(action.getWaitInterval());
                } catch (InterruptedException ee) {
                    log.error("重试{}{}等待出错", action.getAction(), action.getCloudTemplate().getComponentId());
                }
            }
        } while (true);
        return actionResult;
    }

    /**
     * 解析output结果并返回是否不需要重试
     *
     * @param action
     * @param result
     * @return true 结果正常不重试 false 结果需要重试
     */
    private static ActionResult parseIfSuccess(CloudAction action, InvokeResult result) {
        ActionResult actionResult = new ActionResult();
        if (action.getOutput() != null) {
            String successExp = action.getOutput().get("success") + "";
            Assert.notNull(successExp, "output must have success expression");
            Map resultMap = new LinkedHashMap(action.getVariables());
            resultMap.put("result", result.getResult());
            resultMap.put("throwable", result.getThrowable());
            boolean success = "true".equalsIgnoreCase(PlaceHolderUtils.getPlaceHolder(successExp, resultMap) + "");
            actionResult.setInvokeSuccess(success);
            if (!success) {
                //取retry表达式
                String retryExp = (String) action.getOutput().get("retry");
                if (retryExp != null) {
                    String retry = PlaceHolderUtils.getPlaceHolder(retryExp, resultMap) + "";
                    log.info("retry表达式为:{},是否重试:{}", retryExp, retry);
                    actionResult.setNeedRetry("true".equalsIgnoreCase(retry));
                }
            }
        } else {
            LinkedHashMap<String, Object> map = new LinkedHashMap();
            action.setOutput(map);
            actionResult.setInvokeSuccess(true);
        }
        return actionResult;
    }

    private static void parseResult(CloudAction action, Map result) {
        Map<String, Object> output = action.getOutput();
        Map<String, Object> temp = new LinkedHashMap<>(output);
        Map loop = (Map) temp.get("loop");
        Map<String, Object> vars = new LinkedHashMap<>(action.getVariables());
        vars.putAll(result);
        temp.forEach((k, v) -> {
            if ("|loop|success|after|handler".contains("|" + k + "|")) {
                return;
            }
            Object value = v;
            if (k.startsWith("[") && k.endsWith("]")) {
                String[] split = k.split(":");
                String name = split[0].substring(1, split[0].length() - 1);
                String itemExp = split[1].substring(1, split[1].length() - 1);
                Object listValue = ELUtils.getSpelValue(itemExp, result);
                List distList = getOutputResultList(loop, name, output);
                //resultType申明最终的container输出的结果
                if (!output.containsKey("resultType")) {
                    output.put("resultType", "list:" + name);
                }
                value = distList;
                output.put(name, distList);
                vars.put(name, value);
                if (listValue.getClass().isArray()) {
                    Object[] objects = (Object[]) listValue;
                    listValue = Arrays.asList(objects);
                }
                if (listValue instanceof List) {
                    List list = (List) listValue;
                    list.forEach(i -> {
                        Map<String, Object> distMap = new LinkedHashMap((Map<String, Object>) v);
                        for (String key : distMap.keySet().toArray(new String[0])) {
                            distMap.put(key, PlaceHolderUtils.getPlaceHolder(distMap.get(key), i, vars));
                        }
                        distList.add(distMap);
                    });
                }
                log.info("{},listSize:{}", name, distList.size());
            } else if (v instanceof Map) {
                Map node = MapVariablesParser.parser((Map) v, vars);
                value = node;
                output.put(k, node);
            } else if (v instanceof String) {
                String sv = (String) v;
                value = sv;
                if (ELUtils.isSpelExpression(sv)) {
                    value = ELUtils.getSpelValue(sv, vars);
                    output.put(k, value);
                }
            }
            vars.put(k, value);
        });
        if (loop != null) {
            Map loopNode = MapVariablesParser.parser(loop, (Map<String, Object>) result);
            if (Objects.equals("true", loopNode.get("condition") + "")) {
                invoke(action);
            }
        }
    }

    private static List getOutputResultList(Map loopNode, String listName, Map output) {
        if (loopNode != null) {
            List<String> appendList = (List<String>) loopNode.get("appendList");
            if (appendList.contains(listName)) {
                List resultList = (List) output.get(listName);
                if (resultList != null) {
                    return resultList;
                }
            }
        }
        return new ArrayList();
    }

    private static void parseBefore(Map flow, CloudAction action) {
        CloudActionFlow actionFlow = createFlow(flow, action);
        FlowResult flowResult = ActionFlowParser.parse(actionFlow);
        if (flowResult.isSuccess()) {
            action.getVariables().putAll(flowResult.getOutput());
        } else if (flowResult.getThrowable() instanceof TimeoutException) {
            throw (TimeoutException) flowResult.getThrowable();
        } else {
            log.warn("{}.{}.before执行失败，任务结束", action.getCloudTemplate().getComponentId(), action.getAction());
            throw new RuntimeException(action.getCloudTemplate().getComponentId() + "." + action.getAction() + ".before执行失败，任务结束");
        }
    }

    private static void parseAfter(Map flow, CloudAction action) {
        CloudActionFlow actionFlow = createFlow(flow, action);
        FlowResult flowResult = ActionFlowParser.parse(actionFlow);
        if (flowResult.isSuccess()) {
            action.getOutput().putAll(flowResult.getOutput());
        } else if (flowResult.getThrowable() instanceof TimeoutException) {
            throw (TimeoutException) flowResult.getThrowable();
        }
        action.getOutput().put("success", flowResult.isSuccess());
    }

    private static void parseTrigger(Map trigger, CloudAction cloudAction) {
        Assert.hasLength((String) trigger.get("nodeType"), "nodeType不能为空");
        Assert.hasLength((String) trigger.get("action"), "action不能为空");
        RuntimeContext context = RuntimeContext.getContext();
        String[] nodeTypes = ((String) trigger.get("nodeType")).split(",");
        String action = (String) trigger.get("action");
        Map input = (Map) trigger.remove("input");
        if (input == null) {
            input = new HashMap();
        }
        Map vars = MapVariablesParser.parser(input, cloudAction.getOutput());
        String triggerComponentId = cloudAction.getCloudTemplate().getComponentId() + "-trigger";
        log.info("add trigger:{}", triggerComponentId);
        for (String nodeType : nodeTypes) {
            if (context == null) {
                CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate(cloudAction.getCloudTemplate().getVendor(),cloudAction.getCloudTemplate().getVersion(), nodeType);
                cloudTemplate.getVariables().putAll(cloudAction.getVariables());
                cloudTemplate.getVariables().putAll(vars);
                CloudAction install = cloudTemplate.getCloudAction(action);
                ActionParser.parse(install);
            } else {
                context.getContainer().addNodeAtRuntime(triggerComponentId, action, nodeType, vars);
            }
        }
    }



    private static CloudActionFlow createFlow(Map flow, CloudAction action) {
        CloudActionFlow cloudActionFlow = new CloudActionFlow();
        cloudActionFlow.readFormMap(flow);
        cloudActionFlow.getVariables().putAll(action.getVariables());
        cloudActionFlow.getVariables().putAll(action.getOutput());
        cloudActionFlow.setCloudAction(action);
        log.info("创建flow:{},变量:{}", cloudActionFlow.getAction(), SecretMaskJsonSerializer.toJSONString(cloudActionFlow.getVariables(), action.getMaskKeys()));
        return cloudActionFlow;
    }

    private static InvokeResult parseArgValues(CloudAction action) {
        InvokeResult invokeResult = new InvokeResult();
        Class<?> client;
        Object clientInstance;
        if (ELUtils.isSpelExpression(action.getClient())) {
            clientInstance = ELUtils.getSpelValue(action.getClient(), action.getVariables());
            client = clientInstance.getClass();
        } else {
            try {
                client = ClassUtils.getClass(action.getClient());
            } catch (Throwable e) {
                log.error("获取client的类失败：{}", action.getClient());
                throw new RuntimeException("获取client的类失败：" + action.getClient());
            }
            try {
                clientInstance = client.newInstance();
            } catch (Throwable e) {
                log.error("实例化client的类失败：{}", action.getClient());
                throw new RuntimeException("实例化client的类失败：" + action.getClient());
            }
        }
        if (action.getClassProperties() != null) {
            FastBeanCopier.copy(action.getClassProperties(), clientInstance);
        }
        InvokeParameter invokeParameter;
        try {
            invokeParameter = parseInvokeParameter(action, client);
        } catch (Throwable e) {
            log.error("解析{}.{}参数失败", client.getName(), action.getMethod(), e);
            throw new RuntimeException("解析" + client.getName() + "." + action.getMethod() + "参数失败");
        }
        try {
            log.info("准备执行{}.{},参数:{}", client.getName(), action.getMethod(), SecretMaskJsonSerializer.toJSONString(invokeParameter.getObjects()));
            Object invoke = invokeParameter.getMethod().invoke(clientInstance, invokeParameter.getObjects());
            invokeResult.setResult(invoke);
            if (action.isOutputLog()) {
                log.info("执行{}.{}成功,返回:{}", client.getName(), action.getMethod(), SecretMaskJsonSerializer.toJSONString(invoke));
            } else {
                log.info("执行{}.{}成功,***其他内容不显示***", client.getName(), action.getMethod());
            }
        } catch (Throwable e) {
            log.info("执行{}.{}失败", client.getName(), action.getMethod(), ExceptionUtil.unwrapThrowable(e));
            invokeResult.setThrowable(e);
        }
        return invokeResult;
    }

    private static Method getClientMethod(Class client, CloudAction action) {
        String vendor = action.getCloudTemplate().getVendor();
        String nodeType = action.getCloudTemplate().getNodeType();
        String actionName = action.getAction();
        String key = String.join(":", vendor, nodeType, actionName, action.getMethod()).intern();
        Method method = clientMethodCache.get(key);
        if (method == null) {
            synchronized (key) {
                String[] argTypes = action.getArgTypes();
                Map<String, Object> argValues = action.getArgValues();
                if (argTypes != null) {
                    Class[] types = new Class[argTypes.length];
                    for (int i = 0; i < argTypes.length; i++) {
                        try {
                            types[i] = ClassUtils.getClass(argTypes[i]);
                        } catch (Throwable e) {
                            log.error("client:{}对应的Method:{}参数类型获取出错:{}", client.getName(), action.getMethod(), argTypes[i]);
                            throw new RuntimeException("client:" + client.getName() + "对应的Method:" + action.getMethod() + "参数类型获取出错:" + argTypes[i]);
                        }
                    }
                    ObjectSupplier<Method> methodSupplier = new ObjectSupplier();
                    ReflectionUtils.doWithMethods(client, m -> methodSupplier.set(m),
                            m -> Objects.equals(m.getName(), action.getMethod())
                                    && typeEquals(argTypes, types)
                                    && methodSupplier.get() == null);
                    method = methodSupplier.get();
                } else if (!CollectionUtils.isEmpty(argValues)) {
                    method = Arrays.stream(ReflectionUtils.getAllDeclaredMethods(client)).filter(m -> Objects.equals(m.getName(), action.getMethod()) && m.getParameterTypes().length == argValues.size()).findFirst().get();
                } else {
                    method = Arrays.stream(ReflectionUtils.getAllDeclaredMethods(client)).filter(m -> Objects.equals(m.getName(), action.getMethod()) && m.getParameterTypes().length == 0).findFirst().get();
                }
                if (method == null) {
                    log.error("client:{}找不到对应的Method:{}", client.getName(), action.getMethod());
                    throw new RuntimeException("client:" + client.getName() + "找不到对应的Method:" + action.getMethod());
                }
                method.setAccessible(true);
                clientMethodCache.put(key, method);
            }
        }
        return method;
    }

    private static InvokeParameter parseInvokeParameter(CloudAction action, Class client) throws Throwable {
        InvokeParameter invokeParameter = new InvokeParameter();
        Method method = getClientMethod(client, action);
        invokeParameter.setMethod(method);
        if (action.getArgValues() != null) {
            Map<String, Object> argValues = MapVariablesParser.parser(action.getArgValues(), action.getVariables());
            Object[] objects = argValues.values().toArray(new Object[]{});
            String[] argTypes = action.getArgTypes();
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < objects.length; i++) {
                Class targetType = argTypes == null ? parameterTypes[i] : ClassUtils.getClass(argTypes[i]);
                if (objects[i] != null) {
                    if (!targetType.isAssignableFrom(objects[i].getClass())) {
                        objects[i] = converter.convert(objects[i], targetType, null);
                    }
                }
            }
            invokeParameter.setObjects(objects);
        } else if (action.getArgNames() != null) {
            String[] argNames = action.getArgNames();
            String[] argTypes = action.getArgTypes();
            Class<?>[] parameterTypes = method.getParameterTypes();
            Object[] objects = new Object[argNames.length];
            Map<String, Object> variables = action.getVariables();
            for (int i = 0; i < objects.length; i++) {
                Class targetType = argTypes == null ? parameterTypes[i] : ClassUtils.getClass(argTypes[i]);
                Object arg = variables.get(argNames[i]);
                if (arg == null) {
                    objects[i] = null;
                } else if (targetType.isAssignableFrom(arg.getClass())) {
                    objects[i] = arg;
                } else {
                    Object exp = variables.get(argNames[i]);
                    if (exp instanceof String && (ELUtils.isSpelExpression((String) exp))) {
                        objects[i] = ELUtils.getSpelValue((String) exp, variables);
                    } else {
                        objects[i] = converter.convert(exp, targetType, null);
                    }
                }
            }
            invokeParameter.setObjects(objects);
        } else {
            invokeParameter.setObjects(new Object[]{});
        }
        return invokeParameter;
    }

    private static boolean typeEquals(String[] typeNames, Class[] types) {
        if (typeNames.length == types.length) {
            try {
                for (int i = 0; i < typeNames.length; i++) {
                    String type = typeNames[i];
                    if (!types[i].isAssignableFrom(ClassUtils.getClass(type))) {
                        return false;
                    }
                }
            } catch (Throwable e) {
                return false;
            }
            return true;
        }
        return false;
    }
}
