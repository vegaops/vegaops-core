package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetELBsv3Request extends CtyunRequest<GetELBsv3Response> {
    /**
     * 资源池ID，请根据查询资源池列表接口返回值进行传参，获取“regionId”参数
     */
    private String regionId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/queryELBs";
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
    public Class<GetELBsv3Response> getResponseClass() {
        return GetELBsv3Response.class;
    }
}
