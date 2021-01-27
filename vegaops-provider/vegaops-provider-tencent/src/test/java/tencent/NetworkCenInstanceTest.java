package tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vpc.v20170312.VpcClient;
import com.tencentcloudapi.vpc.v20170312.models.*;
import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.tencent.client.TencentNetworkCenInstanceClient;

public class NetworkCenInstanceTest {
    @Test
    @SneakyThrows
    public void getListTest() {
        try{

            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "ap-beijing", clientProfile);

            DescribeCcnAttachedInstancesRequest req = new DescribeCcnAttachedInstancesRequest();

            Filter filter = new Filter();
            filter.setName("instance-id");
            filter.setValues(new String[]{"ins-o0z9777v"});
            req.setFilters(new Filter[]{filter});

            DescribeCcnAttachedInstancesResponse resp = client.DescribeCcnAttachedInstances(req);
            System.out.println(DescribeCcnAttachedInstancesRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    @SneakyThrows
    public void attach() {
        try{

            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "ap-beijing", clientProfile);

            AttachCcnInstancesRequest req = new AttachCcnInstancesRequest();
            AttachCcnInstancesResponse resp = client.AttachCcnInstances(req);

            System.out.println(AttachCcnInstancesRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    @SneakyThrows
    public void detach() {
        try{

            Credential cred = new Credential("xxxxx", "xxxxx");
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "ap-beijing", clientProfile);

            DetachCcnInstancesRequest req = new DetachCcnInstancesRequest();
            DetachCcnInstancesResponse resp = client.DetachCcnInstances(req);

            System.out.println(DetachCcnInstancesRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    @SneakyThrows
    public void attachClient() {
        TencentNetworkCenInstanceClient client = new TencentNetworkCenInstanceClient();
        client.setKey("xxxxx");
        client.setSecret("xxxxx");
//        client.attachedInstance("vpc-1f0ziyua", "ccn-7nngem1z", "ap-beijing", "VPC", "ap-beijing");
    }

    @Test
    @SneakyThrows
    public void detachClient() {
        TencentNetworkCenInstanceClient client = new TencentNetworkCenInstanceClient();
        client.setKey("xxxxx");
        client.setSecret("xxxxx");
        client.detachInstance("vpc-1f0ziyua", "ccn-7nngem1z", "ap-beijing", "VPC", "ap-beijing");
    }

}