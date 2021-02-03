package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlaceRefundOrderResponse extends CtyunResponse {
    private String newOrderId;
    private String newOrderNo;
    private String totalPrice;


}
