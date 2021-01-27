package tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vpc.v20170312.VpcClient;
import com.tencentcloudapi.vpc.v20170312.models.DeleteSecurityGroupRequest;
import com.tencentcloudapi.vpc.v20170312.models.DeleteSecurityGroupResponse;
import org.prophetech.hyperone.vegaops.tencent.util.RequestBuilder;

public class DeleteSecurityGroup
{
    public static void main(String [] args) {
        try{

            Credential cred = new Credential("xxxxx",
                    "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "ap-beijing", clientProfile);

            String params = "{}";
            DeleteSecurityGroupRequest req = DeleteSecurityGroupRequest
                    .fromJsonString(params, DeleteSecurityGroupRequest.class);
            req.setSecurityGroupId("sg-65xlwkd7");

            DeleteSecurityGroupResponse request = RequestBuilder.build().put("SecurityGroupId", "test").build(DeleteSecurityGroupResponse.class);

            DeleteSecurityGroupResponse resp = client.DeleteSecurityGroup(req);

            System.out.println(DeleteSecurityGroupRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

}
