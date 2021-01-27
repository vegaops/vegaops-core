package tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.cvm.v20170312.CvmClient;
import com.tencentcloudapi.cvm.v20170312.models.DescribeRegionsRequest;
import com.tencentcloudapi.cvm.v20170312.models.DescribeRegionsResponse;
import com.tencentcloudapi.cvm.v20170312.models.DescribeZonesRequest;
import com.tencentcloudapi.cvm.v20170312.models.DescribeZonesResponse;
import lombok.SneakyThrows;
import org.junit.Test;

public class RegionTest {

    private static String key = "xxxxx";
    private static String secret = "xxxxx";

    @Test
    @SneakyThrows
    public void list() {
        try {

            Credential cred = new Credential(key, secret);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cvm.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CvmClient client = new CvmClient(cred, "", clientProfile);

            DescribeRegionsRequest req = new DescribeRegionsRequest();

            DescribeRegionsResponse resp = client.DescribeRegions(req);

            System.out.println(DescribeRegionsRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    @SneakyThrows
    public void zonelist() {
        try {

            Credential cred = new Credential(key, secret);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cvm.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CvmClient client = new CvmClient(cred, "ap-shanghai", clientProfile);

            DescribeZonesRequest req = new DescribeZonesRequest();

            DescribeZonesResponse resp = client.DescribeZones(req);

            System.out.println(DescribeZonesRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }
}
