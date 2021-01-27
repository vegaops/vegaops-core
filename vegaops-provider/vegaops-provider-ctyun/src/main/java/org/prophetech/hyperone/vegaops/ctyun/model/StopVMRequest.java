package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;

@Setter
@Getter
public class StopVMRequest extends CtyunRequest<CtyunResponse> {
    private String regionId;
    private String vmId;

    /**
     * 关机类型，取值范围：SOFT、HARD。不传默认为SOFT。SOFT表示普通关机。HARD表示强制关机
     */
    private String type;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/stopVM";
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
        return CtyunResponse.class;
    }
}
