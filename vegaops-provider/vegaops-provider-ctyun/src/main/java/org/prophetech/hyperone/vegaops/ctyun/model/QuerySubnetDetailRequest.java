package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuerySubnetDetailRequest extends CtyunRequest<QuerySubnetDetailResponse> {
    private String regionId;
    private String subnetId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/querySubnetDetail";
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
    public Class<QuerySubnetDetailResponse> getResponseClass() {
        return QuerySubnetDetailResponse.class;
    }
}
