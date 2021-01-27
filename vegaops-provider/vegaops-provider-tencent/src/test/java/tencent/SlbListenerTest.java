package tencent;

import com.tencentcloudapi.clb.v20180317.ClbClient;
import com.tencentcloudapi.clb.v20180317.models.DescribeListenersRequest;
import com.tencentcloudapi.clb.v20180317.models.DescribeListenersResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import lombok.SneakyThrows;
import org.junit.Test;

public class SlbListenerTest {

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

            DescribeListenersRequest req = new DescribeListenersRequest();
            req.setLoadBalancerId("lb-o56cui0x");
            DescribeListenersResponse resp = client.DescribeListeners(req);
            System.out.println(DescribeListenersRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

}