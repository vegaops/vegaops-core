package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StartVMRequest extends CtyunRequest<CtyunResponse> {
    private String regionId;
    private String vmId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/startVM";
    }

    @Override
    public Method getMethod() {
        return Method.POST;
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
