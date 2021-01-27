package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;

import java.util.List;

@Setter
@Getter
public class GetSecurityGroupRulesResponse extends CtyunResponse {


    private List<SecurityGroupRules> security_group_rules;

    @Setter
    @Getter
    @NoArgsConstructor
    public static class SecurityGroupRules{
        /**
         * 描述
         */
        private String description;
        /**
         * 安全组id
         */
        private String security_group_id;
        /**
         * IP地址协议类型
         */
        private String ethertype;
        /**
         * 安全组规则id
         */
        private String id;
        /**
         * 远程组id
         */
        private String remote_group_id;
        /**
         * 租户id
         */
        private String tenant_id;
        /**
         * 功能说明：出入控制方向l 取值范围：egress，ingress
         */
        private String direction;
        /**
         * 功能说明：协议类型l 取值范围：icmp、tcp、udp等 l 约束：为空表示支持所有协议
         */
        private String protocol;
        /**
         * 功能说明：起始端口值l 取值范围：1~65535l 约束：不能大于port_range_max的值，为空表示所有端口，如果协议是icmp类型，取值范围请参见A.1 安全组规则icmp协议名称对应关系表。
         */
        private String port_range_min;
        /**
         * 功能说明：结束端口值l 取值范围：1~65535l 约束：协议不为icmp时，取值不能小于port_range_min的值，为空表示所有端口，如果协议是icmp类型，取值范围请参见A.1 安全组规则icmp协议名称对应关系表。
         */
        private String port_range_max;
        /**
         * 功能说明：远端IP地址，当direction是egress时为虚拟机访问端的地址，当direction是ingress时为访问虚拟机的地址l 取值范围：IP地址，或者cidr格 式 l 约束：和remote_group_id互斥
         */
        private String remote_ip_prefix;
        private String ipv6Cidr;
        /**
         * 资源创建时间
         */
        private String created_at;
        /**
         * 资源更新时间
         */
        private String updated_at;
        private String project_id;
    }

}
