package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetVMOrdersRequest extends CtyunRequest<GetVMOrdersResponse> {
    /**
     * 主机id
     */
    private String pageNo;
    /**
     * 资源池id 请根据查询资源池接口返回值进行传参，获取“regionId”参数
     */
    private String regionId;
    /**
     * 查询返回云服务器数量限制。
     */
    private String pageSize;
    /**
     * 从marker指定的云服务器ID的下一条数据开始查询。
     */
    private String available;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/queryVMs";
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
    public Class<GetVMOrdersResponse> getResponseClass() {
        return GetVMOrdersResponse.class;
    }
}
