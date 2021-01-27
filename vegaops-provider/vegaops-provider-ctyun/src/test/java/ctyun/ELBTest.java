package ctyun;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.ctyun.client.CtyunJsoupClient;
import org.prophetech.hyperone.vegaops.ctyun.model.*;

public class ELBTest {
    private static CtyunAccount ctyunAccount = new CtyunAccount("xxxxx", "xxxxx");

    @Test
    @SneakyThrows
    public void create() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        CreateELBv4Request request = new CreateELBv4Request();
        request.setRegionId("cn-gzT");
        request.setName("fty_test");
        request.setDescription("Miaoshu");
        request.setVip_subnet_id("dc4a96d1-b8c4-4626-8626-ff47b24cc1e4");
        request.setProvider("vlb");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void delete() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        DeleteELBv4Request request = new DeleteELBv4Request();
        request.setRegionId("cn-gzT");
        request.setElbId("bff9abe7-81e9-4568-a4c0-29979cd43fe2");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void list() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        GetELBsv4Request request = new GetELBsv4Request();
        request.setRegionId("cn-gzT");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void detail() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        QueryELBv4DetailRequest request = new QueryELBv4DetailRequest();
        request.setRegionId("cn-gzT");
        request.setElbId("fe50acba-f844-4ea1-9688-036f08c2c470");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }
}
