package aws;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

public class KeypairTest {

    @Test
    @SneakyThrows
    public void create() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");

        Ec2Client ec2 = Ec2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTH_1)
                .build();
        try {
            CreateKeyPairRequest request = CreateKeyPairRequest.builder().keyName("vegaopsTest").build();
            CreateKeyPairResponse response = ec2.createKeyPair(request);
            JSON.toJSONString(response);
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    @SneakyThrows
    public void list() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");

        Ec2Client ec2 = Ec2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTH_1)
                .build();
        try {
            DescribeKeyPairsRequest request = DescribeKeyPairsRequest.builder().build();
            DescribeKeyPairsResponse response = ec2.describeKeyPairs(request);
            JSON.toJSONString(response);
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }

    public void delete(String name) {
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");

        Ec2Client ec2 = Ec2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTH_1)
                .build();
        try {
            DeleteKeyPairRequest request = DeleteKeyPairRequest.builder().keyName(name).build();
            DeleteKeyPairResponse response = ec2.deleteKeyPair(request);
            JSON.toJSONString(response);
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }
}