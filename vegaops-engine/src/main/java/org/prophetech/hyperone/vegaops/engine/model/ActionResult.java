package org.prophetech.hyperone.vegaops.engine.model;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

@Setter
@Getter
public class ActionResult {
    private boolean invokeSuccess;
    private boolean needRetry;
    private Throwable throwable;
    private LinkedHashMap<String, Object> output;
}
