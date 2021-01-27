package aws;

import com.alibaba.fastjson.JSON;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

import java.util.Date;

// snippet-end:[ec2.java2.create_security_group.import]



/**

 * Creates an EC2 security group.

 */

public class CreateSecurityGroup {



    public static void main(String[] args) {




        String groupName = "Hyberbintest " + new Date().getTime();

        String groupDesc = "1111";

        String vpcId = "vpc-a82f88c1";


        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "oH/PCT/XT+Vh1++xxxxx+zuSQ");
        Ec2Client ec2 = Ec2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTHWEST_1)
                .build();


        try {



            // snippet-start:[ec2.java2.create_security_group.create]

            CreateSecurityGroupRequest createRequest = CreateSecurityGroupRequest
                    .builder()
                    .groupName(groupName)
                    .description(groupDesc)
                    .vpcId(vpcId)
                    .build();

            CreateSecurityGroupResponse createResponse =

                    ec2.createSecurityGroup(createRequest);

            // snippet-end:[ec2.java2.create_security_group.create]

            System.out.println("---------------------------");
            System.out.println(JSON.toJSONString(createResponse));
            System.out.println("---------------------------");



            System.out.printf(

                    "Successfully created security group named %s",

                    groupName);



            // snippet-start:[ec2.java2.create_security_group.config]

            IpRange ipRange = IpRange.builder()

                    .cidrIp("0.0.0.0/0").build();



            IpPermission ipPerm = IpPermission.builder()

                    .ipProtocol("tcp")

                    .toPort(80)

                    .fromPort(80)

                    .ipRanges(ipRange)

                    // .ipv4Ranges(ip_range)

                    .build();



            IpPermission ipPerm2 = IpPermission.builder()

                    .ipProtocol("tcp")

                    .toPort(22)

                    .fromPort(22)

                    .ipRanges(ipRange)

                    .build();



            AuthorizeSecurityGroupIngressRequest authRequest =

                    AuthorizeSecurityGroupIngressRequest.builder()

                            .groupName(groupName)

                            .ipPermissions(ipPerm, ipPerm2)

                            .build();



            AuthorizeSecurityGroupIngressResponse authResponse =

                    ec2.authorizeSecurityGroupIngress(authRequest);



            // snippet-end:[ec2.java2.create_security_group.config]

            // snippet-end:[ec2.java2.create_security_group.main]

            System.out.printf(

                    "Successfully added ingress policy to security group %s",

                    groupName);



        } catch (Ec2Exception e) {

            e.getStackTrace();

        }

    }

}


