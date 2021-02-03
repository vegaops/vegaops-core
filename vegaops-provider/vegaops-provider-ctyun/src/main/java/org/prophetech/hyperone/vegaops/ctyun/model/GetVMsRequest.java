package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetVMsRequest extends CtyunRequest<GetVMsResponse> {
    /**
     * 主机id
     */
    private String vmId;
    /**
     * 资源池id 请根据查询资源池接口返回值进行传参，获取“regionId”参数
     */
    private String regionId;
    /**
     * 查询返回云服务器数量限制。
     */
    private String limit;
    /**
     * 从marker指定的云服务器ID的下一条数据开始查询。
     */
    private String marker;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/ondemand/queryVMs";
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
    public Class<GetVMsResponse> getResponseClass() {
        return GetVMsResponse.class;
    }
}
