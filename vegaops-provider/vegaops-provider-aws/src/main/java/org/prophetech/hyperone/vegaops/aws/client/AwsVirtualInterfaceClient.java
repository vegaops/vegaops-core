package org.prophetech.hyperone.vegaops.aws.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.prophetech.hyperone.vegaops.aws.model.CreateVirtualInterfaceParam;
import org.prophetech.hyperone.vegaops.aws.model.GetVirtualInterfaceParam;
import software.amazon.awssdk.services.directconnect.DirectConnectClient;
import software.amazon.awssdk.services.directconnect.model.*;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;

@Slf4j(topic = "vegaops")
public class AwsVirtualInterfaceClient {

    private String PRIVATE = "private";
    private String PUBLIC = "public";
    private String TRANSIT = "transit";

    public GetVirtualInterfaceParam createVirtualInterface(DirectConnectClient client, CreateVirtualInterfaceParam param) {
        if (StringUtils.isBlank(param.getType())) {
            throw new RuntimeException("virtualInterface.type.can.not.be.null");
        }
        GetVirtualInterfaceParam result;
        if (PRIVATE.equals(param.getType())) {
            result = createPrivateVirtualInterface(client, param);
        } else if (PUBLIC.equals(param.getType())) {
            result = createPublicVirtualInterface(client, param);
        } else if (TRANSIT.equals(param.getType())) {
            result = createTransitVirtualInterface(client, param);
        } else {
            throw new RuntimeException("virtualInterface.type.error");
        }
        return result;
    }

    private GetVirtualInterfaceParam createPrivateVirtualInterface(DirectConnectClient client, CreateVirtualInterfaceParam param) {
        try {
            CreatePrivateVirtualInterfaceRequest request =
                    CreatePrivateVirtualInterfaceRequest.builder()
                            .connectionId(param.getConnectId())
                            .newPrivateVirtualInterface(NewPrivateVirtualInterface.builder()
                                    .addressFamily(param.getAddressFamily())
                                    .amazonAddress(param.getAddress())
                                    .asn(param.getAsn())
                                    .authKey(param.getAuthKey())
                                    .customerAddress(param.getCustomerAddress())
                                    .directConnectGatewayId(param.getDirectConnectGatewayId())
                                    .mtu(param.getMtu())
                                    .virtualGatewayId(param.getVirtualGatewayId())
                                    .virtualInterfaceName(param.getName())
                                    .vlan(param.getVlan())
                                    .build())
                            .build();
            CreatePrivateVirtualInterfaceResponse response = client.createPrivateVirtualInterface(request);
            GetVirtualInterfaceParam result = new GetVirtualInterfaceParam();
            result.setAddressFamilyAsString(response.addressFamilyAsString());
            result.setAmazonAddress(response.amazonAddress());
            result.setAsn(response.asn());
            result.setAmazonSideAsn(response.amazonSideAsn());
            result.setAuthKey(response.authKey());
            result.setAwsDeviceV2(response.awsDeviceV2());
            result.setBgpPeers(response.bgpPeers());
            result.setConnectionId(response.connectionId());
            result.setCustomerAddress(response.customerAddress());
            result.setCustomerRouterConfig(response.customerRouterConfig());
            result.setDirectConnectGatewayId(response.directConnectGatewayId());
            result.setJumboFrameCapable(response.jumboFrameCapable());
            result.setLocation(response.location());
            result.setMtu(response.mtu());
            result.setRouteFilterPrefixes(response.routeFilterPrefixes());
            result.setTags(response.tags());
            result.setVirtualGatewayId(response.virtualGatewayId());
            result.setVirtualInterfaceId(response.virtualInterfaceId());
            result.setVirtualInterfaceName(response.virtualInterfaceName());
            result.setVirtualInterfaceStateAsString(response.virtualInterfaceStateAsString());
            result.setVirtualInterfaceType(response.virtualInterfaceType());
            result.setVlan(response.vlan());
            return result;
        } catch (Ec2Exception e) {
            log.error("创建AWS private虚拟接口出错", e);
            throw new RuntimeException("创建AWS虚拟接口出错");
        }
    }

