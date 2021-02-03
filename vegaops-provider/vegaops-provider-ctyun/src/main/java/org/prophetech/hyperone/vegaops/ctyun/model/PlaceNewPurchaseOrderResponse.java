package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlaceNewPurchaseOrderResponse extends CtyunResponse {
    /**
     * 订单ID
     */
    private String newOrderId;
    /**
     * 订单号
     */
    private String newOrderNo;
    /**
     * 总价
     */
    private String totalPrice;
}
