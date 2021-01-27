package aws;

import lombok.SneakyThrows;
import org.junit.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeSubnetsRequest;
import software.amazon.awssdk.services.ec2.model.DescribeSubnetsResponse;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;
import software.amazon.awssdk.services.ec2.model.Subnet;

public class SubnetTest {
    @Test
    @SneakyThrows
    public void testSubnet(){
        //shanghai
        //AwsBasicCredentials credentials = AwsBasicCredentials.create(
        //        "XXXX", "uaZqJlEmw3pDGjjCrq8ChAigPN/gasPW0gBkcQ9H");
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");

        Ec2Client ec2 = Ec2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTH_1)
                .build();
        try {
            DescribeSubnetsRequest request = DescribeSubnetsRequest.builder().build();
            DescribeSubnetsResponse describeSubnetsResponse = ec2.describeSubnets(request);
            for(Subnet subnet : describeSubnetsResponse.subnets()) {
                System.out.println();
            }
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }
}
