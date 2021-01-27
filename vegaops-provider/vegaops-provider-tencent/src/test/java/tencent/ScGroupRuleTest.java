package tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vpc.v20170312.VpcClient;
import com.tencentcloudapi.vpc.v20170312.models.DescribeSecurityGroupPoliciesRequest;
import com.tencentcloudapi.vpc.v20170312.models.DescribeSecurityGroupPoliciesResponse;
import lombok.SneakyThrows;
import org.junit.Test;

public class ScGroupRuleTest {

    @Test
    @SneakyThrows
    public void getScGroupRuleListTest() {
        try{

            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "ap-beijing", clientProfile);

            String params = "{\"SecurityGroupId\":\"sg-0sla001l\"}";
            DescribeSecurityGroupPoliciesRequest req = DescribeSecurityGroupPoliciesRequest.fromJsonString(params, DescribeSecurityGroupPoliciesRequest.class);

            DescribeSecurityGroupPoliciesResponse resp = client.DescribeSecurityGroupPolicies(req);

            System.out.println(DescribeSecurityGroupPoliciesRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }
}