package org.prophetech.hyperone.vegaops.alicloud.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 云主机创建参数
 */
@Setter
@Getter
public class CreateInstancesParamRequest {

    private String regionId;

    private String accessKey;

    private String secret;

    private String flavorId;

    private String imageId;

    private String securityGroups;

    private String name;

    private String internetChargeType;

    private Boolean autoRenew;

    private Integer autoRenewPeriod;

    private Integer internetMaxBandwidthOut;

    private Integer internetMaxBandwidthIn;

    private String hostName;

    private String password;

    private String zoneId;

    private String vlanId;

    private String systemDiskSize;

    private String systemDiskCategory;

    private String systemDiskDiskName;

    private String systemDiskDescription;

    private String systemDiskPerformanceLevel;

    private String description;

    private String VSwitchId;

    private String privateIp;

    private String instanceChargeType;

    private Integer period;

    private String periodUnit;

    private String userData;

    private String keyPairName;

    private String tagValue;






}
