package org.prophetech.hyperone.vegaops.alicloud.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateSecurityGroupRuleRequest {

    private String ipProtocol;

    private String portRange;

    private String description;

    private String policy;

    private String direction;

    private String sourceCidrIp;

    private String securityGroupId;

    private String regionId;

    private String accessKey;

    private String destCidrIp;

    private String priority;

    private String secret;

}
