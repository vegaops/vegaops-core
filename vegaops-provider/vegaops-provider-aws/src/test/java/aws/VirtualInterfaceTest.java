package aws;

import lombok.SneakyThrows;
import org.junit.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.directconnect.DirectConnectClient;
import software.amazon.awssdk.services.directconnect.model.*;

public class VirtualInterfaceTest {

    public static void main(String[] args) {
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");
        DirectConnectClient client = DirectConnectClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTH_1)
                .build();

        CreatePrivateVirtualInterfaceRequest request =
                CreatePrivateVirtualInterfaceRequest.builder()
                        .connectionId("dxcon-fh45k7c2")
                        .newPrivateVirtualInterface(NewPrivateVirtualInterface.builder()
//                                .addressFamily("ipv4")
//                                .amazonAddress("")
                                .asn(1)
//                                .authKey("")
//                                .customerAddress("")
                                .directConnectGatewayId("acf298a3-6b52-4c92-9793-25d3cedcd043")
//                                .mtu(1)
//                                .virtualGatewayId("")
                                .virtualInterfaceName("vegaops_test")
                                .vlan(1)
                        .build())
                        .build();
        CreatePrivateVirtualInterfaceResponse response = client.createPrivateVirtualInterface(request);
    }

    @Test
    @SneakyThrows
    public void query(){
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");
        DescribeVirtualInterfacesRequest request = DescribeVirtualInterfacesRequest.builder().build();
        DescribeVirtualInterfacesResponse response = DirectConnectClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTHWEST_1)
                .build()
                .describeVirtualInterfaces(request);
        response.virtualInterfaces().get(0).addressFamilyAsString();
        response.virtualInterfaces().get(0).amazonAddress();
        response.virtualInterfaces().get(0).asn();
        response.virtualInterfaces().get(0).amazonSideAsn();
        response.virtualInterfaces().get(0).authKey();
        response.virtualInterfaces().get(0).awsDeviceV2();
            response.virtualInterfaces().get(0).bgpPeers();
        response.virtualInterfaces().get(0).connectionId();
        response.virtualInterfaces().get(0).customerAddress();
        response.virtualInterfaces().get(0).customerRouterConfig();
        response.virtualInterfaces().get(0).directConnectGatewayId();
        response.virtualInterfaces().get(0).jumboFrameCapable();
        response.virtualInterfaces().get(0).location();
        response.virtualInterfaces().get(0).mtu();
        response.virtualInterfaces().get(0).region();
            response.virtualInterfaces().get(0).routeFilterPrefixes();
            response.virtualInterfaces().get(0).tags();
        response.virtualInterfaces().get(0).virtualGatewayId();
        response.virtualInterfaces().get(0).virtualInterfaceId();
        response.virtualInterfaces().get(0).virtualInterfaceName();
        response.virtualInterfaces().get(0).virtualInterfaceStateAsString();
        response.virtualInterfaces().get(0).virtualInterfaceType();
        response.virtualInterfaces().get(0).vlan();
    }

    @Test
    @SneakyThrows
    public void delete(){
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");
        DirectConnectClient client = DirectConnectClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTHWEST_1)
                .build();

        DeleteVirtualInterfaceRequest request =
                DeleteVirtualInterfaceRequest.builder().build();
        DeleteVirtualInterfaceResponse response = client.deleteVirtualInterface(request);
    }

    @Test
    @SneakyThrows
    public void associate(){
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");
        DirectConnectClient client = DirectConnectClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTHWEST_1)
                .build();

        AssociateVirtualInterfaceRequest request =
                AssociateVirtualInterfaceRequest.builder().connectionId("").virtualInterfaceId("").build();
        AssociateVirtualInterfaceResponse response = client.associateVirtualInterface(request);
    }
}
