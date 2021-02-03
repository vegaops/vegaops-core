package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PlaceRenewOrderResponse extends CtyunResponse {

    private List<OrderPlacedEvents> orderPlacedEvents;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class OrderPlacedEvents{
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
}
