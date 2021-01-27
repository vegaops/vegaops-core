package ctyun;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.ctyun.client.CtyunJsoupClient;
import org.prophetech.hyperone.vegaops.ctyun.model.*;

import java.util.Arrays;


public class VMTest {
    private static CtyunAccount ctyunAccount = new CtyunAccount("de6e657955d94b2ea6b01de6619d3071", "c9adce56bce74fe29809d3d7f505b7c9");

    @Test
    @SneakyThrows
    public void queryVMDetail() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        QueryVMDetailRequest request = new QueryVMDetailRequest();
        request.setRegionId("cn-gzT");
        request.setVmId("7f29001c-4155-4504-a0bc-27e0a5b9ffed");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void createVM() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        CreateVMRequest request = new CreateVMRequest();
        request.setName("JOBIDtest3");
        request.setAvailability_zone("cn-gzTa");
        request.setImageRef("3af8a1ec-94a8-4e39-adef-7a9cedd93a44");
        request.setOsType("linux");
        CreateVMRequest.Volume volume = new CreateVMRequest.Volume();
        volume.setSize(50);
        volume.setVolumetype("SATA");
        request.setRoot_volume(volume);
        CreateVMRequest.Volume dataVolume = new CreateVMRequest.Volume();
        dataVolume.setSize(10);
        dataVolume.setVolumetype("SATA");
        request.setData_volumes(Arrays.asList(dataVolume));
        request.setFlavorRef("c2.medium");
        request.setVpcid("3819d846-20bc-4159-bb7b-b434334707bd");
        CreateVMRequest.SecurityGroup securityGroup = new CreateVMRequest.SecurityGroup();
        securityGroup.setId("1ae92871-e385-4a29-9db6-05413cdeffa2");
        request.setSecurity_groups(Arrays.asList(securityGroup));
        CreateVMRequest.Nic nic = new CreateVMRequest.Nic();
        nic.setSubnet_id("b94ecea6-2eae-4f93-9aa1-4dcda9ea9ac4");
        request.setNics(Arrays.asList(nic));
        CreateVMRequest.PublicIp publicIp = new CreateVMRequest.PublicIp();
        CreateVMRequest.Eip eip = new CreateVMRequest.Eip();
        eip.setIptype("5_telcom");
        CreateVMRequest.BandWidth bandWidth = new CreateVMRequest.BandWidth();
        bandWidth.setSize(1);
        bandWidth.setSharetype("PER");
        eip.setBandwidth(bandWidth);
        publicIp.setEip(eip);
        request.setPublicip(publicIp);
        request.setAdminPass("Fu123456");
        request.setCount(1);
        request.setRegionID("cn-gzT");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void queryJOB() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        QueryJobStatusRequest request = new QueryJobStatusRequest();
        request.setRegionId("cn-gzT");
        request.setJobId("ff8080827185a99201721279979c3946");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void getVMOrders() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        GetVMOrdersRequest request = new GetVMOrdersRequest();
        request.setRegionId("cn-gzT");
        request.setPageNo("1");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
       System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void deteleVM(){
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        DeleteVMRequest request = new DeleteVMRequest();
        request.setRegionId("cn-gzT");
        request.setVmId("fc6b3886-6e5e-4bcc-800d-9b55394b9947");
        //6a2b3e41-b9b1-48cf-a872-6ab356a26ebb
        //fc6b3886-6e5e-4bcc-800d-9b55394b9947
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void StartVM(){
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        OndemandStartVMRequest request = new OndemandStartVMRequest();
        request.setRegionId("cn-gzT");
        request.setVmId("5ea33e59-f5a7-4ebd-b409-b21aff3e6705");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }
    // c5a02880-454c-4caa-9e26-48edcf4aff55
    // b408e0c6-dc4f-4758-ab80-179d916f27cd
    // fc6b3886-6e5e-4bcc-800d-9b55394b9947
    @Test
    @SneakyThrows
    public void StopVM(){
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        StopVMRequest request = new StopVMRequest();
        request.setRegionId("cn-gzT");
        request.setVmId("5ea33e59-f5a7-4ebd-b409-b21aff3e6705");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
        for (int i = 0; i < 5; i++){
            queryVMDetail();
        }
    }

    @Test
    @SneakyThrows
    public void RestartVM(){
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        RestartVMRequest request = new RestartVMRequest();
        request.setRegionId("cn-gzT");
        request.setVmId("5ea33e59-f5a7-4ebd-b409-b21aff3e6705");
        request.setType("SOFT");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
        for (int i = 0; i < 5; i++){
            queryVMDetail();
        }
    }

    @Test
    @SneakyThrows
    public void QueryOrderDetail() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        QueryVMsByOrderIdRequest request = new QueryVMsByOrderIdRequest();
        request.setOrderId("62f77fd52efc47a686e1e569b0877b46");
        CtyunApiResponse response = client.getCtyunResponse(request);
        if (response.getStatusCode() == 800) {
            CtyunApiListResponse<QueryVMsByOrderIdResponse> orderResponse = (CtyunApiListResponse) response;
            CreateInstanceRequest instance = new CreateInstanceRequest();
            QueryVMsByOrderIdResponse order = orderResponse.getReturnObj().get(0);
            instance.setId(order.getResVmId());
            instance.setStatus(order.getStatus());
            instance.setName(order.getVmName());
            instance.setOsType(order.getOsStyle());
            instance.setRegionId(order.getRegionId());
            instance.setSubnet_id(order.getVlanId());
            instance.setCreateDate(order.getCreateDate());
            instance.setExpireTime(order.getDueDate());
            instance.setAvailability_zone(order.getZoneId());
            instance.setCpuNum(order.getCpuNum());
            instance.setMemSize(order.getMemSize());
        }
    }
}