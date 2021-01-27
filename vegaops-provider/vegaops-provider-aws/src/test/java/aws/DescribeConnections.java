package aws;

import lombok.SneakyThrows;
import org.junit.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.directconnect.DirectConnectClient;
import software.amazon.awssdk.services.directconnect.model.ConfirmConnectionRequest;
import software.amazon.awssdk.services.directconnect.model.ConfirmConnectionResponse;
import software.amazon.awssdk.services.directconnect.model.DescribeConnectionsRequest;
import software.amazon.awssdk.services.directconnect.model.DescribeConnectionsResponse;

public class DescribeConnections {

    public static void main(String[] args) {
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");
        DescribeConnectionsRequest request =
                DescribeConnectionsRequest.builder().build();
        DescribeConnectionsResponse response = DirectConnectClient.builder().credentialsProvider(StaticCredentialsProvider.create(credentials)).region(Region.CN_NORTH_1).build()
                .describeConnections(request);
        response.connections();
    }

    @Test
    @SneakyThrows
    public void confirm(){
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");
        ConfirmConnectionRequest request =
                ConfirmConnectionRequest.builder().connectionId("dxcon-fh45k7c2").build();
        ConfirmConnectionResponse response = DirectConnectClient.builder().credentialsProvider(StaticCredentialsProvider.create(credentials)).region(Region.CN_NORTH_1).build()
                .confirmConnection(request);

    }
}