    private GetVirtualInterfaceParam createPublicVirtualInterface(DirectConnectClient client, CreateVirtualInterfaceParam param) {
        try {
            CreatePublicVirtualInterfaceRequest request =
                    CreatePublicVirtualInterfaceRequest.builder()
                            .connectionId(param.getConnectId())
                            .newPublicVirtualInterface(NewPublicVirtualInterface.builder()
                                    .addressFamily(param.getAddressFamily())
                                    .amazonAddress(param.getAddress())
                                    .asn(param.getAsn())
                                    .authKey(param.getAuthKey())
                                    .customerAddress(param.getCustomerAddress())
                                    .routeFilterPrefixes(RouteFilterPrefix.builder().cidr(param.getCidr()).build())
                                    .virtualInterfaceName(param.getName())
                                    .vlan(param.getVlan())
                                    .build())
                            .build();
            CreatePublicVirtualInterfaceResponse response = client.createPublicVirtualInterface(request);
            GetVirtualInterfaceParam result = new GetVirtualInterfaceParam();
            result.setAddressFamilyAsString(response.addressFamilyAsString());
            result.setAmazonAddress(response.amazonAddress());
            result.setAsn(response.asn());
            result.setAmazonSideAsn(response.amazonSideAsn());
            result.setAuthKey(response.authKey());
            result.setAwsDeviceV2(response.awsDeviceV2());
            result.setBgpPeers(response.bgpPeers());
            result.setConnectionId(response.connectionId());
            result.setCustomerAddress(response.customerAddress());
            result.setCustomerRouterConfig(response.customerRouterConfig());
            result.setDirectConnectGatewayId(response.directConnectGatewayId());
            result.setJumboFrameCapable(response.jumboFrameCapable());
            result.setLocation(response.location());
            result.setMtu(response.mtu());
            result.setRouteFilterPrefixes(response.routeFilterPrefixes());
            result.setTags(response.tags());
            result.setVirtualGatewayId(response.virtualGatewayId());
            result.setVirtualInterfaceId(response.virtualInterfaceId());
            result.setVirtualInterfaceName(response.virtualInterfaceName());
            result.setVirtualInterfaceStateAsString(response.virtualInterfaceStateAsString());
            result.setVirtualInterfaceType(response.virtualInterfaceType());
            result.setVlan(response.vlan());
            return result;
        } catch (Ec2Exception e) {
            log.error("创建AWS public虚拟接口出错", e);
            throw new RuntimeException("创建AWS虚拟接口出错");
        }
    }

    private GetVirtualInterfaceParam createTransitVirtualInterface(DirectConnectClient client, CreateVirtualInterfaceParam param) {
        try {
            CreateTransitVirtualInterfaceRequest request =
                    CreateTransitVirtualInterfaceRequest.builder()
                            .connectionId(param.getConnectId())
                            .newTransitVirtualInterface(NewTransitVirtualInterface.builder()
                                    .addressFamily(param.getAddressFamily())
                                    .amazonAddress(param.getAddress())
                                    .asn(param.getAsn())
                                    .authKey(param.getAuthKey())
                                    .customerAddress(param.getCustomerAddress())
                                    .directConnectGatewayId(param.getDirectConnectGatewayId())
                                    .mtu(param.getMtu())
                                    .virtualInterfaceName(param.getName())
                                    .vlan(param.getVlan())
                                    .build())
                            .build();
            CreateTransitVirtualInterfaceResponse response = client.createTransitVirtualInterface(request);
            GetVirtualInterfaceParam result = new GetVirtualInterfaceParam();
            result.setAddressFamilyAsString(response.virtualInterface().addressFamilyAsString());
            result.setAmazonAddress(response.virtualInterface().amazonAddress());
            result.setAsn(response.virtualInterface().asn());
            result.setAmazonSideAsn(response.virtualInterface().amazonSideAsn());
            result.setAuthKey(response.virtualInterface().authKey());
            result.setAwsDeviceV2(response.virtualInterface().awsDeviceV2());
            result.setBgpPeers(response.virtualInterface().bgpPeers());
            result.setConnectionId(response.virtualInterface().connectionId());
            result.setCustomerAddress(response.virtualInterface().customerAddress());
            result.setCustomerRouterConfig(response.virtualInterface().customerRouterConfig());
            result.setDirectConnectGatewayId(response.virtualInterface().directConnectGatewayId());
            result.setJumboFrameCapable(response.virtualInterface().jumboFrameCapable());
            result.setLocation(response.virtualInterface().location());
            result.setMtu(response.virtualInterface().mtu());
            result.setRouteFilterPrefixes(response.virtualInterface().routeFilterPrefixes());
            result.setTags(response.virtualInterface().tags());
            result.setVirtualGatewayId(response.virtualInterface().virtualGatewayId());
            result.setVirtualInterfaceId(response.virtualInterface().virtualInterfaceId());
            result.setVirtualInterfaceName(response.virtualInterface().virtualInterfaceName());
            result.setVirtualInterfaceStateAsString(response.virtualInterface().virtualInterfaceStateAsString());
            result.setVirtualInterfaceType(response.virtualInterface().virtualInterfaceType());
            result.setVlan(response.virtualInterface().vlan());
            return result;
        } catch (Ec2Exception e) {
            log.error("创建AWS transit虚拟接口出错", e);
            throw new RuntimeException("创建AWS虚拟接口出错");
        }
    }
}
