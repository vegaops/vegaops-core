package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.GetJobIdResponse;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;

@Setter
@Getter
public class DeleteVMRequest extends CtyunRequest<GetJobIdResponse> {
    /**
     * 资源池Id 请根据查询资源池接口返回值进行传参，获取“regionId”参数
     */
    private String regionId;

    /**
     * 真实云主机id
     */
    private String vmId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/ondemand/deleteVM";
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
    public Class<GetJobIdResponse> getResponseClass() {
        return GetJobIdResponse.class;
    }
}
