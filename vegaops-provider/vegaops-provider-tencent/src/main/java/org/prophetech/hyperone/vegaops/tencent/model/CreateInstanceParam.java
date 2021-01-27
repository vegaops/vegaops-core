package org.prophetech.hyperone.vegaops.tencent.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateInstanceParam {

    private String hostName;
    private String imageId;
    private Long period;
    private String instanceChargeType;
    private Long instanceCount;
    private String instanceName;
    private String instanceType;
    private String internetChargeType;
    private Long internetMaxBandwidthOut;
    private String bandwidthPackageId;
    private Boolean publicIpAssigned;
    private String keyName;
    private String password;
    private String zone;
    private String securityGroupId;
    private Long diskSize;
    private String diskType;
    private String vpcId;
    private String subnetId;
    private String regionId;
    private Boolean asVpcGateway;
    private Long ipv6AddressCount;
    private String userData;
    private String renewFlag;
    private String privateIpAddresses;

}
