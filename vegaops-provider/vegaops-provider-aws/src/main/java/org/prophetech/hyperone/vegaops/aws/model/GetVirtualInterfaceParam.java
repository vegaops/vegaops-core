package org.prophetech.hyperone.vegaops.aws.model;

import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.services.directconnect.model.BGPPeer;
import software.amazon.awssdk.services.directconnect.model.RouteFilterPrefix;
import software.amazon.awssdk.services.directconnect.model.Tag;

import java.util.List;

@Getter
@Setter
public class GetVirtualInterfaceParam {

    private String addressFamilyAsString;
    private String amazonAddress;
    private Integer asn;
    private Long amazonSideAsn;
    private String authKey;
    private String awsDeviceV2;
    private List<BGPPeer> bgpPeers;
    private String connectionId;
    private String customerAddress;
    private String customerRouterConfig;
    private String directConnectGatewayId;
    private Boolean jumboFrameCapable;
    private String location;
    private Integer mtu;
    private List<RouteFilterPrefix> routeFilterPrefixes;
    private List<Tag> tags;
    private String virtualGatewayId;
    private String virtualInterfaceId;
    private String virtualInterfaceName;
    private String virtualInterfaceStateAsString;
    private String virtualInterfaceType;
    private Integer vlan;


}
