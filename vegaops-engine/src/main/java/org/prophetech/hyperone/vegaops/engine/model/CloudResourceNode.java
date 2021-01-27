package org.prophetech.hyperone.vegaops.engine.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.engine.utils.ELUtils;
import org.springframework.util.Assert;

import java.util.*;

@Setter
@Getter
public class CloudResourceNode {

    private String componentId;
    private String nodeType;
    private String action="install";
    private Map<String, String> vars = new LinkedHashMap<>();
    private Map<String, Object> output = new LinkedHashMap<>();
    private Set<String> dependency;

    public void validate() {
        Assert.notNull(componentId, "componentId must not be null");
        Assert.notNull(nodeType, "nodeType must not be null");
    }

    public Set<String> getDependencyNodes() {
        if(dependency==null){
            dependency=getDependency(vars);
        }
        return new HashSet<>(dependency);
    }

    private Set<String>  getDependency(Map map){
        Set<String> dependency=new HashSet<>(0);
        if (map != null) {
            Collection values = map.values();
            values.forEach(v -> {
                if (v instanceof String) {
                    String expression = ((String) v).trim();
                    if(expression.startsWith("&")){
                        String[] split = expression.split("[.]");
                        dependency.add(split[0].substring(1));
                    }
                }else throw new RuntimeException("vars节点内容只能是Map<String,String>");
            });
        }
        return dependency;
    }

    public void checkVariablesSafe(){
        checkMap(getVars());
    }

    /**
     * 变量的表达式中不允许出现(),防止方法通过velocity表达式或者SpringEl注入引发安全问题
     *
     * @param map
     */
    private void checkMap(Map map) {
        if (map != null) {
            Collection values = map.values();
            values.forEach(v -> {
                if (v instanceof Map) {
                    checkMap((Map) v);
                } else if (v instanceof String) {
                    String expression = (String) v;
                    if (ELUtils.isSpelExpression(expression)) {
                        throw new RuntimeException("不支持的表达式:" + expression);
                    }
                }
            });
        }
    }
}
