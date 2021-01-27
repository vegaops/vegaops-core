package org.prophetech.hyperone.vegaops.tencent.util;

import com.tencentcloudapi.vpc.v20170312.models.CreateSecurityGroupPoliciesRequest;
import com.tencentcloudapi.vpc.v20170312.models.DeleteSecurityGroupPoliciesRequest;
import com.tencentcloudapi.vpc.v20170312.models.SecurityGroupPolicy;
import com.tencentcloudapi.vpc.v20170312.models.SecurityGroupPolicySet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Setter
@Getter
@AllArgsConstructor
public class SecurityGroupRuleBuilder {

    private static final String EGRESS = "egress";
    private static final String INGRESS = "ingress";

    public static CreateSecurityGroupPoliciesRequest buildCreateRequest(String protocol, String portStart, String portEnd,
                                                                        String securityGroupId, String policy, String description,
        String direction,String cidrBlock) {
        CreateSecurityGroupPoliciesRequest request = new CreateSecurityGroupPoliciesRequest();
        request.setSecurityGroupId(securityGroupId);
        SecurityGroupPolicySet policySet = buildRule(protocol, portStart, portEnd, policy, description, direction,cidrBlock);
        request.setSecurityGroupPolicySet(policySet);
        return request;
    }

    public static DeleteSecurityGroupPoliciesRequest buildDeleteRequest(String securityGroupId, String direction, Long index) {
        DeleteSecurityGroupPoliciesRequest request = new DeleteSecurityGroupPoliciesRequest();
        request.setSecurityGroupId(securityGroupId);
        SecurityGroupPolicy rule = new SecurityGroupPolicy();
        rule.setPolicyIndex(index);
        SecurityGroupPolicySet policySet = new SecurityGroupPolicySet();
        if (EGRESS.equalsIgnoreCase(direction)) {
            policySet.setEgress(new SecurityGroupPolicy[]{rule});
        } else if (INGRESS.equalsIgnoreCase(direction)) {
            policySet.setIngress(new SecurityGroupPolicy[]{rule});
        }
        request.setSecurityGroupPolicySet(policySet);
        return request;
    }

    private static SecurityGroupPolicySet buildRule(String protocol, String portStart, String portEnd,
                                                    String policy, String description,
                                                    String direction, String cidrBlock) {
        String port = "";
        if ("all".equalsIgnoreCase(portStart)
                || "all".equalsIgnoreCase(portEnd)
                || (StringUtils.isEmpty(portStart) && StringUtils.isEmpty(portEnd))) {
            port = "ALL";
        }
        if (StringUtils.isEmpty(port)
                && StringUtils.isNotEmpty(portStart)
                && StringUtils.isNotEmpty(portEnd)) {
            if (portStart.equals(portEnd)) {
                // 端口号相同
                port = portStart;
            } else {
                port = portStart + "-" + portEnd;
            }
        }
        SecurityGroupPolicy rule = new SecurityGroupPolicy();
        rule.setProtocol(protocol);
        if (!"ICMP".equalsIgnoreCase(protocol)) {
            rule.setPort(port);
        }
//        rule.setCidrBlock(cidrBlock);
//        rule.setIpv6CidrBlock(ipv6CidrBlock);
//        rule.setSecurityGroupId(securityGroupId);
        rule.setAction(policy);
        rule.setCidrBlock(cidrBlock);
        rule.setPolicyDescription(description);
        SecurityGroupPolicySet policySet = new SecurityGroupPolicySet();
        if (EGRESS.equalsIgnoreCase(direction)) {
            policySet.setEgress(new SecurityGroupPolicy[]{rule});
        } else if (INGRESS.equalsIgnoreCase(direction)) {
            policySet.setIngress(new SecurityGroupPolicy[]{rule});
        }
        return policySet;
    }
}
