package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetELBsv4Request extends CtyunRequest<GetELBsv4Response> {
    /**
     * 资源池ID，请根据查询资源池列表接口返回值进行传参，获取“regionId”参数
     */
    private String regionId;

    @Override
    public String getUrl() {
        return "/apiproxy/v4/queryELBs";
    }

    @Override
    public Method getMethod() {
        return Method.GET;
    }

    @Override
    public BodyType getBodyType() {
        return BodyType.OBJECT;
    }

    @Override
    public Class<GetELBsv4Response> getResponseClass() {
        return GetELBsv4Response.class;
    }
}
