package ctyun;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.ctyun.client.CtyunJsoupClient;
import org.prophetech.hyperone.vegaops.ctyun.model.*;

public class EipTest {
    private static CtyunAccount ctyunAccount = new CtyunAccount("xxxxx", "xxxxx");

    @Test
    @SneakyThrows
    public void getEips() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        QueryIPsRequest request = new QueryIPsRequest();
        request.setRegionId("cn-gzT");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void unbind() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        UnbindIPRequest request = new UnbindIPRequest();
        request.setRegionId("cn-gzT");
        request.setPublicIpId("e5528855-457f-448f-aea6-05c5c91a1548");
        request.setPrivateIp("192.168.1.18");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }


    @Test
    @SneakyThrows
    public void bind() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        BindIPRequest request = new BindIPRequest();
        request.setRegionId("cn-gzT");
        request.setPublicIpId("f9472ee7-cb00-43cf-a061-255b4da307e7");
        request.setNetworkCardId("4aa59f30-d557-4a02-9112-fd7770dee286");
        //request.setPrivateIp("192.168.1.88");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

}
