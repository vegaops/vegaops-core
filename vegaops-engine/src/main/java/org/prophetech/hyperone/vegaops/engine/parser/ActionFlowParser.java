package org.prophetech.hyperone.vegaops.engine.parser;

import lombok.extern.slf4j.Slf4j;
import org.prophetech.hyperone.vegaops.engine.utils.ELUtils;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.exception.TimeoutException;
import org.prophetech.hyperone.vegaops.engine.model.*;
import org.prophetech.hyperone.vegaops.engine.utils.ExceptionUtil;
import org.prophetech.hyperone.vegaops.engine.utils.PlaceHolderUtils;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j(topic = "vegaops")
public class ActionFlowParser {
    /**
     * 解析flow返回output
     *
     * @param actionFlow
     * @return output
     */
    public static FlowResult parse(CloudActionFlow actionFlow) {
        String action = actionFlow.getAction();
        FlowResult result=new FlowResult();
        Assert.notNull(action, "action must not be null");
        Assert.notNull(actionFlow.getOutput(), "output must not be null");
        Assert.notNull(actionFlow.getOutput().get("success"), "output.success must not be null");
        if (actionFlow.getInput() != null) {
            CloudTemplate cloudTemplate = actionFlow.getCloudAction().getCloudTemplate();
            Map<String, Object> vars = new LinkedHashMap<>(actionFlow.getVariables());
            vars.putAll(actionFlow.getCloudAction().getVariables());
            LinkedHashMap<String, Object> bizOutput = actionFlow.getCloudAction().getOutput();
            if(bizOutput!=null){
                bizOutput.forEach((k,v)->{
                    if(v instanceof String){
                        if(!ELUtils.isSpelExpression((String) v)){
                            vars.put(k,v);
                        }
                    }else {
                        vars.put(k,v);
                    }
                });
            }
            Map<String, Object> input = MapVariablesParser.parser(actionFlow.getInput(), vars);
            String[] split = action.split("[.]");

            String vendor = cloudTemplate.getVendor();
            String version = cloudTemplate.getVersion();
            String nodeType = cloudTemplate.getNodeType();
            String flowAction = action;
            if (split.length == 2) {
                nodeType = split[0];
                flowAction = split[1];
            } else if (split.length > 2) {
                vendor = split[0];
                nodeType = split[1];
                flowAction = split[2];
            }
            int times = 0;
            CloudAction cloudAction;
            boolean retry=true;
            do {
                Map variables = new LinkedHashMap(input);
                variables.putAll(cloudTemplate.getVariables());
                cloudAction= CloudTemplateFactory.getTemplate(vendor,version, nodeType, variables).getCloudAction(flowAction);
                FlowResult flowResult = execute(cloudAction, actionFlow, ++times);
                log.info("execute:{}",flowResult.isSuccess());
                if (flowResult.isSuccess()) {
                    String successExp = actionFlow.getOutput().get("success")+"";
                    Assert.notNull(successExp, "flow output must have success expression");
                    Map flowOutputVars=new LinkedHashMap(cloudAction.getVariables());
                    flowOutputVars.putAll(cloudAction.getOutput());
                    boolean success = "true".equalsIgnoreCase(PlaceHolderUtils.getPlaceHolder(successExp, flowOutputVars) + "");
                    log.info("success:{},successExp:{}",success,successExp);
                    if (success) {
                        String handler = (String) actionFlow.getOutput().remove("handler");
                        if (handler != null) {
                            Map handlerVars = new HashMap();
                            handlerVars.put("output", flowOutputVars);
                            handlerVars.put("vendor", vendor);
                            handlerVars.put("nodeType",nodeType);
                            handlerVars.put("resourceType", nodeType);
                            handlerVars.put("action",flowAction);
                            try {
                                log.info("执行handler:{}",handler);
                                ELUtils.getSpelValue(handler, handlerVars);
                            }catch (Throwable e){
                                log.error("执行handler出错",e);
                            }
                        }
                        result.setSuccess(true);
                        String mergeKeys = (String) actionFlow.getOutput().remove("mergeKeys");
                        if (mergeKeys != null) {
                            Map output=new LinkedHashMap();
                            if ("*".equals(mergeKeys)) {
                                output.putAll(cloudAction.getOutput());
                            } else {
                                for (String key : mergeKeys.split(",")) {
                                    output.put(key, cloudAction.getOutput().get(key));
                                }
                            }
                            result.setOutput(output);
                        }
                        return result;
                    }
                }

                if(times < actionFlow.getRetryTimes()){
                    Map retryVars=new HashMap();
                    if(flowResult.getOutput()!=null){
                        retryVars.putAll(flowResult.getOutput());
                    }
                    retryVars.put("throwable",flowResult.getThrowable());
                    retry = ELUtils.getSpelValue(actionFlow.getRetry(), retryVars);
                    if(retry){
                        log.info("等待{}毫秒再执行{}{}的flow", actionFlow.getWaitInterval(), cloudAction.getAction(), cloudAction.getCloudTemplate().getComponentId());
                        try {
                            Thread.sleep(actionFlow.getWaitInterval());
                        } catch (InterruptedException e) {
                            log.error("重试Flow：{}{}等待出错", cloudAction.getAction(), cloudAction.getCloudTemplate().getComponentId());
                        }
                    }else {
                        return result;
                    }
                }else {
                    result.setThrowable(new TimeoutException(cloudAction.getCloudTemplate().getComponentId() + "," + action + "重试" + times + "次后执行失败！"));
                    return result;
                }
            } while (retry);
            throw new RuntimeException("Flow：" + cloudAction.getAction() + cloudAction.getCloudTemplate().getComponentId() + "重试" + times + "次后执行失败！");
        }
        return result;
    }

    private static FlowResult execute(CloudAction cloudAction, CloudActionFlow actionFlow, int times) {
        log.info("第{},次执行{}{}的flow", times, cloudAction.getAction(), cloudAction.getCloudTemplate().getComponentId());
        FlowResult flowResult=new FlowResult();
        try {
            ActionResult actionResult = ActionParser.parse(cloudAction);
            flowResult.setSuccess(actionResult.isInvokeSuccess());
            if (actionResult.isInvokeSuccess()) {
                Map vars=new HashMap(cloudAction.getVariables());
                vars.putAll(cloudAction.getOutput());;
                MapVariablesParser.parser(actionFlow.getOutput(), vars);
                log.info("执行{}{}的flow成功", cloudAction.getAction(), cloudAction.getCloudTemplate().getComponentId());
                flowResult.setSuccess(true);
                flowResult.setOutput(actionResult.getOutput());
            }
            flowResult.setThrowable(actionResult.getThrowable());
        } catch (Throwable e) {
            flowResult.setSuccess(false);
            flowResult.setThrowable(ExceptionUtil.unwrapThrowable(e));
            log.info("执行{}{}的flow失败", cloudAction.getAction(), cloudAction.getCloudTemplate().getComponentId(), e);
        }
        return flowResult;
    }
}
