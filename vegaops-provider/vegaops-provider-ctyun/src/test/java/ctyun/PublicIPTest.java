package ctyun;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.ctyun.client.CtyunJsoupClient;
import org.prophetech.hyperone.vegaops.ctyun.model.*;

public class PublicIPTest {
    private static CtyunAccount ctyunAccount = new CtyunAccount("xxxxx", "xxxxx");

    @Test
    @SneakyThrows
    public void getIPs() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        QueryIPsRequest request = new QueryIPsRequest();
        request.setRegionId("cn-gzT");
        CtyunApiObjectResponse<QueryIPsResponse> ctyunResponse = (CtyunApiObjectResponse)client.getCtyunResponse(request);
//        ctyunResponse.getReturnObj().getPublicips().forEach(o->{
//            deteleIP(o.getId());
//        });
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void createIP(){
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        CreateIPRequest request = new CreateIPRequest();
        request.setRegionId("cn-gzT");
        request.setZoneId("cn-gzTa");
        request.setName("vegaopsTestIPz");
        request.setType("5_telcom");
        request.setSize("1");
        request.setIpVersion("4");
        request.setShareType("PER");
        request.setChargeMode("bandwidth");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void deteleIP(){
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        DeleteIPRequest request = new DeleteIPRequest();
        request.setRegionId("cn-gzT");
        request.setPublicIpId("b0f1eb65-2f8a-4352-a391-995336912be1");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }
}
