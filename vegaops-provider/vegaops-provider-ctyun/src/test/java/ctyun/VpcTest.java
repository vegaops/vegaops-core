package ctyun;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.ctyun.client.CtyunJsoupClient;
import org.prophetech.hyperone.vegaops.ctyun.model.*;

import java.util.List;

public class VpcTest {
    private static CtyunAccount ctyunAccount = new CtyunAccount("xxxxx", "xxxxx");

    @Test
    @SneakyThrows
    public void getVpcs() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        GetVpcsRequest request = new GetVpcsRequest();
        request.setRegionId("cn-gzT");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void queryVPCDetail() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        QueryVPCDetailRequest request = new QueryVPCDetailRequest();
        request.setRegionId("cn-gzT");
        request.setVpcId("3819d846-20bc-4159-bb7b-b434334707bd");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void createVpc() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        CreateVpcRequest request = new CreateVpcRequest();
        request.setName("ftyTest3");
        request.setCidr("192.168.0.0/24");
        request.setRegionId("cn-gzT");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void deleteVpc() {
        deleteVpc("dc66e6ab-7a56-4b0f-9f4e-0c5657c6c89a");
    }

    @Test
    @SneakyThrows
    public void deleteVpcs() {
        String[] vpcs = "3ebc4cf8-d8fa-4f2d-a25e-637f77a5fed6,10c56632-7e5a-40fc-89bf-7b8f02546308,ae3f5ab2-d1a8-4688-9bf3-6bb886b0c790,e0331329-406f-4d08-bc1b-fd2876976e78,e719cbee-4a0c-4d4c-af2c-023656073e6c".split(",");
        for (String vpcId : vpcs) {
            deleteVpc(vpcId);
        }
    }

    @Test
    @SneakyThrows
    public void clearVpcs() {
        SubnetTest subnetTest = new SubnetTest();
        subnetTest.clearSubnets();
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        GetVpcsRequest request = new GetVpcsRequest();
        request.setRegionId("cn-gzT");
        CtyunApiListResponse<GetVpcsResponse> ctyunResponse = (CtyunApiListResponse) client.getCtyunResponse(request);
        List<GetVpcsResponse> vpcs = ctyunResponse.getReturnObj();
        vpcs.forEach(vpc -> {
            try {
                deleteVpc(vpc.getResVpcId());
            } catch (Throwable e) {
            }

        });
    }


    @SneakyThrows
    private void deleteVpc(String vpcId) {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        DeleteVPCRequest request = new DeleteVPCRequest();
        request.setRegionId("cn-gzT");
        request.setVpcId(vpcId);
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

}
