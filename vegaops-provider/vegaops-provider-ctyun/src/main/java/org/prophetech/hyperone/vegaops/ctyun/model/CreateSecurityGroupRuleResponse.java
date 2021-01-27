package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;
import org.prophetech.hyperone.vegaops.ctyun.model.GetSecurityGroupRulesResponse;


@Setter
@Getter
public class CreateSecurityGroupRuleResponse extends CtyunResponse {

    private GetSecurityGroupRulesResponse.SecurityGroupRules security_group_rule;

    private String message;

}
