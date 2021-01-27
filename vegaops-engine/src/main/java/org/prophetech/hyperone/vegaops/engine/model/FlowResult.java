package org.prophetech.hyperone.vegaops.engine.model;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Setter
@Getter
public class FlowResult {
    private boolean success;
    private Map output=new LinkedHashMap();
    private Throwable throwable;
}
