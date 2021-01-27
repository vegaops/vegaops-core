package org.prophetech.hyperone.vegaops.aws.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstanceParser {
    private String id;
    private String ramdiskId;
    private String name;
    private String image;
    private String flavor;
    private String vpcId;
    private String privateIp;
    private String securityGroups;
    private String zoneId;
    private String floatingIp;
    private String payType;
    private String regionId;
    private String osType;
    private String vswithId;
    private String memory;
    private Integer cpu;
    private String status;
    private String created;
    private String expireTime;
    private String bandwidthSize;
    private String keyPairName;
}
