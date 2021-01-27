package ctyun;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.ctyun.client.CtyunJsoupClient;
import org.prophetech.hyperone.vegaops.ctyun.model.*;

import java.util.List;

public class SubnetTest {
    private static CtyunAccount ctyunAccount = new CtyunAccount("xxxxx", "xxxxx");


    @Test
    @SneakyThrows
    public void createSubnet() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        CreateSubnetRequest request = new CreateSubnetRequest();
        request.setName("hybTest");
        request.setCidr("192.168.0.0/24");
        request.setRegionId("cn-gzT");
        request.setZoneId("cn-gzTa");
        request.setDhcpEnable("true");
        request.setVpcId("3819d846-20bc-4159-bb7b-b434334707bd");
        request.setGatewayIp("192.168.0.1");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void deleteSubnet() {
        deleteSubnet("3819d846-20bc-4159-bb7b-b434334707bd","b94ecea6-2eae-4f93-9aa1-4dcda9ea9ac4");
    }

    @SneakyThrows
    public void deleteSubnet(String vpc,String subnet) {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        DeleteSubnetRequest request = new DeleteSubnetRequest();
        request.setRegionId("cn-gzT");
        request.setVpcId(vpc);
        request.setSubnetId(subnet);
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void getSubnets() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        GetSubnetsRequest request = new GetSubnetsRequest();
        request.setRegionId("cn-gzT");
        request.setVpcId("3819d846-20bc-4159-bb7b-b434334707bd");
        // 10c56632-7e5a-40fc-89bf-7b8f02546308
        //ae3f5ab2-d1a8-4688-9bf3-6bb886b0c790
        // e0331329-406f-4d08-bc1b-fd2876976e78
        // e719cbee-4a0c-4d4c-af2c-023656073e6c
        CtyunApiListResponse<GetSubnetsResponse> ctyunResponse = (CtyunApiListResponse)client.getCtyunResponse(request);
//        ctyunResponse.getReturnObj().forEach(o->{
//            deleteSubnet(o.getVpcId(), o.getResVlanId());
//        });
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void querySubnetDetail() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        QuerySubnetDetailRequest request = new QuerySubnetDetailRequest();
        request.setRegionId("cn-gzT");
        request.setSubnetId("220e7f3e-6951-4974-8f99-5c08e7621c1e");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void clearSubnets() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        GetSubnetsRequest request = new GetSubnetsRequest();
        request.setRegionId("cn-gzT");
        CtyunApiListResponse<GetSubnetsResponse> ctyunResponse = (CtyunApiListResponse) client.getCtyunResponse(request);
        List<GetSubnetsResponse> subnets = ctyunResponse.getReturnObj();
        subnets.forEach(subnet->{
            try {
                deleteSubnet(subnet.getVpcId(),subnet.getResVlanId());
            }catch (Throwable e){
            }
        });
    }
}
