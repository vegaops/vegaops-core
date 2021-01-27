package aws;

import lombok.SneakyThrows;
import org.junit.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

public class RegionTest {

    @Test
    @SneakyThrows
    public void testRegion() {
        //shanghai
        //AwsBasicCredentials credentials = AwsBasicCredentials.create(
        //        "XXXX", "uaZqJlEmw3pDGjjCrq8ChAigPN/gasPW0gBkcQ9H");
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");

        Ec2Client ec2 = Ec2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(software.amazon.awssdk.regions.Region.CN_NORTH_1)
                .build();
        try {
            DescribeRegionsRequest regionsRequest = DescribeRegionsRequest.builder().allRegions(true).build();
            DescribeRegionsResponse regionsResponse = ec2.describeRegions(regionsRequest);
            for (Region region : regionsResponse.regions()) {
                System.out.printf(
                        "Found region %s " +
                                "with endpoint %s",
                        region.regionName(),
                        region.endpoint());
                System.out.println();
                // snippet-end:[ec2.java2.describe_region_and_zones.region]
            }
            // snippet-start:[ec2.java2.describe_region_and_zones.avail_zone]
            DescribeAvailabilityZonesResponse zonesResponse =
                    ec2.describeAvailabilityZones();

            for (AvailabilityZone zone : zonesResponse.availabilityZones()) {
                System.out.printf(
                        "Found availability zone %s " +
                                "with status %s " +
                                "in region %s",
                        zone.zoneName(),
                        zone.state(),
                        zone.regionName());
                System.out.println();
                // snippet-end:[ec2.java2.describe_region_and_zones.avail_zone]
            }

        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
    }
}