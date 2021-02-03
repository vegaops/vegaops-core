package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryPriceRequest extends CtyunRequest<QueryPriceResponse> {

    private String orderDetailJson;

    /**
     * 周期类型 0表示按小时计费
     */
    private String cycleType;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/ondemand/queryPrice";
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
    public Class<QueryPriceResponse> getResponseClass() {
        return QueryPriceResponse.class;
    }
}
