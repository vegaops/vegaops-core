package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;
import org.prophetech.hyperone.vegaops.ctyun.model.PlaceNewPurchaseOrderResponse;

@Setter
@Getter
public class PlaceNewPurchaseOrderRequest extends CtyunRequest<PlaceNewPurchaseOrderResponse> {

    private String orderDetailJson;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/order/placeNewPurchaseOrder";
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
    public Class<PlaceNewPurchaseOrderResponse> getResponseClass() {
        return PlaceNewPurchaseOrderResponse.class;
    }


}
