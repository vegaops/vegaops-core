package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetSubnetsRequest extends CtyunRequest<GetSubnetsResponse> {
    private String regionId;
    /**
     * 专属云资源池ID，请根据查询projectId接口返回值进行传参，获取“id”参数
     */
    private String vpcId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/getSubnets";
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
    public Class getResponseClass() {
        return GetSubnetsResponse.class;
    }
}
