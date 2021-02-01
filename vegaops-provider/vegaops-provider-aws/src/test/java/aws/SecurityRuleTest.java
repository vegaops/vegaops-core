package aws;

import lombok.SneakyThrows;
import org.junit.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

public class SecurityRuleTest {

    @Test
    @SneakyThrows
    public void testCreateSecurityRule(){
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "oH/PCT/XT+Vh1++xxxxx+zuSQ");

        Ec2Client ec2 = Ec2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTHWEST_1)
                .build();
        try {
            IpPermission permission = IpPermission.builder().fromPort(3).toPort(5).ipProtocol("tcp").ipRanges(IpRange.builder().build()).build();
            AuthorizeSecurityGroupIngressRequest requestIngress = AuthorizeSecurityGroupIngressRequest.builder().groupId("sg-0c667bae465280144")
                    .ipPermissions(permission)
                    .build();
            AuthorizeSecurityGroupEgressRequest requestEgress = AuthorizeSecurityGroupEgressRequest.builder().groupId("sg-0c667bae465280144")
                    .ipPermissions(permission)
                    .build();
            AuthorizeSecurityGroupIngressResponse authorizeSecurityGroupIngressResponse = ec2.authorizeSecurityGroupIngress(requestIngress);
            AuthorizeSecurityGroupEgressResponse authorizeSecurityGroupEgressResponse = ec2.authorizeSecurityGroupEgress(requestEgress);
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    @SneakyThrows
    public void testListSecurityRule(){
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");

        Ec2Client ec2 = Ec2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTH_1)
                .build();
        try {

            DescribeSecurityGroupsRequest request =
                    DescribeSecurityGroupsRequest.builder()
                            .groupIds("sg-02410fd6da6d12dbc").build();
            DescribeSecurityGroupsResponse response = ec2.describeSecurityGroups(request);
            response.securityGroups().get(0).ipPermissions();
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    @SneakyThrows
    public void CreatetSecurityRule(){
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");

        Ec2Client ec2 = Ec2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTH_1)
                .build();
        try {
            AuthorizeSecurityGroupIngressRequest authRequest =
                    AuthorizeSecurityGroupIngressRequest.builder()
                            .groupId("sg-033ff79840ab7bfd2")
                            .fromPort(2)
                            .toPort(65535)
                            .ipProtocol("tcp")
                            .build();
            AuthorizeSecurityGroupIngressResponse authResponse = ec2.authorizeSecurityGroupIngress(authRequest);
            System.out.println(authResponse.sdkHttpResponse().isSuccessful());
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    @SneakyThrows
    public void testDeleteSecurityRule(){
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");

        Ec2Client ec2 = Ec2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTH_1)
                .build();
        try {
            IpPermission permission = IpPermission.builder().ipProtocol("-1").toPort(-1).fromPort(-1).ipRanges(IpRange.builder().cidrIp("0.0.0.0/0").build()).build();
            RevokeSecurityGroupEgressRequest egressRequest = RevokeSecurityGroupEgressRequest.builder().ipPermissions(permission).groupId("sg-01c39e90b329a421f").build();
//            RevokeSecurityGroupIngressRequest ingressRequest = RevokeSecurityGroupIngressRequest.builder().groupId("sg-01c39e90b329a421f").cidrIp("0.0.0.0/0").ipProtocol("tcp").fromPort(80).toPort(80).build();
//            ec2.revokeSecurityGroupIngress(ingressRequest);
            ec2.revokeSecurityGroupEgress(egressRequest);
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }
}
