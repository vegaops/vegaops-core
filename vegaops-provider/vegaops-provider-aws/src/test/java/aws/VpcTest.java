package aws;

import lombok.SneakyThrows;
import org.junit.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeVpcsRequest;
import software.amazon.awssdk.services.ec2.model.DescribeVpcsResponse;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;

public class VpcTest {

    @Test
    @SneakyThrows
    public void testVpc(){
        //shanghai
        //AwsBasicCredentials credentials = AwsBasicCredentials.create(
        //        "XXXX", "uaZqJlEmw3pDGjjCrq8ChAigPN/gasPW0gBkcQ9H");
        //AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
        //        "oH/PCT/XT+Vh1++xxxxx+zuSQ");
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");
        Ec2Client ec2 = Ec2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTHWEST_1)
                .build();
        try {
            DescribeVpcsRequest request = DescribeVpcsRequest.builder().build();
            DescribeVpcsResponse response = ec2.describeVpcs(request);
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }
}