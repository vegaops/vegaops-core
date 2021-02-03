package org.prophetech.hyperone.vegaops.ctyun.model;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.annotation.IgnoreParam;

@Setter
@Getter
public class CreateSecurityGroupRuleRequest extends CtyunRequest<CreateSecurityGroupRuleResponse> {
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
     * 安全组ID
     */
    @IgnoreParam
    private String securityGroupId;
    /**
     * 出入控制方向，取值范围：egress，ingress
     */
    @IgnoreParam
    private String direction;
    /**
     * IP地址协议类型 ，取值范围：IPv4,IPv6
     */
    @IgnoreParam
    private String ethertype;
    /**
     * 1、取值范围：icmp、tcp、udp 等等 2、功能说明：协议类型 3、约束：为空表示支持所有协议
     */
    @IgnoreParam
    private String protocol;
    /**
     * 1、取值范围：-1~65535 2、功能说明：起始端口值 3、约束：不能大于port_range_max的值，为空表示所有端口，如果协议是icmp类型，取值范围参照附录B.2 安全组规则icmp协议名称对应关系表
     */
    @IgnoreParam
    private String portRangeMin;
    /**
     * 1、取值范围：-1~65535 2、功能说明：结束端口值 3、约束：协议不为icmp时，取值不能小于port_range_min的值，为空表示所有端口，如果协议是icmp类型，取值范围参照附录B.2 安全组规则icmp协议名称对应关系表
     */
    @IgnoreParam
    private String portRangeMax;
    /**
     * 1、取值范围：IP地址，或者cidr格式 2、功能说明：远端IP地址，当direction是egress时为虚拟机访问端的地址，当direction是ingress时为访问虚拟机的地址 3、约束：和remote_group_id互斥
     */
    @IgnoreParam
    private String remoteIpPrefix;
    /**
     * 远程组ID
     */
    @IgnoreParam
    private String remoteGroupId;

    @Override
    public void init() {
        jsonStr = JSON.toJSONString(getIgnoreParamMap());
    }

    @Override
    public String getUrl() {
        return "/apiproxy/v3/createSecurityGroupRule";
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
    public Class<CreateSecurityGroupRuleResponse> getResponseClass() {
        return CreateSecurityGroupRuleResponse.class;
    }

}
