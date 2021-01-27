package org.prophetech.hyperone.vegaops.aws.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateVpcParam {
    private String cidrBlock;
    private String name;
    private String instanceTenancy;
    private String statusCode;
    private String kernelId;
    private String keypairName;
    private Integer maxCount;
    private Integer minCount;
    private String zoneId;
    private String privateIpAddress;
    private String ramDiskId;
    private String internetGatewayId;
    private String vpcId;
    private String vswitchId;
    private String routeTableId;
    private  String availabilityZone;


}
