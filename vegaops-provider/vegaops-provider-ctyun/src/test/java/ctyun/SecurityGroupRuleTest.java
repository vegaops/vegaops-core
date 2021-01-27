package ctyun;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.ctyun.client.CtyunJsoupClient;
import org.prophetech.hyperone.vegaops.ctyun.model.*;

public class SecurityGroupRuleTest {
    private static CtyunAccount ctyunAccount = new CtyunAccount("xxxxx", "xxxxx");

    @Test
    @SneakyThrows
    public void getSecurityGroupRules() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        GetSecurityGroupRulesRequest request = new GetSecurityGroupRulesRequest();
        request.setRegionId("cn-gzT");
        CtyunApiObjectResponse<GetSecurityGroupRulesResponse> ctyunResponse = (CtyunApiObjectResponse) client.getCtyunResponse(request);
//        ctyunResponse.getReturnObj().getSecurity_group_rules().forEach(o->{
//            deleteSGR(o.getId());
//        });
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void createSecurityGroupRule() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        CreateSecurityGroupRuleRequest request = new CreateSecurityGroupRuleRequest();
        request.setRegionId("cn-gzT");
        request.setSecurityGroupId("5124e7e2-0bae-4956-b577-e062c4853d70");
        request.setDirection("egress");
        request.setEthertype("IPv4");
        request.setProtocol("tcp");
        request.setPortRangeMin("3");
        request.setPortRangeMax("6");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @SneakyThrows
    public void deleteSGR(String id){
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        DeleteSecurityGroupRuleRequest request = new DeleteSecurityGroupRuleRequest();
        request.setRegionId("cn-gzT");
        request.setSecurityGroupRuleId(id);
        CtyunApiResponse response = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(response));
    }
}
