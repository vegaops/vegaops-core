package org.prophetech.hyperone.vegaops.engine.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;
import org.hswebframework.web.bean.FastBeanCopier;
import org.prophetech.hyperone.vegaops.engine.parser.MapVariablesParser;
import org.prophetech.hyperone.vegaops.engine.utils.RuntimeContext;

import javax.validation.constraints.NotNull;
import java.util.*;

@Setter
@Getter
public class CloudContainer {
    @NotNull(message = "请输入credentials")
    private Credentials credentials;
    @NotNull(message = "请输入vendor")
    private String vendor;
    @NotNull(message = "请输入version")
    private String version;
    private Map<String, Object> variables = new LinkedHashMap<>();
    private List<CloudResourceNode> nodes = new ArrayList<>();
    private Map<String, CloudResourceNode> nodeMap = new LinkedHashMap<>();
    private boolean success;
    /**
     * 节点解析顺序
     */
    private List<String> resolveArrangement;
    /**
     * 已经执行的节点
     */
    private Set<String> resolvedNodes = new HashSet<>();
    /**
     * 待执行的节点
     */
    private Set<String> unresolvedNodes = new HashSet<>();

    public void readFormMap(Map source) {
        FastBeanCopier.copy(source, this);
        Map map = new LinkedHashMap(source);
        String[] keys = "vendor,nodes,variables".split(",");
        for (String key : keys) {
            map.remove(key);
        }
        variables.putAll(map);
        Map<String,Map> nodes = getNodeMap((List) source.get("nodes"));
        Set<String> components = new HashSet<>();
        this.nodes.forEach(n -> {
            if (components.contains(n.getComponentId())) {
                throw new RuntimeException(n.getComponentId() + "节点componentId重复");
            }
            components.add(n.getComponentId());
            n.validate();
            n.checkVariablesSafe();
            n.getVars().putAll(nodes.get(n.getComponentId()));
            Map parser = MapVariablesParser.parser(n.getVars(), variables);
            n.setVars(parser);
            unresolvedNodes.add(n.getComponentId());
            nodeMap.put(n.getComponentId(), n);
        });
        checkDependency();
    }

    /**
     * 将源数据node中的多余属性放到var变量中
     * @param nodes
     * @return
     */
    private Map<String,Map> getNodeMap(List<Map<String,Object>> nodes){
        Map<String,Map> nodeMap=new HashMap<>();
        if(CollectionUtils.isNotEmpty(nodes)){
            nodes.forEach(n->{
                if(n.containsKey("componentId")){
                    Map map=new HashMap(n);
                    map.remove("action");
                    map.remove("componentId");
                    map.remove("nodeType");
                    for(Object o:new HashSet<>(map.keySet())){
                        if(!(map.get(o) instanceof String)){
                            map.remove(o);
                        }
                    }
                    nodeMap.put((String)n.get("componentId"),map);
                }
            });
        }
        return nodeMap;
    }

    public void addNodeAtRuntime(String componentId,String action,String nodeType,Map vars){
        CloudResourceNode node=new CloudResourceNode();
        node.setComponentId(componentId);
        node.setAction(action);
        node.setNodeType(nodeType);
        node.setVars(vars);
        node.validate();
        node.checkVariablesSafe();
        Map parser = MapVariablesParser.parser(node.getVars(), variables);
        node.setVars(parser);
        unresolvedNodes.add(node.getComponentId());
        nodeMap.put(node.getComponentId(),node);
        nodes.add(node);
        checkDependency();
        RuntimeContext.getContext().getRunNodeList().add(node.getComponentId());
    }

    public CloudResourceNode getNode(String componentId) {
        return nodeMap.get(componentId);
    }

    public void addResolved(String componentId) {
        unresolvedNodes.remove(componentId);
        resolvedNodes.add(componentId);
    }

    public boolean isResolvedNode(String componentId) {
        return resolvedNodes.contains(componentId);
    }

    public Map<String, Object> getNodeOutput(String componentId) {
        return nodeMap.get(componentId).getOutput();
    }

    public Map<String, Map<String, Object>> getContainerOutput() {
        Map<String, Map<String, Object>> output = new LinkedHashMap<>();
        resolvedNodes.forEach(n -> {
            output.put(n, getNodeOutput(n));
        });
        return output;
    }

    public void checkDependency() {
        Map<String, Set<String>> dependencyMap = new LinkedHashMap<>();
        List<String> resolvedNodes = new ArrayList<>();
        List<String> unresolvedNodes = new ArrayList<>();
        nodes.forEach(n -> {
            Set<String> dependencyNodes = n.getDependencyNodes();
            dependencyMap.put(n.getComponentId(), dependencyNodes);
            if (CollectionUtils.isEmpty(dependencyNodes)) {
                resolvedNodes.add(n.getComponentId());
            } else {
                unresolvedNodes.add(n.getComponentId());
            }
        });
        int scanTimes = 0;
        while (++scanTimes < 100 && !unresolvedNodes.isEmpty()) {
            String node = unresolvedNodes.remove(0);
            Set<String> strings = dependencyMap.get(node);
            for (String s : strings.toArray(new String[0])) {
                if (resolvedNodes.contains(s)) {
                    strings.remove(s);
                }
            }
            if (dependencyMap.get(node).size() == 0) {
                resolvedNodes.add(node);
            } else {
                unresolvedNodes.add(node);
            }
        }
        if (!unresolvedNodes.isEmpty()) {
            throw new RuntimeException("无法解析节点依赖:" + unresolvedNodes);
        }
        resolveArrangement = resolvedNodes;
    }

    @JSONField(serialize = false, deserialize = false)
    public String toJson() {
        return JSON.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect);
    }


    public static CloudContainer readFromJson(String json) {
        return JSON.parseObject(json, CloudContainer.class);
    }
}
