package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryOrdersRequest extends CtyunRequest<QueryOrdersResponse> {
    private Integer pageNo;
    private Integer pageSize;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/order/queryOrders";
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
        return QueryOrdersResponse.class;
    }
}
