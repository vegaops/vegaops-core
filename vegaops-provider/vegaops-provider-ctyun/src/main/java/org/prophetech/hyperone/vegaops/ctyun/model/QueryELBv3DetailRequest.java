package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.GetELBsv3Response;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;

@Setter
@Getter
public class QueryELBv3DetailRequest extends CtyunRequest<GetELBsv3Response.loadbalancer> {
    private String elbId;

    private String regionId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/deleteELB";
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
    public Class<GetELBsv3Response.loadbalancer> getResponseClass() {
        return GetELBsv3Response.loadbalancer.class;
    }
}
