package org.prophetech.hyperone.vegaops.alicloud.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VbrResponse {

    private String localGatewayIp;

    private String peerGatewayIp;

    private String physicalConnectionOwnerUid;

    private String vlanId;

    private String physicalConnectionStatus;

    private String physicalConnectionId;

    private String routeTableId;

    private String peeringSubnetMask;

    private String creationTime;

    private String activationTime;

    private String ipv6CidrBlock;

    private String status;

    private String physicalConnectionBusinessStatus;

    private String accessPointId;

    private String vlanInterfaceId;

    private String vbrId;
}
