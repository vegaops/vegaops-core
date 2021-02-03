package org.prophetech.hyperone.vegaops.engine.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.engine.bean.FastBeanCopier;

import java.util.LinkedHashMap;
import java.util.Map;

@Setter
@Getter
public class CloudActionFlow {
    private String action;
    private Map<String, Object> input;
    /**
     * 默认等待时间3秒
     */
    private long waitInterval=3000;
    /**
     * 最大等待时间30秒
     */
    private int retryTimes=10;
    private String retry="true";
    private LinkedHashMap<String, Object> output;
    private CloudAction cloudAction;
    private LinkedHashMap<String, Object> variables=new LinkedHashMap<>();

    public void readFormMap(Map source){
        FastBeanCopier.copy(source,this);
        Map map=new LinkedHashMap(source);
        String[] keys="action,input,waitInterval,maxWait,output,retry".split(",");
        for(String key:keys){
            map.remove(key);
        }
        variables.putAll(map);
    }
}
