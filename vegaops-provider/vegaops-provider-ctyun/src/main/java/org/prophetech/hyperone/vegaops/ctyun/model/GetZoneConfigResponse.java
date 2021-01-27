package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;

@Setter
@Getter
public class GetZoneConfigResponse extends CtyunResponse {
    /**
     * 资源池id
     */
    private String regionId;
    /**
     * 资源池可用区id
     */
    private String zoneId;
    /**
     * 资源池名称
     */
    private String zoneName;
    /**
     * 是否蒙贵 （1蒙贵，2非蒙贵）
     */
    private String zoneType;

    /**
     * 是否可以销售
     */
    private String isSale;
}
