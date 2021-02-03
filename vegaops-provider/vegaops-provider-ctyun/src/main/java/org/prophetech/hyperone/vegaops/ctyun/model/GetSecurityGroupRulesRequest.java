package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetSecurityGroupRulesRequest extends CtyunRequest<GetSecurityGroupRulesResponse> {
    private String regionId;
    /**
     * 专属云资源池ID，请根据查询projectId接口返回值进行传参，获取“id”参数
     */
    private String projectId;
    /**
     * 安全组id
     */
    private String securityGroupId;
    /**
     * 安全组规则id
     */
    private String securityGroupRuleId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/getSecurityGroupRules";
    }

    @Override
    public Method getMethod() {
        return Method.GET;
    }

    @Override
    public BodyType getBodyType() {
        return BodyType.OBJECT;
    }

    @Override
    public Class getResponseClass() {
        return GetSecurityGroupRulesResponse.class;
    }
}
