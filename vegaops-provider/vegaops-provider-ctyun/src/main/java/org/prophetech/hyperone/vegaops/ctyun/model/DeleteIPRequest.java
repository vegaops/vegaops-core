package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteIPRequest extends CtyunRequest<CtyunResponse> {

    private String regionId;
    private String publicIpId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/ondemand/deleteIp";
    }

    @Override
    public Method getMethod() {
        return Method.POST;
    }

    @Override
    public BodyType getBodyType() {
        return BodyType.String;
    }

    @Override
    public Class getResponseClass() {
        return CtyunResponse.class;
    }
}
