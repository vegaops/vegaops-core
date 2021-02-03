package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuerySecurityGroupDetailResponse extends CtyunResponse {
    /**
     * 安全组id
     */
    private String resSecurityGroupId;
    /**
     * 安全组名称
     */
    private String name;
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
}
