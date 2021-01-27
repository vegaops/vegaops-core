package org.prophetech.hyperone.vegaops.ctyun.model;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.TreeMap;

@Getter
@Setter
public class ResourceDetailJson {

    private List<String> resourceIds;

    private String cycleCount;

    private String cycleType;

    public String toJson() {
        TreeMap map = new TreeMap();
        map.put("resourceIds", resourceIds);
        map.put("cycleCount", cycleCount);
        map.put("cycleType", cycleType);
        return JSON.toJSONString(map);
    }
}
