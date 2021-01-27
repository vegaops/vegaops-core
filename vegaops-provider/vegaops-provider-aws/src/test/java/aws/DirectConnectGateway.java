package aws;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.directconnect.DirectConnectClient;
import software.amazon.awssdk.services.directconnect.model.DescribeDirectConnectGatewaysRequest;
import software.amazon.awssdk.services.directconnect.model.DescribeDirectConnectGatewaysResponse;

public class DirectConnectGateway {

    public static void main(String[] args) {
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");
        DescribeDirectConnectGatewaysRequest request =
                DescribeDirectConnectGatewaysRequest.builder().build();
        DescribeDirectConnectGatewaysResponse response = DirectConnectClient.builder().
                credentialsProvider(StaticCredentialsProvider.create(credentials)).region(Region.CN_NORTH_1).build()
                .describeDirectConnectGateways(request);
        response.directConnectGateways();
    }
}
