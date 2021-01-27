package tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.cvm.v20170312.CvmClient;
import com.tencentcloudapi.cvm.v20170312.models.DescribeInstanceTypeConfigsRequest;
import com.tencentcloudapi.cvm.v20170312.models.DescribeInstanceTypeConfigsResponse;
import lombok.SneakyThrows;
import org.junit.Test;

public class FlavorTest {

    @Test
    @SneakyThrows
    public void getFlavorListTest() {
        try{

            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cvm.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CvmClient client = new CvmClient(cred, "ap-beijing", clientProfile);

            String params = "{}";
            DescribeInstanceTypeConfigsRequest req = DescribeInstanceTypeConfigsRequest.fromJsonString(params, DescribeInstanceTypeConfigsRequest.class);

            DescribeInstanceTypeConfigsResponse resp = client.DescribeInstanceTypeConfigs(req);

            System.out.println(DescribeInstanceTypeConfigsRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

}