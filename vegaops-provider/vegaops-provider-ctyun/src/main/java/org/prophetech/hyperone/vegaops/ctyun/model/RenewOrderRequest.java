package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RenewOrderRequest {
    private String packageType;
    private String cycleCount;
    private String cycleType;
    private String regionId;
    private String resourceId;
    private Integer statusCode;
    private String instanceId;
}
