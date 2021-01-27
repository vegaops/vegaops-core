package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;
import org.prophetech.hyperone.vegaops.ctyun.model.QueryNetworkCardsResponse;

@Setter
@Getter
public class QueryNetworkCardsRequest extends CtyunRequest<QueryNetworkCardsResponse> {
    private String regionId;

    private  String vmId;
    @Override
    public String getUrl() {
        return "/apiproxy/v3/queryNetworkCards";
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
    public Class<QueryNetworkCardsResponse> getResponseClass() {
        return QueryNetworkCardsResponse.class;
    }
}
