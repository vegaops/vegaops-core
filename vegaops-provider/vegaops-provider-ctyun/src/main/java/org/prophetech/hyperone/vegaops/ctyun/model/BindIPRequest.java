package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;

@Setter
@Getter
public class BindIPRequest extends CtyunRequest<CtyunResponse> {

    private String regionId;
    private String publicIpId;
    private String privateIp;
    private String networkCardId;
    private String payType;

    @Override
    public String getUrl() {
        if ("PostPaid".equals(payType)) {
            return "/apiproxy/v3/ondemand/bindIp";
        } else {
            return "/apiproxy/v3/bindIp";
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
