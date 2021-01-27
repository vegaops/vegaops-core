package tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.dc.v20180410.DcClient;
import com.tencentcloudapi.dc.v20180410.models.DescribeDirectConnectsRequest;
import com.tencentcloudapi.dc.v20180410.models.DescribeDirectConnectsResponse;

public class DescribeDirectConnects
{
    public static void main(String [] args) {
        try{

            Credential cred = new Credential("", "");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("dc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            DcClient client = new DcClient(cred, "", clientProfile);

            String params = "{}";
            DescribeDirectConnectsRequest req = DescribeDirectConnectsRequest.fromJsonString(params, DescribeDirectConnectsRequest.class);
            DescribeDirectConnectsResponse resp = client.DescribeDirectConnects(req);

            System.out.println(DescribeDirectConnectsRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

}
