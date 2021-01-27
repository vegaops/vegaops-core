package org.prophetech.hyperone.vegaops.engine.model;

import lombok.Getter;
import lombok.Setter;
import org.hswebframework.web.bean.FastBeanCopier;
import org.prophetech.hyperone.vegaops.engine.parser.MapVariablesParser;
import org.prophetech.hyperone.vegaops.engine.utils.SecretMaskJsonSerializer;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Setter
@Getter
public class CloudAction {
    private String action;
    private String client;
    private String method;
    private String[] argTypes;
    private String[] argNames;
    private List<String> maskKeys;
    /**
     * 默认等待时间3秒
     */
    private long waitInterval=1000;
    /**
     * 最多重试0次
     */
    private int retryTimes=0;
    private Map<String, Object> classProperties;
    private Map<String, Object> argValues;
    private LinkedHashMap<String, Object> output;
    private Map<String, Object> variables=new LinkedHashMap<>();
    private List<CloudActionFlow> flow;
    private CloudTemplate cloudTemplate;
    private boolean outputLog=true;

    public void readFormMap(Map source){
        FastBeanCopier.copy(source,this);
        Map map=new LinkedHashMap(source);
        String[] keys="client,output,variables,flow,argValues,maskKeys".split(",");
        for(String key:keys){
            map.remove(key);
        }
        variables.putAll(cloudTemplate.getVariables());
        variables.putAll(map);
        if(maskKeys==null){
            maskKeys=(List<String>) variables.get("maskKeys");
        }else {
            maskKeys.addAll((List<String>) variables.get("maskKeys"));
        }
        SecretMaskJsonSerializer.addSecretMaskKeys(maskKeys);
        variables=MapVariablesParser.parser(variables,variables);
        classProperties=(Map<String, Object>) variables.get("classProperties");
        FastBeanCopier.copy(variables,this);
        if(output!=null){
            String log = output.remove("log")+"";
            outputLog= !Objects.equals("false",log);
        }
    }
}
