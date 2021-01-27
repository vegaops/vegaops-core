package ctyun;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.ctyun.client.CtyunJsoupClient;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunAccount;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunApiResponse;
import org.prophetech.hyperone.vegaops.ctyun.model.GetZoneConfigRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.QueryAvailableZonesRequest;

public class ZoneTest {
    private static CtyunAccount ctyunAccount = new CtyunAccount("xxxxx", "xxxxx");

    @Test
    @SneakyThrows
    public void zonefig() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        GetZoneConfigRequest request = new GetZoneConfigRequest();
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void queryAvailableZones() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        QueryAvailableZonesRequest request = new QueryAvailableZonesRequest();
        request.setRegionId("cn-gzT");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }
}
