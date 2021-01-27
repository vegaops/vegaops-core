package tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.cvm.v20170312.CvmClient;
import com.tencentcloudapi.cvm.v20170312.models.DescribeZonesRequest;
import com.tencentcloudapi.cvm.v20170312.models.DescribeZonesResponse;
import lombok.SneakyThrows;
import org.junit.Test;

public class ZoneTest {

    @Test
    @SneakyThrows
    public void getVpcListTest() {
        try {
            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cvm.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CvmClient client = new CvmClient(cred, "ap-beijing", clientProfile);

            String params = "{}";
            DescribeZonesRequest req = DescribeZonesRequest.fromJsonString(params, DescribeZonesRequest.class);

            DescribeZonesResponse resp = client.DescribeZones(req);

            System.out.println(DescribeZonesResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

}