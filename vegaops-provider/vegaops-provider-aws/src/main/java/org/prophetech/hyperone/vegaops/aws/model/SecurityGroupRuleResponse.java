package org.prophetech.hyperone.vegaops.aws.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityGroupRuleResponse {
    private Integer statusCode;

    private String protocol;

    private Integer portStart;

    private Integer portEnd;

    private String direction;

    private String securityGroupId;

    private String cidrBlock;

    private String ipv6CidrBlock;
}
