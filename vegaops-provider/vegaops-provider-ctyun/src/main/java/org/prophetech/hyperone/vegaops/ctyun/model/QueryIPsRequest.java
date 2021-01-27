package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;
import org.prophetech.hyperone.vegaops.ctyun.model.QueryIPsResponse;

@Setter
@Getter
public class QueryIPsRequest extends CtyunRequest<QueryIPsResponse> {
    private String regionId;

    private String publicIpId;

    private String limit;

    private String marker;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/ondemand/queryIps";
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
        return QueryIPsResponse.class;
    }
}
