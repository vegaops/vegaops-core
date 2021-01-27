package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNetworkCardsParam {

    private String regionId;

    private String instanceId;

    private String securityGroups;

    private String vswitchId;
}
