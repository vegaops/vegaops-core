package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.GetKeyPairsResponse;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;

@Setter
@Getter
public class GetKeyPairsRequest extends CtyunRequest<GetKeyPairsResponse> {
    private String regionId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/querySSH";
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
        return GetKeyPairsResponse.class;
    }
}
