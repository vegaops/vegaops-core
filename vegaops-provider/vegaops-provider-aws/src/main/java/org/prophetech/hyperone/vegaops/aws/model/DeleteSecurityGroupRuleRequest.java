package org.prophetech.hyperone.vegaops.aws.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteSecurityGroupRuleRequest {

    private String protocol;

    private Integer portStart;

    private Integer portEnd;

    private String direction;

    private String securityGroupId;

    private String cidr;


}
