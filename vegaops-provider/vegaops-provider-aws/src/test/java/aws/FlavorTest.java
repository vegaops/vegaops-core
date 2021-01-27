package aws;

import lombok.SneakyThrows;
import org.junit.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeInstanceTypesRequest;
import software.amazon.awssdk.services.ec2.model.DescribeInstanceTypesResponse;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;
import software.amazon.awssdk.services.ec2.model.InstanceTypeInfo;

import java.util.ArrayList;
import java.util.List;

public class FlavorTest {

    @Test
    @SneakyThrows
    public void testFlavor() {
        //shanghai
        //AwsBasicCredentials credentials = AwsBasicCredentials.create(
        //        "XXXX", "uaZqJlEmw3pDGjjCrq8ChAigPN/gasPW0gBkcQ9H");
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "oH/PCT/XT+Vh1++xxxxx+zuSQ");

        Ec2Client ec2 = Ec2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(software.amazon.awssdk.regions.Region.CN_NORTH_1)
                .build();
        try {
            DescribeInstanceTypesRequest.Builder builder = DescribeInstanceTypesRequest.builder();
            DescribeInstanceTypesRequest request = builder.build();
            DescribeInstanceTypesResponse response;
            List<InstanceTypeInfo> instanceTypeInfos =new ArrayList<>();
            do{
                response = ec2.describeInstanceTypes(request);
                instanceTypeInfos.addAll(response.instanceTypes());
                if(response.nextToken()!=null){
                    request=builder.nextToken(response.nextToken()).build();
                }
            }while (response.nextToken()!=null);
            System.out.println(response);

        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    @SneakyThrows
    public void queryFlavor() {
        //shanghai
        //AwsBasicCredentials credentials = AwsBasicCredentials.create(
        //        "XXXX", "uaZqJlEmw3pDGjjCrq8ChAigPN/gasPW0gBkcQ9H");
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");

        String instanceType = "t2.xlarge";

        Ec2Client ec2 = Ec2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(software.amazon.awssdk.regions.Region.CN_NORTH_1)
                .build();
        try {
            DescribeInstanceTypesRequest.Builder builder = DescribeInstanceTypesRequest.builder();
//            DescribeInstanceTypesRequest request = builder.filters(Filter.builder().name("network-info.ena-support").values("supported").build()).build();
            DescribeInstanceTypesRequest request = builder.build();
            DescribeInstanceTypesResponse response;
            List<InstanceTypeInfo> instanceTypeInfos =new ArrayList<>();
            do{
                response = ec2.describeInstanceTypes(request);
                instanceTypeInfos.addAll(response.instanceTypes());
                if(response.nextToken()!=null){
                    request=builder.nextToken(response.nextToken()).build();
                }
            }while (response.nextToken()!=null);
            System.out.println(response);

        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }
}