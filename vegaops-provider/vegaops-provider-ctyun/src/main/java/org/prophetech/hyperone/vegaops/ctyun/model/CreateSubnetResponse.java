package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;

@Setter
@Getter
public class CreateSubnetResponse extends CtyunResponse {
    /**
     * 子网id
     */
    private String id;
    /**
     * 子网名称
     */
    private String name;
    /**
     * 子网网段
     */
    private String cidr;
    /**
     * 子网是否开启dhcp功能
     */
    private String gateway_ip;
    /**
     * 资源池可用区id
     */
    private String availability_zone;
    /**
     * 子网所在vpcid
     */
    private String vpc_id;
    /**
     * 子网创建状态
     */
    private String status;
    /**
     * DNS地址
     */
    private String primary_dns;
    /**
     * DNS地址
     */
    private String secondary_dns;
}
