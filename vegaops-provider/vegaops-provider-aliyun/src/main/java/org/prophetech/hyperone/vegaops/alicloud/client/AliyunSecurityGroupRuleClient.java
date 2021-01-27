package org.prophetech.hyperone.vegaops.alicloud.client;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.ecs.model.v20140526.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.prophetech.hyperone.vegaops.alicloud.model.CreateSecurityGroupRuleRequest;
import org.prophetech.hyperone.vegaops.alicloud.model.SecurityGroupRuleResponse;


@Slf4j(topic = "vegaops")
public class AliyunSecurityGroupRuleClient {

    public SecurityGroupRuleResponse createScGroupRule(CreateSecurityGroupRuleRequest createSecurityGroupRuleRequest) {
        DefaultProfile profile = DefaultProfile.getProfile(createSecurityGroupRuleRequest.getRegionId(), createSecurityGroupRuleRequest.getAccessKey(), createSecurityGroupRuleRequest.getSecret());
        IAcsClient clients = new DefaultAcsClient(profile);
        SecurityGroupRuleResponse result = new SecurityGroupRuleResponse();
        if ("egress".equalsIgnoreCase(createSecurityGroupRuleRequest.getDirection())) {
            AuthorizeSecurityGroupEgressRequest request = new AuthorizeSecurityGroupEgressRequest();
            request.setSecurityGroupId(createSecurityGroupRuleRequest.getSecurityGroupId());
            request.setIpProtocol(createSecurityGroupRuleRequest.getIpProtocol());
            request.setPolicy(createSecurityGroupRuleRequest.getPolicy());
            request.setPortRange(createSecurityGroupRuleRequest.getPortRange());
            if(StringUtils.isNotEmpty(createSecurityGroupRuleRequest.getDescription())){
                request.setDescription(createSecurityGroupRuleRequest.getDescription());
            }
            if(StringUtils.isNotEmpty(createSecurityGroupRuleRequest.getPriority())){
                request.setPriority(createSecurityGroupRuleRequest.getPriority());
            }
            if(StringUtils.isNotEmpty(createSecurityGroupRuleRequest.getDestCidrIp())){
                request.setDestCidrIp(createSecurityGroupRuleRequest.getDestCidrIp());
            }
            try {
                AuthorizeSecurityGroupEgressResponse response = clients.getAcsResponse(request);
//                System.out.println(new Gson().toJson(response));
                result.setDirection(createSecurityGroupRuleRequest.getDirection());
                result.setSecurityGroupId(createSecurityGroupRuleRequest.getSecurityGroupId());
                result.setIpProtocol(createSecurityGroupRuleRequest.getIpProtocol());
                result.setPolicy(createSecurityGroupRuleRequest.getPolicy());
                result.setPortRange(createSecurityGroupRuleRequest.getPortRange());
                if(StringUtils.isNotEmpty(createSecurityGroupRuleRequest.getDescription())){
                    result.setDescription(createSecurityGroupRuleRequest.getDescription());
                }
                if(StringUtils.isNotEmpty(createSecurityGroupRuleRequest.getDestCidrIp())){
                    result.setDestCidrIp(createSecurityGroupRuleRequest.getDestCidrIp());
                }
            } catch (ServerException e) {
                throw new RuntimeException("ErrMsg:" + e);
            }catch (ClientException e) {
                throw new RuntimeException("ErrMsg:" + e.getErrMsg());
            }
            return result;
        } else {
            AuthorizeSecurityGroupRequest request = new AuthorizeSecurityGroupRequest();
            request.setSecurityGroupId(createSecurityGroupRuleRequest.getSecurityGroupId());
            request.setIpProtocol(createSecurityGroupRuleRequest.getIpProtocol());
            request.setPolicy(createSecurityGroupRuleRequest.getPolicy());
            request.setPortRange(createSecurityGroupRuleRequest.getPortRange());
            if(StringUtils.isNotEmpty(createSecurityGroupRuleRequest.getDescription())){
                request.setDescription(createSecurityGroupRuleRequest.getDescription());
            }
            if(StringUtils.isNotEmpty(createSecurityGroupRuleRequest.getSourceCidrIp())){
                request.setSourceCidrIp(createSecurityGroupRuleRequest.getSourceCidrIp());
            }
            if(StringUtils.isNotEmpty(createSecurityGroupRuleRequest.getPriority())){
                request.setPriority(createSecurityGroupRuleRequest.getPriority());
            }
            try {
                AuthorizeSecurityGroupResponse response = clients.getAcsResponse(request);
                result.setDirection(createSecurityGroupRuleRequest.getDirection());
                result.setIpProtocol(createSecurityGroupRuleRequest.getIpProtocol());
                result.setPolicy(createSecurityGroupRuleRequest.getPolicy());
                result.setPortRange(createSecurityGroupRuleRequest.getPortRange());
                if(StringUtils.isNotEmpty(createSecurityGroupRuleRequest.getDescription())){
                    result.setDescription(createSecurityGroupRuleRequest.getDescription());
                }
                if(StringUtils.isNotEmpty(createSecurityGroupRuleRequest.getSourceCidrIp())){
                    result.setSourceCidrIp(createSecurityGroupRuleRequest.getSourceCidrIp());
                }
                result.setSecurityGroupId(createSecurityGroupRuleRequest.getSecurityGroupId());
            } catch (ServerException e) {
                throw new RuntimeException("ErrMsg:" + e);
            } catch (ClientException e) {
                throw new RuntimeException("ErrMsg:" + e.getErrMsg());
            }
            return result;
        }

    }

