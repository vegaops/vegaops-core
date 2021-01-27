package tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vpc.v20170312.VpcClient;
import com.tencentcloudapi.vpc.v20170312.models.*;
import lombok.SneakyThrows;
import org.junit.Test;

public class NetworkCenTest {
    @Test
    @SneakyThrows
    public void getVpcListTest() {
        try {

            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "ap-beijing", clientProfile);

            DescribeCcnsRequest req = new DescribeCcnsRequest();
            Filter filter = new Filter();
            filter.setName("ccn-id");
            filter.setValues(new String[]{"ccn-7nngem1a"});
            req.setFilters(new Filter[]{filter});
            DescribeCcnsResponse resp = client.DescribeCcns(req);
            System.out.println(DescribeCcnsRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

    @Test
    @SneakyThrows
    public void create() {
        try{

            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "ap-beijing", clientProfile);

            CreateCcnRequest req = new CreateCcnRequest();
            req.setBandwidthLimitType("INTER_REGION_LIMIT");
            req.setCcnDescription("vegaops_test_des");
            req.setCcnName("vegaops_test");
            req.setInstanceChargeType("POSTPAID");
            req.setQosLevel("AG");
            CreateCcnResponse resp = client.CreateCcn(req);
            System.out.println(CreateCcnRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    @SneakyThrows
    public void uninstall() {
        try{

            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "ap-beijing", clientProfile);

            DeleteCcnRequest req = new DeleteCcnRequest();
            DeleteCcnResponse resp = client.DeleteCcn(req);
            System.out.println(DeleteCcnRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }
}