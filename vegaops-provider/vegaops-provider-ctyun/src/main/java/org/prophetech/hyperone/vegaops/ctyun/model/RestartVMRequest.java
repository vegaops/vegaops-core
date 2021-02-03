package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestartVMRequest extends CtyunRequest<CtyunResponse> {
    /**
     * 主机id
     */
    private String vmId;
    /**
     * 资源池id 请根据查询资源池接口返回值进行传参，获取“regionId”参数
     */
    private String regionId;
    /**
     * 重启类型：SOFT：普通重启。HARD：强制重启。
     */
    private String type;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/ondemand/restartVM";
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
