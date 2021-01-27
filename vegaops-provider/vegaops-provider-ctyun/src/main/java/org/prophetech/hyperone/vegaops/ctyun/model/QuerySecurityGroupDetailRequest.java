package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;
import org.prophetech.hyperone.vegaops.ctyun.model.QuerySecurityGroupDetailResponse;

@Setter
@Getter
public class QuerySecurityGroupDetailRequest extends CtyunRequest<QuerySecurityGroupDetailResponse> {
    private String regionId;
    private String securityGroupId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/querySecurityGroupDetail";
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
        return QuerySecurityGroupDetailResponse.class;
    }
}
