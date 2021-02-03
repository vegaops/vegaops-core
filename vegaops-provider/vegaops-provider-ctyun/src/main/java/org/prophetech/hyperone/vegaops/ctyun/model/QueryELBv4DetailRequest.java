package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryELBv4DetailRequest extends CtyunRequest<CreateELBv4Response> {
    private String elbId;

    private String regionId;

    @Override
    public String getUrl() {
        return "/apiproxy/v4/queryELBDetail";
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
    public Class<CreateELBv4Response> getResponseClass() {
        return CreateELBv4Response.class;
    }
}
