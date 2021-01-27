package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;

@Setter
@Getter
public class GetSecurityGroupsResponse extends CtyunResponse {
    /**
     * 安全组id
     */
    private String resSecurityGroupId;
    /**
     * 安全组名称
     */
    private String name;
    /**
     * 资源池id
     */
    private String regionId;
    /**
     * 资源池可用区
     */
    private String zoneId;
    /**
     * 资源池名称
     */
    private String zoneName;
    /**
     * 安全组对应的vpcid
     */
    private String vpcId;
}
