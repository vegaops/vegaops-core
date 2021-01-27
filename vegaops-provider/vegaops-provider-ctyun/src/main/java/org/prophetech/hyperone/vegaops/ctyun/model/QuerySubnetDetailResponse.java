package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;

@Setter
@Getter
public class QuerySubnetDetailResponse extends CtyunResponse {
    /**
     * 子网id
     */
    private String resVlanId;
    /**
     * 子网名称
     */
    private String name;
    /**
     * 资源池id
     */
    private String regionId;
    /**
     * 子网对应的vpcid
     */
    private String vpcId;
    /**
     * 资源池可用区id
     */
    private String zoneId;
    /**
     * 资源池名称
     */
    private String zoneName;
    /**
     * 网关
     */
    private String gateway;
    /**
     * vlan状态
     */
    private String vlanStatus;
    /**
     * 子网网段
     */
    private String cidr;
    /**
     * dns地址
     */
    private String firstDcn;
    /**
     * dns地址
     */
    private String secondDcn;
}
