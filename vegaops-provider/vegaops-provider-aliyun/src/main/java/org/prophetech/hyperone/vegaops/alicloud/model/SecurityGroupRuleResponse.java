package org.prophetech.hyperone.vegaops.alicloud.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityGroupRuleResponse {

    private String portRange;

    private String ipProtocol;

    private String description;

    private String policy;

    private String direction;

    private String securityGroupId;

    private String destCidrIp;

    private String sourceCidrIp;

}
