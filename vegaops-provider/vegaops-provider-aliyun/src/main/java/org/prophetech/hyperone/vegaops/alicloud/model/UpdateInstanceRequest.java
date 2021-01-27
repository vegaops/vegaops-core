package org.prophetech.hyperone.vegaops.alicloud.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateInstanceRequest {

    private String payType;

    private String instanceType;

    private String instanceId;

    private Boolean allowMigrateAcrossZone;

    private Integer internetMaxBandwidthOut;

    private Integer internetMaxBandwidthIn;

    private String operatorType;

    private Boolean autoPay;

    private String regionId;

    private String accessKey;

    private String secret;

}
