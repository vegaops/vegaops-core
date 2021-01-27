package tencent;

import com.tencentcloudapi.clb.v20180317.ClbClient;
import com.tencentcloudapi.clb.v20180317.models.DescribeTargetGroupListRequest;
import com.tencentcloudapi.clb.v20180317.models.DescribeTargetGroupListResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import lombok.SneakyThrows;
import org.junit.Test;

public class SlbServerGroupTest {

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

            DescribeTargetGroupListRequest req = new DescribeTargetGroupListRequest();

            DescribeTargetGroupListResponse resp = client.DescribeTargetGroupList(req);
            System.out.println(DescribeTargetGroupListRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

}