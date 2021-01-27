package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;
import org.prophetech.hyperone.vegaops.ctyun.model.QueryVMsByOrderIdResponse;

@Setter
@Getter
public class QueryVMsByOrderIdRequest extends CtyunRequest<QueryVMsByOrderIdResponse> {
    /**
     * 订单ID
     */
    private String orderId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/queryVMsByOrderId";
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
        return QueryVMsByOrderIdResponse.class;
    }
}
