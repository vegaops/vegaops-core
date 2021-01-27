package tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vpc.v20170312.VpcClient;
import com.tencentcloudapi.vpc.v20170312.models.CreateSecurityGroupRequest;
import com.tencentcloudapi.vpc.v20170312.models.CreateSecurityGroupResponse;

public class CreateSecurityGroup {

    public static void main(String[] args) {
        try {
            Credential cred = new Credential("xxxxx",
                    "xxxxx");
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            VpcClient client = new VpcClient(cred, "ap-beijing", clientProfile);
            String params = "{'GroupName':'sg-11','GroupDescription':'test'}";
            CreateSecurityGroupRequest req = com.tencentcloudapi.vpc.v20170312.models.CreateSecurityGroupRequest.fromJsonString("{'GroupName':'sg-11','GroupDescription':'test'}", CreateSecurityGroupRequest.class);
            CreateSecurityGroupResponse resp = client.CreateSecurityGroup(req);
            System.out.println(CreateSecurityGroupRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

}
