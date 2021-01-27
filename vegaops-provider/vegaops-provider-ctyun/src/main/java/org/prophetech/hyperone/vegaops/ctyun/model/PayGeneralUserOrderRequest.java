package org.prophetech.hyperone.vegaops.ctyun.model;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.annotation.IgnoreParam;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;

@Setter
@Getter
public class PayGeneralUserOrderRequest extends CtyunRequest<CtyunResponse> {

    /**
     * 主订单ID
     */
    private String masterOrderId;
    /**
     * 支付信息 示例： {"cash": "10.0"}
     */
    private String amountJson;

    @IgnoreParam
    private String cash;

    @Override
    public void init() {
        amountJson = JSON.toJSONString(getIgnoreParamMap());
    }

    @Override
    public String getUrl() {
        return "/apiproxy/v3/order/payGeneralUserOrder";
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
    public Class<CtyunResponse> getResponseClass() {
        return CtyunResponse.class;
    }

}
