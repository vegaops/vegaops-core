package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;
import org.prophetech.hyperone.vegaops.ctyun.model.QueryDataDiskDetailResponse;

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
