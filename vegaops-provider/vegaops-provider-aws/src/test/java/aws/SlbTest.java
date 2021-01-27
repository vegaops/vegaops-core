package aws;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.elasticloadbalancing.ElasticLoadBalancingClient;
import software.amazon.awssdk.services.elasticloadbalancing.model.*;

public class SlbTest {

    @Test
    @SneakyThrows
    public void list() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");


        ElasticLoadBalancingClient client = ElasticLoadBalancingClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTH_1)
                .build();

        DescribeLoadBalancersRequest request = DescribeLoadBalancersRequest.builder().loadBalancerNames("vegaopsTest").build();

        DescribeLoadBalancersResponse response = client.describeLoadBalancers(request);
        System.out.println(JSON.toJSONString(response.loadBalancerDescriptions()));
    }

    @Test
    @SneakyThrows
    public void create() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");


        ElasticLoadBalancingClient client = ElasticLoadBalancingClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTH_1)
                .build();

        CreateLoadBalancerRequest request = CreateLoadBalancerRequest.builder()
                .availabilityZones("cn-north-1b")
                .listeners(Listener.builder().instancePort(8080).instanceProtocol("HTTP").loadBalancerPort(80).protocol("HTTP").build())
                .loadBalancerName("vegaopsTest")
//                .scheme()
                .securityGroups()
                .subnets()
//                .tags("")
                .build();

        CreateLoadBalancerResponse response = client.createLoadBalancer(request);


        System.out.println(JSON.toJSONString(response));
    }

    @Test
    @SneakyThrows
    public void delete() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");


        ElasticLoadBalancingClient client = ElasticLoadBalancingClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTH_1)
                .build();

        DeleteLoadBalancerRequest request = DeleteLoadBalancerRequest.builder().loadBalancerName("vegaopsTest").build();

        DeleteLoadBalancerResponse response = client.deleteLoadBalancer(request);
        System.out.println(JSON.toJSONString(response));
    }
}