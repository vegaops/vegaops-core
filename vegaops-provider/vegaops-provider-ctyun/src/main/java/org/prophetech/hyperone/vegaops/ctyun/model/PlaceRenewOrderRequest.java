package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;
import org.prophetech.hyperone.vegaops.ctyun.model.PlaceRenewOrderResponse;

@Setter
@Getter
public class PlaceRenewOrderRequest extends CtyunRequest<PlaceRenewOrderResponse> {

    private String resourceDetailJson;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/order/placeRenewOrder";
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
    public Class<PlaceRenewOrderResponse> getResponseClass() {
        return PlaceRenewOrderResponse.class;
    }


}
