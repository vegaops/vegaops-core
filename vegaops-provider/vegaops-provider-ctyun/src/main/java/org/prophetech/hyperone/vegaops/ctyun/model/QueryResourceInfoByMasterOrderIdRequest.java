package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryResourceInfoByMasterOrderIdRequest extends CtyunRequest<QueryResourceInfoByMasterOrderIdResponse> {
    /**
     * 订单ID
     */
    private String masterOrderId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/order/queryResourceInfoByMasterOrderId";
    }

    @Override
    public Method getMethod() {
        return Method.GET;
    }

    @Override
    public BodyType getBodyType() {
        return BodyType.ARRAY;
    }

    @Override
    public Class getResponseClass() {
        return QueryResourceInfoByMasterOrderIdResponse.class;
    }
}
