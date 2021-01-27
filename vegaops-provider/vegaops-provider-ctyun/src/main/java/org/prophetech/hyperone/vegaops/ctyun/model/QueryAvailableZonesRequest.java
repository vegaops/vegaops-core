package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;
import org.prophetech.hyperone.vegaops.ctyun.model.QueryAvailableZonesResponse;

@Setter
@Getter
public class QueryAvailableZonesRequest extends CtyunRequest<QueryAvailableZonesResponse> {
    private String regionId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/dcs/queryAvailableZones";
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
    public Class<QueryAvailableZonesResponse> getResponseClass() {
        return QueryAvailableZonesResponse.class;
    }
}
