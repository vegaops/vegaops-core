package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetFlavorsRequest extends CtyunRequest<GetFlavorsResponse> {
    /**
     * 资源池Id，请根据查询资源池列表接口返回值进行传参，获取“regionId”参数
     */
    private String regionId;
    /**
     * CPU核数
     */
    private String cpuNum;
    /**
     * 内存大小。
     */
    private String memSize;
    /**
     * 可用区Id（区分多可用区）。
     */
    private String zoneId;
    /**
     * 主机规格类型。
     */
    private String flavorType;
    /**
     * 规格版本。
     */
    private String version;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/order/getFlavors";
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
    public Class<GetFlavorsResponse> getResponseClass() {
        return GetFlavorsResponse.class;
    }
}
