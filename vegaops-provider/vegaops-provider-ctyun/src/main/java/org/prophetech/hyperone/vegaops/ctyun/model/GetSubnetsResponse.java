package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetSubnetsResponse extends CtyunResponse {

    private String resVlanId;
    private String name;
    private String regionId;
    private String vpcId;
    private String zoneId;
    private String zoneName;
    private String gateway;
    private String vlanStatus;
    private String cidr;
    private String firstDcn;
    private String secondDcn;
    private String neutronSubnetId;

}
