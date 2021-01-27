package aws;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

public class VolumeTest {
    @Test
    @SneakyThrows
    public void describeVolume() {
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
            DescribeVolumesRequest request = DescribeVolumesRequest.builder().build();
            DescribeVolumesResponse describeVolumesResponse = ec2.describeVolumes(request);
//            describeVolumesResponse.volumes().get(0).tags()
            for (Volume volume : describeVolumesResponse.volumes()) {
                System.out.println(JSON.toJSONString(volume.attachments()));
            }
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    @SneakyThrows
    public void createVolume() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");

        Ec2Client ec2 = Ec2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTH_1)
                .build();
        try {
            CreateVolumeRequest request = CreateVolumeRequest.builder()
                    .size(1)
                    .availabilityZone("cn-north-1a")
                    .tagSpecifications(TagSpecification.builder().resourceType(ResourceType.VOLUME).tags(Tag.builder().key("Name").value("name").build()).build()).build();
            CreateVolumeResponse describeVolumesResponse = ec2.createVolume(request);
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    @SneakyThrows
    public void attachVolume() {
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
            AttachVolumeRequest request = AttachVolumeRequest.builder().device("/dev/sdb").instanceId("i-0211a29184a83f72a").volumeId("vol-0873d1b070c894c88").build();
            AttachVolumeResponse attachVolumeResponse = ec2.attachVolume(request);
            System.out.println(attachVolumeResponse);
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    @SneakyThrows
    public void detachVolume() {
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
            DetachVolumeRequest request = DetachVolumeRequest.builder().device("/dev/sdb").instanceId("i-0211a29184a83f72a").volumeId("vol-0873d1b070c894c88").build();
            DetachVolumeResponse detachVolumeResponse = ec2.detachVolume(request);
            System.out.println(detachVolumeResponse);
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }
}
