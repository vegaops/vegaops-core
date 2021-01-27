package ctyun;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.ctyun.client.CtyunJsoupClient;
import org.prophetech.hyperone.vegaops.ctyun.model.*;

public class SecurityGroupTest {
    private static CtyunAccount ctyunAccount = new CtyunAccount("xxxxx", "xxxxx");

    @Test
    @SneakyThrows
    public void getSecurityGroups() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        GetSecurityGroupsRequest request = new GetSecurityGroupsRequest();
        request.setRegionId("cn-gzT");
        CtyunApiListResponse<GetSecurityGroupsResponse> ctyunResponse = (CtyunApiListResponse) client.getCtyunResponse(request);
//        ctyunResponse.getReturnObj().forEach(sg->{
//            delelteSG(sg.getResSecurityGroupId());
//        });
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void querySecurityGroupDetail() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        QuerySecurityGroupDetailRequest request = new QuerySecurityGroupDetailRequest();
        request.setRegionId("cn-gzT");
        request.setSecurityGroupId("36ea1eb1-d4c4-4e75-845b-21cc6f20b7bf");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void createSecurityGroup() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        CreateSecurityGroupRequest request = new CreateSecurityGroupRequest();
        request.setName("ftyTest");
        request.setVpcId("a7c76eaf-0e57-4aaf-886f-d12f3587b76b");
        request.setRegionId("cn-gzT");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @SneakyThrows
    public void delelteSG(String id){
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        DeleteSecurityGroupRequest request = new DeleteSecurityGroupRequest();
        request.setSecurityGroupId(id);
        request.setRegionId("cn-gzT");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }
}
