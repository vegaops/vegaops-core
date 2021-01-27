package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;
import org.prophetech.hyperone.vegaops.ctyun.model.QueryJobStatusResponse;

/**
 * 轮询资源状态
 */
@Setter
@Getter
public class QueryJobStatusRequest extends CtyunRequest<QueryJobStatusResponse> {
    private String regionId;
    private String jobId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/queryJobStatus";
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
        return QueryJobStatusResponse.class;
    }
}
