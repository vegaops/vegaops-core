package org.prophetech.hyperone.vegaops.engine.parser;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.exception.TimeoutException;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudContainer;
import org.prophetech.hyperone.vegaops.engine.model.CloudResourceNode;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.utils.ELUtils;
import org.prophetech.hyperone.vegaops.engine.utils.RuntimeContext;

import java.util.*;
import java.util.function.Predicate;

@Slf4j(topic = "vegaops")
public class ContainerParser {

    public static void install(CloudContainer container) {
        try {
            List<String> resolveArrangement = new ArrayList<>(container.getResolveArrangement());
            resolveArrangement.removeAll(container.getResolvedNodes());
            List<String> runNodeList=new ArrayList<>(resolveArrangement);
            RuntimeContext.setContext(new RuntimeContext(container,runNodeList));
            while (CollectionUtils.isNotEmpty(runNodeList)){
                CloudResourceNode node = container.getNode(runNodeList.remove(0));
                boolean b = parseNode(container, node,false);
                if (!b) {
                    log.warn("node:{}安装失败!", node.getComponentId());
                    return;
                }
            }
            container.setSuccess(true);
        }catch (TimeoutException e){
            throw e;
        }catch (Throwable e){
            container.setSuccess(false);
            log.error("parse {}:container error",container.getVendor(),e);
        }finally {
            RuntimeContext.remove();
        }
    }

    public static void uninstall(CloudContainer container) {
        try {
            List<String> resolveArrangement = new ArrayList<>(container.getResolveArrangement());
            Collections.reverse(resolveArrangement);
            resolveArrangement.removeAll(container.getUnresolvedNodes());
            List<String> runNodeList=new ArrayList<>(resolveArrangement);
            RuntimeContext.setContext(new RuntimeContext(container,runNodeList));
            while (CollectionUtils.isNotEmpty(runNodeList)){
                CloudResourceNode node = container.getNode(runNodeList.remove(0));
                Map output = node.getOutput();
                node.getVars().putAll(output);
                parseNode(container, node,true);
            }
            container.setSuccess(true);
        }catch (TimeoutException e){
            throw e;
        }catch (Throwable e){
            container.setSuccess(false);
            log.error("parse {}:container error",container.getVendor(),e);
        }finally {
            RuntimeContext.remove();
        }
    }

    private static boolean parseNode(CloudContainer container, CloudResourceNode node,boolean isUninstall) {
        replaceDependency(container, node);
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate(container.getVendor(),container.getVersion(), node.getNodeType());
        cloudTemplate.setComponentId(node.getComponentId());
        cloudTemplate.inputVars(container.getVariables());
        cloudTemplate.inputVars(node.getVars());
        String actionName = node.getAction();
        if (isUninstall) {
            actionName = cloudTemplate.getUninstallAction(node.getAction());
        }
        CloudAction action = cloudTemplate.getCloudAction(actionName);
        ActionParser.parse(action);
        Map<String, Object> output = action.getOutput();
        if(Objects.equals(output.get("success")+"","true")){
            node.setOutput(output);
            container.addResolved(node.getComponentId());
            return true;
        }
        return false;
    }

    private static void replaceDependency(CloudContainer container, CloudResourceNode node) {
        if (!node.getDependency().isEmpty()) {
            Map<String, String> vars = node.getVars();
            for (String key : vars.keySet().toArray(new String[0])) {
                String value = vars.get(key).trim();
                if (value.startsWith("&")) {
                    String[] split = value.split("[.]");
                    String varName=split[1].replaceAll("[^A-Za-z0-9]", "");
                    Map<String, Object> nodeOutput = container.getNodeOutput(split[0].substring(1));
                    Object o = nodeOutput.get(varName);
                    if (o == null) {
                        vars.remove(key);
                    } else {
                        if (split.length > 2) {
                            Map tempVars = new HashMap();
                            tempVars.put(varName, o);
                            o = ELUtils.getSpelValue( "#" + value.substring(value.indexOf(".") + 1),tempVars);
                        }
                        vars.put(key, o.toString());
                    }
                }
            }
        }
    }
}
