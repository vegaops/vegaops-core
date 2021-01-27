package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class QueryPriceResponse extends CtyunResponse {

    private Boolean isSucceed;
    private BigDecimal totalPrice;
    private BigDecimal finalPrice;
    private List<SubOrderPrice> subOrderPrices;
    private Integer verifyStatusCode;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SubOrderPrice{
        private String serviceTag;
        private BigDecimal totalPrice;
        private BigDecimal finalPrice;
        private Integer cycleType;
        private List<OrderItemPrice> orderItemPrices;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class OrderItemPrice{
        private String itemId;
        private String resourceType;
        private BigDecimal totalPrice;
        private BigDecimal finalPrice;

    }
}
