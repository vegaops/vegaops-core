package tencent;

import com.alibaba.fastjson.JSONObject;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vpc.v20170312.VpcClient;
import com.tencentcloudapi.vpc.v20170312.models.DeleteSecurityGroupPoliciesRequest;
import com.tencentcloudapi.vpc.v20170312.models.DeleteSecurityGroupPoliciesResponse;
import com.tencentcloudapi.vpc.v20170312.models.SecurityGroupPolicy;
import com.tencentcloudapi.vpc.v20170312.models.SecurityGroupPolicySet;

public class DeleteSecurityGroupPolicies
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
            DeleteSecurityGroupPoliciesRequest request = DeleteSecurityGroupPoliciesRequest.fromJsonString(params, DeleteSecurityGroupPoliciesRequest.class);
            request.setSecurityGroupId("sg-0h86pgnv");
            SecurityGroupPolicySet securityGroupPolicySet = new SecurityGroupPolicySet();
            SecurityGroupPolicy policy = new SecurityGroupPolicy();
            policy.setSecurityGroupId("sg-0h86pgnv");
            policy.setAction("accept");
            SecurityGroupPolicy[] egress = new SecurityGroupPolicy[]{policy};
            securityGroupPolicySet.setEgress(egress);
            request.setSecurityGroupPolicySet(securityGroupPolicySet);
            System.out.println(JSONObject.toJSONString(request));
            DeleteSecurityGroupPoliciesResponse resp = client.DeleteSecurityGroupPolicies(request);

            System.out.println(DeleteSecurityGroupPoliciesRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

}
