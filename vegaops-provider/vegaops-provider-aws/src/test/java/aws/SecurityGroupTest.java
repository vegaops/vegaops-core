package aws;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

public class SecurityGroupTest {

    @Test
    @SneakyThrows
    public void getInstances() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");

        Ec2Client ec2 = Ec2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTH_1)
                .build();
        try {
            DescribeInstancesRequest request = DescribeInstancesRequest.builder().build();
            DescribeInstancesResponse response = ec2.describeInstances(request);
            JSON.toJSONString(response);
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    @SneakyThrows
    public void createInstance() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");

        Ec2Client ec2 = Ec2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTH_1)
                .build();
        try {
            RunInstancesRequest request = RunInstancesRequest.builder()
//                    .additionalInfo()
//                    .blockDeviceMappings()
//                    .capacityReservationSpecification()
//                    .clientToken()
//                    .cpuOptions(CpuOptionsRequest.builder().coreCount(1).build())
//                    .creditSpecification()
//                    .disableApiTermination()
//                    .ebsOptimized()
//                    .elasticGpuSpecification()
//                    .elasticInferenceAccelerators()
//                    .hibernationOptions()
                    .imageId("ami-010e92a33d9d1fc40")
//                    .instanceInitiatedShutdownBehavior()
//                    .instanceMarketOptions()
                    .instanceType("t2.small")
//                    .ipv6Addresses()
//                    .ipv6AddressCount()
//                    .kernelId("")
//                    .keyName("vegaops")
//                    .launchTemplate()
//                    .licenseSpecifications()
                    .maxCount(1)
//                    .metadataOptions()
                    .minCount(1)
//                    .monitoring()
//                    .networkInterfaces()
                    .placement(Placement.builder().availabilityZone("cn-north-1b").build())
//                    .privateIpAddress("")
//                    .ramdiskId("")
                    .securityGroupIds("sg-dcf871a5")
                        .subnetId("subnet-43be5335")
//                    .tagSpecifications()
//                    .userData()
                    .build();
            RunInstancesResponse response = ec2.runInstances(request);
            JSON.toJSONString(response);
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    @SneakyThrows
    public void deleteInstance() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "oH/PCT/XT+Vh1++xxxxx+zuSQ");

        Ec2Client ec2 = Ec2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTH_1)
                .build();
        try {
            TerminateInstancesRequest request = TerminateInstancesRequest.builder().instanceIds("i-0b3020962df44f395").build();
            TerminateInstancesResponse response = ec2.terminateInstances(request);
            System.out.println(response.toString());
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }
}