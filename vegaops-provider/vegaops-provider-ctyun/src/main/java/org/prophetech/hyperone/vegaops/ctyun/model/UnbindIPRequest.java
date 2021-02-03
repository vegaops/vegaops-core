package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UnbindIPRequest extends CtyunRequest<CtyunResponse> {

    private String regionId;
    private String publicIpId;
    private String privateIp;
    private String payType;

    @Override
    public String getUrl() {
        if ("PostPaid".equals(payType)) {
            return "/apiproxy/v3/ondemand/unbindIp";
        } else {
            return "/apiproxy/v3/unbindIp";
        }
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
