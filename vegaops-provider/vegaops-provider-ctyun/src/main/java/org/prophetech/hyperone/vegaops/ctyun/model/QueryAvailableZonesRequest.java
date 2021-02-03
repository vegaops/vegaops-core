package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

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
