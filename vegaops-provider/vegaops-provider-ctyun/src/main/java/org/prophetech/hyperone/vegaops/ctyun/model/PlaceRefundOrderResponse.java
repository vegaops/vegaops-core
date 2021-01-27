package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;

@Setter
@Getter
public class PlaceRefundOrderResponse extends CtyunResponse {
    private String newOrderId;
    private String newOrderNo;
    private String totalPrice;


}
