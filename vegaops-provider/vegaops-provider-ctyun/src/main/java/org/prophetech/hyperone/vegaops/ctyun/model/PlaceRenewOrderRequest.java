package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

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
