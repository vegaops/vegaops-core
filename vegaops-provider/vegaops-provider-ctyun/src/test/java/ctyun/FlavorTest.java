package ctyun;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.ctyun.client.CtyunFlavorClient;
import org.prophetech.hyperone.vegaops.ctyun.client.CtyunJsoupClient;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunAccount;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunApiResponse;
import org.prophetech.hyperone.vegaops.ctyun.model.GetFlavorsRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.GetFlavorsResponse;

import java.util.List;

public class FlavorTest {
    private static CtyunAccount ctyunAccount = new CtyunAccount("xxxxx", "xxxxx");

    @Test
    @SneakyThrows
    public void getFlavors() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        GetFlavorsRequest request = new GetFlavorsRequest();
        request.setRegionId("cn-gzT");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void getFlavor(){
        CtyunFlavorClient client = new CtyunFlavorClient();
        client.setCtyunAccount(ctyunAccount);
        GetFlavorsRequest request = new GetFlavorsRequest();
        request.setRegionId("cn-gzT");
        List<GetFlavorsResponse> flavors = client.getAllFlavors(request);
        System.out.println(JSON.toJSONString(flavors));
    }

}
