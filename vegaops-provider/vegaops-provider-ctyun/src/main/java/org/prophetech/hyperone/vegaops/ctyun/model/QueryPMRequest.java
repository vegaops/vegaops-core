package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryPMRequest extends CtyunRequest<CtyunResponse> {
    private String pageNo;
    private String pageSize;
    private String orderId;
    private String regionId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/queryPM";
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
        return CtyunResponse.class;
    }
}
