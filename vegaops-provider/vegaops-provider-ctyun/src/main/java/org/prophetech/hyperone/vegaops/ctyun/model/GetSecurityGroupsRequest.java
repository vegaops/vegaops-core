package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.GetSecurityGroupsResponse;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;

@Setter
@Getter
public class GetSecurityGroupsRequest extends CtyunRequest<GetSecurityGroupsResponse> {
    private String regionId;

    private String vpcId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/getSecurityGroups";
    }

    @Override
    public Method getMethod() {
        return Method.GET;
    }

    @Override
    public BodyType getBodyType() {
        return BodyType.ARRAY;
    }

    @Override
    public Class getResponseClass() {
        return GetSecurityGroupsResponse.class;
    }
}
