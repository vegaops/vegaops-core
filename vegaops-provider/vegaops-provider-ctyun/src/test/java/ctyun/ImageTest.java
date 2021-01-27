package ctyun;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.ctyun.client.CtyunJsoupClient;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunAccount;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunApiResponse;
import org.prophetech.hyperone.vegaops.ctyun.model.GetImagesRequest;

public class ImageTest {
    private static CtyunAccount ctyunAccount = new CtyunAccount("xxxxx", "xxxxx");

    @Test
    @SneakyThrows
    public void getImages() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        GetImagesRequest request = new GetImagesRequest();
        request.setRegionId("cn-gzT");
        request.setImageType("gold");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

}
