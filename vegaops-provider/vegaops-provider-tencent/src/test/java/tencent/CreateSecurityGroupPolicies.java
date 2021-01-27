package tencent;

import com.alibaba.fastjson.JSONObject;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vpc.v20170312.VpcClient;
import com.tencentcloudapi.vpc.v20170312.models.CreateSecurityGroupPoliciesRequest;
import com.tencentcloudapi.vpc.v20170312.models.CreateSecurityGroupPoliciesResponse;
import com.tencentcloudapi.vpc.v20170312.models.SecurityGroupPolicy;
import com.tencentcloudapi.vpc.v20170312.models.SecurityGroupPolicySet;
import org.prophetech.hyperone.vegaops.tencent.util.RequestBuilder;

public class CreateSecurityGroupPolicies
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

            CreateSecurityGroupPoliciesRequest request = new CreateSecurityGroupPoliciesRequest();
            request.setSecurityGroupId("sg-0h86pgnv");
            SecurityGroupPolicySet securityGroupPolicySet = new SecurityGroupPolicySet();
            SecurityGroupPolicy policy = new SecurityGroupPolicy();
            policy.setSecurityGroupId("sg-0h86pgnv");
            policy.setAction("accept");
            SecurityGroupPolicy[] egress = new SecurityGroupPolicy[]{policy};
            securityGroupPolicySet.setEgress(egress);
            request.setSecurityGroupPolicySet(securityGroupPolicySet);
            System.out.println(JSONObject.toJSONString(request));

            request = RequestBuilder.build().json("{\"securityGroupId\":\"sg-0h86pgnv\",\"securityGroupPolicySet\":{\"egress\":[{\"action\":\"accept\",\"securityGroupId\":\"sg-0h86pgnv\"}]}}")
                    .build(CreateSecurityGroupPoliciesRequest.class);

            CreateSecurityGroupPoliciesResponse resp = client.CreateSecurityGroupPolicies(request);

            System.out.println(CreateSecurityGroupPoliciesRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

}
