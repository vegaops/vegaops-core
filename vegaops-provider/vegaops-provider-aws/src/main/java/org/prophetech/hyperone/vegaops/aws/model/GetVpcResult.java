package org.prophetech.hyperone.vegaops.aws.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetVpcResult {
    private String cidrBlock;
    private String name;
    private String instanceTenancy;
    private Integer statusCode;
    private String vpcId;
    private String cidrBlcok;
    private String zoneId;
    private String vpcState;
    private String vswitchState;
    private String internetGatewayId;
    private String securityGroups;
    private String subnetId;
    private String routeTableId;
    private String associationId;
}
