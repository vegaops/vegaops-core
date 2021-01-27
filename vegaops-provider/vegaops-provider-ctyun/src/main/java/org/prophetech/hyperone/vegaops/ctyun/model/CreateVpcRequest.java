package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CreateVpcResponse;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;

@Setter
@Getter
public class CreateVpcRequest extends CtyunRequest<CreateVpcResponse> {
    /**
     * 资源池ID，请根据查询资源池列表接口返回值进行传参，获取“regionId”参数
     */
    private String regionId;
    /**
     * 虚拟私有云名称。取值范围：1-32，支持数字、字母、中文、_(下划线)、-（中划线）。约束：同一个租户下的名称不能重复。
     */
    private String name;
    /**
     * 虚拟私有云下可用子网范围。取值范围：10.0.0.0/8~10.255.255.0/24或者172.16.0.0/12 ~ 172.31.255.0/24或者192.168.0.0/16 ~ 192.168.255.0/24。约束：必须是cidr格式，例如:192.168.0.0/16。
     */
    private String cidr;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/createVPC";
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
        return CreateVpcResponse.class;
    }
}
