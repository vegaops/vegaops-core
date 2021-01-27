package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;

@Setter
@Getter
public class CancelOrderRequest extends CtyunRequest<CtyunResponse> {
    private String masterOrderId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/order/cancelOrder";
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
