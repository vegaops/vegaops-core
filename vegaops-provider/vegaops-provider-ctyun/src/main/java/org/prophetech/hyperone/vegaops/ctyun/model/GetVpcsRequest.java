package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetVpcsRequest extends CtyunRequest<GetVpcsResponse> {
    private String regionId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/getVpcs";
    }

    @Override
    public Method getMethod() {
        return Method.GET;
    }

    @Override
    public BodyType getBodyType() {
        return BodyType.ARRAY;
    }

    @Override
    public Class getResponseClass() {
        return GetVpcsResponse.class;
    }
}
