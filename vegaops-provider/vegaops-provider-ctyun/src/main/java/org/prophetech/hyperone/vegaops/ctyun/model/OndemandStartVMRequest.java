package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OndemandStartVMRequest extends CtyunRequest<GetJobIdResponse> {
    private String regionId;
    private String vmId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/ondemand/startVM";
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
        return GetJobIdResponse.class;
    }
}
