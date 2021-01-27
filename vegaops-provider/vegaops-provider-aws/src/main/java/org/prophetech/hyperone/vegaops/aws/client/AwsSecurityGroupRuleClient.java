package org.prophetech.hyperone.vegaops.aws.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.prophetech.hyperone.vegaops.aws.model.CreateSecurityGroupRuleRequest;
import org.prophetech.hyperone.vegaops.aws.model.DeleteSecurityGroupRuleRequest;
import org.prophetech.hyperone.vegaops.aws.model.SecurityGroupRuleResponse;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

@Slf4j(topic = "vegaops")
public class AwsSecurityGroupRuleClient {

    public SecurityGroup getSecurityGroup(Ec2Client ec2, String securityGroupId){
        if (StringUtils.isEmpty(securityGroupId)) {
            throw new RuntimeException("securityGroupId is empty");
        }
        DescribeSecurityGroupsRequest describeSecurityGroupsRequest = DescribeSecurityGroupsRequest.builder()
                .groupIds(securityGroupId).build();
        DescribeSecurityGroupsResponse groupsResponse = ec2.describeSecurityGroups(describeSecurityGroupsRequest);
        if (!groupsResponse.sdkHttpResponse().isSuccessful()
                || CollectionUtils.isEmpty(groupsResponse.securityGroups())) {
            throw new RuntimeException("securityGroup does not exist");
        }
        ec2.close();
        return groupsResponse.securityGroups().get(0);
    }


    public SecurityGroupRuleResponse createSecurityGroupRule(Ec2Client ec2, CreateSecurityGroupRuleRequest request) {
        SecurityGroup securityGroup = getSecurityGroup(ec2, request.getSecurityGroupId());

        IpPermission.Builder ipPermissionBuilder = IpPermission.builder()
                .ipProtocol(request.getProtocol())
                .toPort(request.getPortEnd())
                .fromPort(request.getPortStart());
        IpRange ipRange = null;
        if (StringUtils.isNotEmpty(request.getCidrBlock())) {
            ipRange = IpRange.builder().cidrIp(request.getCidrBlock()).build();
        }
        Ipv6Range ipv6Range = null;
        if (StringUtils.isNotEmpty(request.getIpv6CidrBlock())) {
            ipv6Range = Ipv6Range.builder().cidrIpv6(request.getIpv6CidrBlock()).build();
        }
        if (ipRange != null) {
            ipPermissionBuilder = ipPermissionBuilder.ipRanges(ipRange);
        }
        if (ipv6Range != null) {
            ipPermissionBuilder = ipPermissionBuilder.ipv6Ranges(ipv6Range);
        }
        SecurityGroupRuleResponse result = new SecurityGroupRuleResponse();
        if ("ingress".equalsIgnoreCase(request.getDirection())) {
            AuthorizeSecurityGroupIngressRequest authRequest =
                    AuthorizeSecurityGroupIngressRequest.builder()
                            .groupId(request.getSecurityGroupId())
                            .ipPermissions(ipPermissionBuilder.build())
                            .build();
            AuthorizeSecurityGroupIngressResponse authResponse = ec2.authorizeSecurityGroupIngress(authRequest);
            result.setStatusCode(authResponse.sdkHttpResponse().statusCode());
        }
        if ("egress".equalsIgnoreCase(request.getDirection())) {
            AuthorizeSecurityGroupEgressRequest authRequest =
                    AuthorizeSecurityGroupEgressRequest.builder()
                            .groupId(securityGroup.groupId())
                            .ipPermissions(ipPermissionBuilder.build())
                            .build();
            AuthorizeSecurityGroupEgressResponse authResponse = ec2.authorizeSecurityGroupEgress(authRequest);
            result.setStatusCode(authResponse.sdkHttpResponse().statusCode());
        }
        if (200 == result.getStatusCode()) {
            result.setCidrBlock(request.getCidrBlock());
            result.setDirection(request.getDirection());
            result.setIpv6CidrBlock(request.getIpv6CidrBlock());
            result.setSecurityGroupId(request.getSecurityGroupId());
            result.setPortEnd(request.getPortEnd());
            result.setPortStart(request.getPortStart());
            result.setProtocol(request.getProtocol());
        }
        ec2.close();
        return result;
    }

    public void deleteSgRule(Ec2Client ec2, DeleteSecurityGroupRuleRequest request) {
        if ("ingress".equalsIgnoreCase(request.getDirection())) {
            try {
                RevokeSecurityGroupIngressRequest ingressRequest = RevokeSecurityGroupIngressRequest.builder().groupId(request.getSecurityGroupId()).cidrIp(request.getCidr()).ipProtocol(request.getProtocol()).fromPort(request.getPortStart()).toPort(request.getPortEnd()).build();
                RevokeSecurityGroupIngressResponse response = ec2.revokeSecurityGroupIngress(ingressRequest);
            } catch (Ec2Exception e) {
                e.getStackTrace();
            }
        } else if ("egress".equalsIgnoreCase(request.getDirection())) {
            try {
                IpPermission permission = IpPermission.builder().ipProtocol(request.getProtocol()).toPort(request.getPortStart()).fromPort(request.getPortEnd()).ipRanges(IpRange.builder().cidrIp(request.getCidr()).build()).build();
                RevokeSecurityGroupEgressRequest egressRequest = RevokeSecurityGroupEgressRequest.builder().ipPermissions(permission).groupId(request.getSecurityGroupId()).build();
                ec2.revokeSecurityGroupEgress(egressRequest);
            } catch (Ec2Exception e) {
                e.getStackTrace();
            }
        }
    }

}
