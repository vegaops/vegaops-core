package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteSecurityGroupRequest extends CtyunRequest<CtyunResponse> {

    private String regionId;
    private String securityGroupId;
    private String projectId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/deleteSecurityGroup";
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
    public Class<CtyunResponse> getResponseClass() {
        return CtyunResponse.class;
    }
}
