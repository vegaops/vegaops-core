package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;
import org.prophetech.hyperone.vegaops.ctyun.model.QueryPriceResponse;

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
