package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryDataDiskDetailRequest extends CtyunRequest<QueryDataDiskDetailResponse> {
    private String regionId;
    private String volumeId;
    @Override
    public String getUrl() {
        return "/apiproxy/v3/queryDataDiskDetail";
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
        return QueryDataDiskDetailResponse.class;
    }
}
