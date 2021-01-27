package tencent;

import com.tencentcloudapi.clb.v20180317.ClbClient;
import com.tencentcloudapi.clb.v20180317.models.*;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import lombok.SneakyThrows;
import org.junit.Test;

public class SlbTest {

    private static String key = "xxxxx";
    private static String secret = "xxxxx";

    @Test
    @SneakyThrows
    public void list() {
        try {

            Credential cred = new Credential(key, secret);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("clb.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            ClbClient client = new ClbClient(cred, "ap-beijing", clientProfile);

            DescribeLoadBalancersRequest req = new DescribeLoadBalancersRequest();
            DescribeLoadBalancersResponse resp = client.DescribeLoadBalancers(req);
            LoadBalancer a = resp.getLoadBalancerSet()[0];
            a.getNetworkAttributes().getInternetChargeType();
            System.out.println(DescribeLoadBalancersRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    @SneakyThrows
    public void create() {
        try{

            Credential cred = new Credential(key, secret);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("clb.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            ClbClient client = new ClbClient(cred, "ap-beijing", clientProfile);

            CreateLoadBalancerRequest req = new CreateLoadBalancerRequest();
            req.setAddressIPVersion("IPV4");
            req.setForward(1L);
            InternetAccessible internetAccessible = new InternetAccessible();
//            internetAccessible.setBandwidthpkgSubType();
            internetAccessible.setInternetChargeType("TRAFFIC_POSTPAID_BY_HOUR");
//            internetAccessible.setInternetMaxBandwidthOut();
            req.setInternetAccessible(internetAccessible);
            req.setLoadBalancerName("fty_test");
            req.setLoadBalancerType("OPEN");
//            req.setMasterZoneId("ap-beijing-1");
            req.setNumber(1L);
//            req.setProjectId();
//            req.setSubnetId("subnet-rp6fc62d");
//            req.setTags();
//            req.setVipIsp();
//            req.setVpcId("vpc-hdlbw0iq");
//            req.setZoneId("ap-beijing-1");

            CreateLoadBalancerResponse resp = client.CreateLoadBalancer(req);

            System.out.println(CreateLoadBalancerRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    @SneakyThrows
    public void uninstall() {
        try{

            Credential cred = new Credential(key, secret);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("clb.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            ClbClient client = new ClbClient(cred, "ap-beijing", clientProfile);

            DeleteLoadBalancerRequest req = new DeleteLoadBalancerRequest();

            DeleteLoadBalancerResponse resp = client.DeleteLoadBalancer(req);

            System.out.println(DeleteLoadBalancerRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }
}