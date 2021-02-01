package aws;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.jsoup.Connection;
import org.junit.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.CreateRoleRequest;
import software.amazon.awssdk.services.iam.model.CreateRoleResponse;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest;
import software.amazon.awssdk.services.sts.model.AssumeRoleResponse;
import software.amazon.awssdk.services.sts.model.StsException;

import java.util.*;

public class InstanceTest {

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
//                    .placement(Placement.builder().availabilityZone("cn-north-1b").build())
//                    .privateIpAddress("")
//                    .ramdiskId("")
                    .securityGroupIds("sg-0c8417344d95ad973")
                    .subnetId("subnet-07f6d447abaf096e9")
                    .tagSpecifications(TagSpecification.builder().resourceType(ResourceType.INSTANCE).tags(Tag.builder().key("Name").value("fty_test").build()).build())
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

    @Test
    @SneakyThrows
    public void startInstance() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "oH/PCT/XT+Vh1++xxxxx+zuSQ");

        Ec2Client ec2 = Ec2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTH_1)
                .build();
        try {
            StartInstancesRequest request = StartInstancesRequest.builder().instanceIds("i-0b3020962df44f395").build();
            StartInstancesResponse response = ec2.startInstances(request);
            System.out.println(response.toString());
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    @SneakyThrows
    public void stopInstance() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "oH/PCT/XT+Vh1++xxxxx+zuSQ");

        Ec2Client ec2 = Ec2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTH_1)
                .build();
        try {
            StopInstancesRequest request = StopInstancesRequest.builder().instanceIds("i-0b3020962df44f395").build();
            StopInstancesResponse response = ec2.stopInstances(request);
            System.out.println(response.toString());
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * 创建角色
     */
    @Test
    @SneakyThrows
    public void createRole() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx", "xxxxx");
        IamClient client = IamClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTH_1)
                .build();

        CreateRoleRequest request = CreateRoleRequest.builder()
                .path("/application_abc/component_xyz/")
                .roleName("zhangdeshuai")
                .assumeRolePolicyDocument("{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"Service\":[\"iam.amazonaws.com\"]},\"Action\":[\"sts:AssumeRole\"]}]}")
                .build();

        try {
            CreateRoleResponse response = client.createRole(request);
            System.out.println(response.toString());
        } catch (StsException e) {
            throw new RuntimeException("AWS创建角色失败", e);
        } finally {
        }
    }



    @Test
    @SneakyThrows
    public void login() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx", "xxxxx");
        IamClient client = IamClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTH_1)
                .build();

        CreateRoleRequest request = CreateRoleRequest.builder()
                .path("/application_abc/component_xyz/")
                .roleName("zhangdeshuai")
                .assumeRolePolicyDocument("{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"Service\":[\"iam.amazonaws.com\"]},\"Action\":[\"sts:AssumeRole\"]}]}")
                .build();

        try {
            CreateRoleResponse response = client.createRole(request);
            System.out.println(response.toString());
        } catch (StsException e) {
            throw new RuntimeException("AWS创建角色失败", e);
        } finally {
        }
    }


    /**
     * 角色扮演
     */
    @Test
    @SneakyThrows
    public void assumeRole() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx", "xxxxx");
        StsClient client = StsClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTH_1)
                .build();
        AssumeRoleRequest request = AssumeRoleRequest.builder()
                // 角色会话的持续时间（以秒为单位）。 该值的范围从900秒（15分钟）到角色的最大会话持续时间设置。 此设置的值可以在1小时到12小时之间。 如果指定的值大于此设置，则操作将失败。默认情况下，该值设置为3600秒。有效范围:最小值900。最大值为43200。
//                .durationSeconds(3600)
                // 在另一个帐户中担任角色时可能需要的唯一标识符。 如果角色所属帐户的管理员为您提供了外部ID，请在ExternalId参数中提供该值。 该值可以是任何字符串，例如密码或帐号。 通常会设置跨帐户角色，以信任帐户中的每个人。 因此，信任帐户的管理员可能会向信任帐户的管理员发送外部ID。 这样，只有具有ID的人才能担任该角色，而不是帐户中的每个人。长度约束:最小长度为2。最大长度1224。
//                .externalId("")
                // 想要用作内联会话策略的JSON格式的IAM策略。此参数是可选的。将策略传递给此操作将返回新的临时凭据。生成的会话权限是角色的基于身份的策略和会话策略的交集。您可以在后续的AWS API调用中使用角色的临时凭证来访问拥有该角色的帐户中的资源。您不能使用会话策略授予比所假定角色的基于身份的策略所允许的权限更多的权限。
//                .policy("")
                // 您希望用作托管会话策略的IAM管理策略的Amazon资源名。策略必须存在于与角色相同的帐户中。此参数是可选的。您可以提供多达10个托管策略arn。但是，内联和托管会话策略使用的纯文本不能超过2048个字符。
//                .policyArns(PolicyDescriptorType.builder().arn("").build())
                // Required: Yes 要承担的角色的Amazon资源名(ARN)。
                .roleArn("arn:aws-cn:iam::932563355466:role/application_abc/component_xyz/zhangdeshuai")
                // Required: Yes 假设角色会话的标识符。当不同主体或出于不同原因假定相同的角色时，使用角色会话名称来惟一地标识会话。在跨帐户场景中，角色会话名对拥有该角色的帐户可见，并且可以由该帐户记录。角色会话名也在假定的角色主体的ARN中使用。这意味着后续使用临时安全凭据的跨帐户API请求将在其AWS CloudTrail日志中向外部帐户公开角色会话名称。
                .roleSessionName("oneProTest")
                // 与进行AssumeRole呼叫的用户相关联的MFA设备的标识号。 如果假定角色的信任策略包括要求MFA身份验证的条件，请指定此值。 该值可以是硬件设备（例如GAHT12345678）的序列号，也可以是虚拟设备（例如arn：aws：iam :: 123456789012：mfa / user）的Amazon资源名称（ARN）。
//                .serialNumber("")
                // 如果所假定的角色的信任策略需要MFA(即，如果策略包含测试MFA的条件)，则MFA设备提供的值。如果所假定的角色需要MFA，并且TokenCode值丢失或过期，则假设调用返回一个“拒绝访问”错误。此参数的格式（如其正则表达式模式所描述）是六个数字的序列。
//                .tokenCode("")
                // 要设置为可传递的会话标签的键列表。 如果将标记键设置为可传递键，则相应的键和值将传递到角色链中的后续会话。
//                .transitiveTagKeys("")
                .build();

        try {
            AssumeRoleResponse response = client.assumeRole(request);
            System.out.println(response.toString());
        } catch (StsException e) {
            throw new RuntimeException("AWS授权客户角色过程失败", e);
        } finally {
        }
    }



    @Test
    @SneakyThrows
    public void demo(){
        String[] arr = new String[]{"shuai","da"};
        List<String> list = Arrays.asList(arr);
        Set<String> set = new HashSet(list);
        Map<String, String> demoMap = new HashMap<>();
        demoMap.put("xjh","shuai");
        demoMap.put("xjhPro","da");
        for(Map.Entry<String, String> entry:demoMap.entrySet()){
            if(set.contains(entry.getValue())){
                System.out.println(entry.getValue());
            }
        }
    }


}