package org.prophetech.hyperone.vegaops.ctyun.model;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.annotation.IgnoreParam;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CreateIPResponse;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;

@Setter
@Getter
public class CreateIPRequest extends CtyunRequest<CreateIPResponse> {
    private String createIpInfo;

    @IgnoreParam
    private String regionId;
    @IgnoreParam
    private String zoneId;
    @IgnoreParam
    private String name;
    @IgnoreParam
    private String type;
    @IgnoreParam
    private String size;
    @IgnoreParam
    private String ipVersion;
    @IgnoreParam
    private String shareType;
    @IgnoreParam
    private String chargeMode;

    @Override
    public void init() {
        createIpInfo = JSON.toJSONString(getIgnoreParamMap());
    }

    @Override
    public String getUrl() {
        return "/apiproxy/v3/ondemand/createIp";
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
        return CreateIPResponse.class;
    }
}
