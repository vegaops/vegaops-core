package org.prophetech.hyperone.vegaops.ctyun.model;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.annotation.IgnoreParam;

@Setter
@Getter
public class CreateSubnetRequest extends CtyunRequest<CreateSubnetResponse> {
    /**
     * 创建子网信息
     */
    private String jsonStr;

    /**
     * 资源池ID,，请根据查询资源池列表接口返回值进行传参，获取“regionId”参数
     */
    @IgnoreParam
    private String regionId;
    /**
     * 区域ID,，请根据查询资源池列表接口返回值进行传参，获取“regionId”参数
     */
    @IgnoreParam
    private String zoneId;
    /**
     * 子网名称
     */
    @IgnoreParam
    private String name;
    /**
     * 子网CIDR地址，取值范围：必须在vpc对应cidr范围内
     */
    @IgnoreParam
    private String cidr;
    /**
     * 网关IP地址
     */
    @IgnoreParam
    private String gatewayIp;
    /**
     * 是否开启自动分配Ip地址
     */
    @IgnoreParam
    private String dhcpEnable;
    /**
     * 虚拟私有云ID
     */
    @IgnoreParam
    private String vpcId;
    /**
     * DNS地址，如果主机需要访问公网就需要填写该值，不填写就不能使用DNS解析
     */
    @IgnoreParam
    private String primaryDns;
    /**
     * DNS地址，如果主机需要访问公网就需要填写该值，不填写就不能使用DNS解析
     */
    @IgnoreParam
    private String secondaryDns;

    @Override
    public void init() {
        jsonStr = JSON.toJSONString(getIgnoreParamMap());
    }

    @Override
    public String getUrl() {
        return "/apiproxy/v3/createSubnet";
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
    public Class<CreateSubnetResponse> getResponseClass() {
        return CreateSubnetResponse.class;
    }
}
