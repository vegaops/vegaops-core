package ctyun;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.ctyun.client.CtyunJsoupClient;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunAccount;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunApiResponse;
import org.prophetech.hyperone.vegaops.ctyun.model.GetListenersv4Request;

public class ListenerTest {
    private static CtyunAccount ctyunAccount = new CtyunAccount("xxxxx", "xxxxx");


    @Test
    @SneakyThrows
    public void list() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        GetListenersv4Request request = new GetListenersv4Request();
        request.setRegionId("cn-gzT");
        request.setLoadBalancerId("26036991-1f36-4a6b-8286-aa4c5a426b2a");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

}
