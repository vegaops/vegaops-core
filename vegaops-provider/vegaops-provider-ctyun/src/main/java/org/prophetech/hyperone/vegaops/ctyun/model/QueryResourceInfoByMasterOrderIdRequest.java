package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;
import org.prophetech.hyperone.vegaops.ctyun.model.QueryResourceInfoByMasterOrderIdResponse;

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
