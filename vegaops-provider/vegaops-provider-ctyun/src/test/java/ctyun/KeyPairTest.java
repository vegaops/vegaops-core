package ctyun;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.ctyun.client.CtyunJsoupClient;
import org.prophetech.hyperone.vegaops.ctyun.model.*;

public class KeyPairTest {
    private static CtyunAccount ctyunAccount = new CtyunAccount("xxxxx", "xxxxx");

    @Test
    @SneakyThrows
    public void getSSHs() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        GetKeyPairsRequest request = new GetKeyPairsRequest();
        request.setRegionId("cn-gzT");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void createSSH() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        CreateKeyPairRequest request = new CreateKeyPairRequest();
        request.setRegionId("cn-gzT");
        request.setName("ftyTestzz");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void getKeyPairList() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        GetKeyPairsRequest request = new GetKeyPairsRequest();
        request.setRegionId("cn-gzT");
        CtyunApiObjectResponse<GetKeyPairsResponse> ctyunResponse = (CtyunApiObjectResponse)client.getCtyunResponse(request);
        ctyunResponse.getReturnObj().getKeypairs().forEach(o->{
            deleteSSH(o.getKeypair().getName());
        });
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @SneakyThrows
    public void deleteSSH(String name) {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        DeleteKeyPairRequest request = new DeleteKeyPairRequest();
        request.setRegionId("cn-gzT");
        request.setName(name);
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }
}