package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;

@Setter
@Getter
public class QueryVPCDetailResponse extends CtyunResponse {
    /**
     * VPCId
     */
    private String resVpcId;
    /**
     * vpc名称
     */
    private String name;
    /**
     * 可用子网范围
     */
    private String cidr;
    /**
     * vpc状态
     */
    private String vpcStatus;
    /**
     * 资源池Id
     */
    private String regionId;
    /**
     * 可用区Id
     */
    private String zoneId;
    /**
     * 资源池名称
     */
    private String zoneName;
    /**
     * 创建时间
     */
    private Long createDate;
}
