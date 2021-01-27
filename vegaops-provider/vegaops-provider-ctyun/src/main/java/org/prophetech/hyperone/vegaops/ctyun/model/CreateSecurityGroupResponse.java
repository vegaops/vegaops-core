package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;
import org.prophetech.hyperone.vegaops.ctyun.model.GetSecurityGroupRulesResponse;

import java.util.List;

@Setter
@Getter
public class CreateSecurityGroupResponse extends CtyunResponse {
    /**
     * uuid
     */
    private String id;
    /**
     * VPC名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 安全组规则
     */
    private List<GetSecurityGroupRulesResponse.SecurityGroupRules> securityGroupRules;
}
