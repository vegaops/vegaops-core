package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.GetZoneConfigResponse;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;

@Setter
@Getter
public class GetZoneConfigRequest extends CtyunRequest<GetZoneConfigResponse> {
    private String regionId;
    /**
     * 云平台类型 0：CloudStack，1：OpenStack 不传默认查询CloudStack资源池
     */
    private String cloudPlatformType;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/order/getZoneConfig";
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
    public Class<GetZoneConfigResponse> getResponseClass() {
        return GetZoneConfigResponse.class;
    }
}
