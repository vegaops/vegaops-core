package org.prophetech.hyperone.vegaops.ctyun.model;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.annotation.IgnoreParam;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.GetKeyPairsResponse;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;

import java.util.List;

@Setter
@Getter
public class PlaceRefundOrderRequest extends CtyunRequest<GetKeyPairsResponse> {

    private String resourceDetailJson;

    private String type;

    @IgnoreParam
    private List<String> resourceIds;
    @IgnoreParam
    private String refundingCash;
    @IgnoreParam
    private String autoApproval;

    @Override
    public void init() {
        resourceDetailJson = JSON.toJSONString(getIgnoreParamMap());
    }

    @Override
    public String getUrl() {
        return "/apiproxy/v3/order/placeRefundOrder";
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
    public Class getResponseClass() {
        return GetKeyPairsResponse.class;
    }

}
