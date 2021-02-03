package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryVPCDetailRequest extends CtyunRequest<QueryVPCDetailResponse> {
    private String regionId;
    private String vpcId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/queryVPCDetail";
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
        return QueryVPCDetailResponse.class;
    }
}