    public void deleteScGroupRule(CreateSecurityGroupRuleRequest createSecurityGroupRuleRequest) {
        DefaultProfile profile = DefaultProfile.getProfile(createSecurityGroupRuleRequest.getRegionId(), createSecurityGroupRuleRequest.getAccessKey(), createSecurityGroupRuleRequest.getSecret());
        IAcsClient clients = new DefaultAcsClient(profile);
//        SecurityGroupRuleResponse result = new SecurityGroupRuleResponse();
        if ("egress".equalsIgnoreCase(createSecurityGroupRuleRequest.getDirection())) {
            RevokeSecurityGroupEgressRequest request = new RevokeSecurityGroupEgressRequest();
            request.setSecurityGroupId(createSecurityGroupRuleRequest.getSecurityGroupId());
            request.setIpProtocol(createSecurityGroupRuleRequest.getIpProtocol());
            request.setPolicy(createSecurityGroupRuleRequest.getPolicy());
            request.setPortRange(createSecurityGroupRuleRequest.getPortRange());
            if(StringUtils.isNotEmpty(createSecurityGroupRuleRequest.getSourceCidrIp())){
                request.setSourceCidrIp(createSecurityGroupRuleRequest.getSourceCidrIp());
            }
            if(StringUtils.isNotEmpty(createSecurityGroupRuleRequest.getDestCidrIp())){
                request.setDestCidrIp(createSecurityGroupRuleRequest.getDestCidrIp());
            }
            try {
                RevokeSecurityGroupEgressResponse response = clients.getAcsResponse(request);
            } catch (ServerException e) {
                throw new RuntimeException("ErrMsg:" + e);
//                e.printStackTrace();
            } catch (ClientException e) {
//                System.out.println("ErrCode:" + e.getErrCode());
//                System.out.println("ErrMsg:" + e.getErrMsg());
                throw new RuntimeException("ErrMsg:" + e.getErrMsg());
//                System.out.println("RequestId:" + e.getRequestId());
            }
        } else {
            RevokeSecurityGroupRequest request = new RevokeSecurityGroupRequest();
            request.setSecurityGroupId(createSecurityGroupRuleRequest.getSecurityGroupId());
            request.setIpProtocol(createSecurityGroupRuleRequest.getIpProtocol());
            request.setPolicy(createSecurityGroupRuleRequest.getPolicy());
            request.setPortRange(createSecurityGroupRuleRequest.getPortRange());
            if(StringUtils.isNotEmpty(createSecurityGroupRuleRequest.getSourceCidrIp())){
                request.setSourceCidrIp(createSecurityGroupRuleRequest.getSourceCidrIp());
            }
            if(StringUtils.isNotEmpty(createSecurityGroupRuleRequest.getDestCidrIp())){
                request.setDestCidrIp(createSecurityGroupRuleRequest.getDestCidrIp());
            }
            try {
                RevokeSecurityGroupResponse response = clients.getAcsResponse(request);
//
            } catch (ServerException e) {
                throw new RuntimeException("ErrMsg:" + e);
//                e.printStackTrace();
            } catch (ClientException e) {
//                System.out.println("ErrCode:" + e.getErrCode());
//                System.out.println("ErrMsg:" + e.getErrMsg());
                throw new RuntimeException("ErrMsg:" + e.getErrMsg());
//                System.out.println("RequestId:" + e.getRequestId());
            }
        }

    }


}
