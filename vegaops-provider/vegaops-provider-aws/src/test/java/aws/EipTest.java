package aws;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

public class EipTest {
    @Test
    @SneakyThrows
    public void testVolume(){
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
            DescribeAddressesRequest request = DescribeAddressesRequest.builder().allocationIds().build();
            DescribeAddressesResponse describeAddressesResponse = ec2.describeAddresses(request);
            for(Address address : describeAddressesResponse.addresses()) {
                System.out.println();
            }
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }


    @Test
    @SneakyThrows
    public void associateAddress(){
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
            CreateTagsRequest tagsRequest = CreateTagsRequest.builder().resources("eipalloc-0f0447ea95bb9f891").tags(Tag.builder().key("Name").value("xjhtest").build()).build();
            CreateTagsResponse createAddressResponse = ec2.createTags(tagsRequest);
            System.out.println(JSON.toJSONString(createAddressResponse));
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    @SneakyThrows
    public void createAddress(){
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
            AllocateAddressRequest.Builder builder = AllocateAddressRequest.builder();
            AllocateAddressRequest request = builder.domain("vpc").publicIpv4Pool("amazon").networkBorderGroup("cn-north-1").build();
            AllocateAddressResponse createAddressResponse = ec2.allocateAddress(request);
            System.out.println(JSON.toJSONString(createAddressResponse));
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    @SneakyThrows
    public void describeAddress(){
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
            DescribeAddressesRequest request = DescribeAddressesRequest.builder().build();
            DescribeAddressesResponse associateAddressResponse = ec2.describeAddresses(request);
            System.out.println(JSON.toJSONString(associateAddressResponse));
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    @SneakyThrows
    public void deleteAddress(){
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
            ReleaseAddressRequest request = ReleaseAddressRequest.builder().allocationId("eipalloc-0f0447ea95bb9f891").build();
            ReleaseAddressResponse associateAddressResponse = ec2.releaseAddress(request);
            System.out.println(JSON.toJSONString(associateAddressResponse));
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    @SneakyThrows
    public void disassociateAddress(){
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
            DisassociateAddressRequest request = DisassociateAddressRequest.builder().associationId("eipassoc-0bff06ecdcf67c083").build();
            DisassociateAddressResponse disassociateAddressResponse = ec2.disassociateAddress(request);
            System.out.println(JSON.toJSONString(disassociateAddressResponse));
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }

}
