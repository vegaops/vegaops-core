package aws;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeSecurityGroupsRequest;
import software.amazon.awssdk.services.ec2.model.DescribeSecurityGroupsResponse;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;
import software.amazon.awssdk.services.ec2.model.SecurityGroup;
// snippet-end:[ec2.java2.describe_security_groups.import]

/**
 * Describes all security groups
 */
public class DescribeSecurityGroups {

    public static void main(String[] args) {

        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");
        Ec2Client ec2 = Ec2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTH_1)
                .build();

        try {

            DescribeSecurityGroupsRequest request =
                    DescribeSecurityGroupsRequest.builder()
                            .build();
//                            .groupIds(groupId).build();

            DescribeSecurityGroupsResponse response =
                    ec2.describeSecurityGroups(request);


            // snippet-end:[ec2.java2.describe_security_groups.main]
            for(SecurityGroup group : response.securityGroups()) {
                System.out.printf(
                        "Found security group with id %s, " +
                                "vpc id %s " +
                                "and description %s",
                        group.groupId(),
                        group.vpcId(),
                        group.description());
            }
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }
}
