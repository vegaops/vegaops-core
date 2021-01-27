package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;

@Setter
@Getter
public class UpgradeVMRequest extends CtyunRequest<CtyunResponse> {
    /**
     * 主机id
     */
    private String vmId;
    /**
     * 资源池id 请根据查询资源池接口返回值进行传参，获取“regionId”参数
     */
    private String regionId;
    /**
     * 云主机规格ID
     */
    private String flavorId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/ondemand/upgradeVM";
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
