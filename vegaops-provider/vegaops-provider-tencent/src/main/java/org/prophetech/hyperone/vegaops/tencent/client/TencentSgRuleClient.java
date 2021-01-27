package org.prophetech.hyperone.vegaops.tencent.client;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vpc.v20170312.VpcClient;
import com.tencentcloudapi.vpc.v20170312.models.DeleteSecurityGroupPoliciesRequest;
import com.tencentcloudapi.vpc.v20170312.models.DeleteSecurityGroupPoliciesResponse;
import com.tencentcloudapi.vpc.v20170312.models.SecurityGroupPolicy;
import com.tencentcloudapi.vpc.v20170312.models.SecurityGroupPolicySet;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.prophetech.hyperone.vegaops.tencent.model.DeleteSgRuleParam;

@Getter
@Setter
@Slf4j(topic = "vegaops")
public class TencentSgRuleClient {

    private static final String EGRESS = "egress";
    private static final String INGRESS = "ingress";

    public void deleteSgRule(DeleteSgRuleParam param) {
        try {
            Credential cred = new Credential(param.getAccessKey(), param.getSecret());

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, param.getRegionId(), clientProfile);
            SecurityGroupPolicy rule = new SecurityGroupPolicy();
            rule.setAction(param.getAction());
            if(StringUtils.isNotEmpty(param.getCidrBlock())){
                rule.setCidrBlock(param.getCidrBlock());
            }
            if(StringUtils.isNotEmpty(param.getIpv6CidrBlock())){
                rule.setIpv6CidrBlock(param.getIpv6CidrBlock());
            }
            rule.setPort(param.getPort());
            rule.setProtocol(param.getProtocol());
            SecurityGroupPolicySet policySet = new SecurityGroupPolicySet();
            if (EGRESS.equalsIgnoreCase(param.getDirection())) {
                policySet.setEgress(new SecurityGroupPolicy[]{rule});
            } else if (INGRESS.equalsIgnoreCase(param.getDirection())) {
                policySet.setIngress(new SecurityGroupPolicy[]{rule});
            }

            DeleteSecurityGroupPoliciesRequest req = new DeleteSecurityGroupPoliciesRequest();
            req.setSecurityGroupId(param.getSecurityGroupId());
            req.setSecurityGroupPolicySet(policySet);
            DeleteSecurityGroupPoliciesResponse resp = client.DeleteSecurityGroupPolicies(req);
        } catch (TencentCloudSDKException e) {
            log.error("腾讯云接口请求失败{}", e);
            throw new RuntimeException("删除规则失败" + e);
        }
    }
}

