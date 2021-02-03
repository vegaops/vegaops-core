package org.prophetech.hyperone.vegaops.ctyun.client;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.prophetech.hyperone.vegaops.ctyun.exception.ClientException;
import org.prophetech.hyperone.vegaops.ctyun.exception.ServerException;
import org.prophetech.hyperone.vegaops.ctyun.model.*;

import java.util.Collections;
import java.util.List;

@Setter
@Getter
@Slf4j(topic = "vegaops")
public class CtyunSecurityGroupRuleClient {
    private CtyunAccount ctyunAccount;

    public List<GetSecurityGroupRulesResponse.SecurityGroupRules> getAllSecurityGroupRules(String regionId) throws ServerException, ClientException {
        GetSecurityGroupRulesRequest request = new GetSecurityGroupRulesRequest();
        CtyunJsoupClient client = new CtyunJsoupClient();
        request.setRegionId(regionId);
        client.setCtyunAccount(ctyunAccount);
        CtyunApiResponse response = client.getCtyunResponse(request);
        if (response.getStatusCode() == 800) {
            CtyunApiObjectResponse<GetSecurityGroupRulesResponse> ctyunResponse = (CtyunApiObjectResponse<GetSecurityGroupRulesResponse>) response;
            List<GetSecurityGroupRulesResponse.SecurityGroupRules> securityGroupRules = ctyunResponse.getReturnObj().getSecurity_group_rules();
            securityGroupRules.forEach(o->{
                if (StringUtils.isNotBlank(o.getEthertype()) && "IPv6".equals(o.getEthertype())){
                    o.setIpv6Cidr(o.getRemote_ip_prefix());
                    o.setRemote_ip_prefix("");
                }
            });
            return securityGroupRules;
        }
        return Collections.EMPTY_LIST;
    }

    public List<GetSecurityGroupRulesResponse.SecurityGroupRules> getSecurityGroupRules(QueryGroupRuleRequest queryGroupRuleRequest) throws ServerException, ClientException {
        GetSecurityGroupRulesRequest request = new GetSecurityGroupRulesRequest();
        CtyunJsoupClient client = new CtyunJsoupClient();
        request.setRegionId(queryGroupRuleRequest.getRegionId());
        if(StringUtils.isNotEmpty(queryGroupRuleRequest.getSecurityGroupId())){
            request.setSecurityGroupId(queryGroupRuleRequest.getSecurityGroupId());
        }
//        request.setSecurityGroupId(queryGroupRuleRequest.getSecurityGroupId());
        client.setCtyunAccount(ctyunAccount);
        CtyunApiResponse response = client.getCtyunResponse(request);
        if (response.getStatusCode() == 800) {
            CtyunApiObjectResponse<GetSecurityGroupRulesResponse> ctyunResponse = (CtyunApiObjectResponse<GetSecurityGroupRulesResponse>) response;
            List<GetSecurityGroupRulesResponse.SecurityGroupRules> securityGroupRules = ctyunResponse.getReturnObj().getSecurity_group_rules();
            securityGroupRules.forEach(o->{
                if (StringUtils.isNotBlank(o.getEthertype()) && "IPv6".equals(o.getEthertype())){
                    o.setIpv6Cidr(o.getRemote_ip_prefix());
                    o.setRemote_ip_prefix("");
                }
            });
            return securityGroupRules;
        }
        return Collections.EMPTY_LIST;
    }
}
