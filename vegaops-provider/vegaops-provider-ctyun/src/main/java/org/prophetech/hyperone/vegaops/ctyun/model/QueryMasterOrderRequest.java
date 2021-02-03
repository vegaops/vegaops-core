package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryMasterOrderRequest extends CtyunRequest<QueryVMsByOrderIdResponse> {
    /**
     * 订单ID
     */
    private String masterOrderId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/order/queryMasterOrder";
    }

    @Override
    public Method getMethod() {
        return Method.GET;
    }

    @Override
    public BodyType getBodyType() {
        return BodyType.OBJECT;
    }

    @Override
    public Class getResponseClass() {
        return QueryVMsByOrderIdResponse.class;
    }
}
