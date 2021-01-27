package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CreateSecurityGroupResponse;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;

@Setter
@Getter
public class CreateSecurityGroupRequest extends CtyunRequest<CreateSecurityGroupResponse> {
    /**
     * 资源池ID，请根据查询资源池列表接口返回值进行传参，获取“regionId”参数
     */
    private String regionId;
    /**
     * 安全组名称
     */
    private String name;
    /**
     * 真实VPCID
     */
    private String vpcId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/createSecurityGroup";
    }

    @Override
    public Method getMethod() {
        return Method.POST;
    }

    @Override
    public BodyType getBodyType() {
        return BodyType.OBJECT;
    }

    @Override
    public Class getResponseClass() {
        return CreateSecurityGroupResponse.class;
    }
}
