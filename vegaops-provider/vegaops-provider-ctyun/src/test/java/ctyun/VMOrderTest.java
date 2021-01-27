package ctyun;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.ctyun.client.CtyunInstanceClient;
import org.prophetech.hyperone.vegaops.ctyun.client.CtyunJsoupClient;
import org.prophetech.hyperone.vegaops.ctyun.model.*;

import java.util.Arrays;
import java.util.List;


public class VMOrderTest {
    private static CtyunAccount ctyunAccount = new CtyunAccount("xxxxx", "xxxxx");

    @Test
    @SneakyThrows
    public void createOrderVM() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        PlaceNewPurchaseOrderRequest request = new PlaceNewPurchaseOrderRequest();
        OrderDetail orderDetail = new OrderDetail();
        OrderDetail.Order order = new OrderDetail.Order();
        order.setInstanceCnt("1");
        order.setCycleCnt("1");
        order.setCycleType("3");
        // 云主机配置
        OrderDetail.Item vmcItem = new OrderDetail.Item();
        vmcItem.setMaster("true");
        vmcItem.setResourceType("VMC");
        vmcItem.setServiceTag("HWS");
        vmcItem.setItemValue(1);
        OrderDetail.Vmc vmc = new OrderDetail.Vmc();
        vmc.setMemSize("1");
        vmc.setCpuNum("1");
        vmc.setFlavorType("CPU");
        vmc.setOsType("linux");
        vmc.setAvailability_zone("cn-gzTa");
        vmc.setName("prepay");
        vmc.setImageRef("3af8a1ec-94a8-4e39-adef-7a9cedd93a44");
        vmc.setVpcid("3819d846-20bc-4159-bb7b-b434334707bd");
        CreateVMRequest.SecurityGroup securityGroup = new CreateVMRequest.SecurityGroup();
        securityGroup.setId("1ae92871-e385-4a29-9db6-05413cdeffa2");
        vmc.setSecurity_groups(Arrays.asList(securityGroup));
        CreateVMRequest.Nic nic = new CreateVMRequest.Nic();
        nic.setSubnet_id("b94ecea6-2eae-4f93-9aa1-4dcda9ea9ac4");
        vmc.setNics(Arrays.asList(nic));
        vmc.setAdminPass("Wj123456");
        vmc.setRegionId("cn-gzT");
        vmc.setSupport_auto_recovery("true");
        vmcItem.setItemConfig(vmc);
        // 系统盘配置
        OrderDetail.Item sysVolumeItem = new OrderDetail.Item();
        sysVolumeItem.setMaster("false");
        sysVolumeItem.setResourceType("EBSC");
        sysVolumeItem.setServiceTag("HWS");
        sysVolumeItem.setItemValue(50);
        sysVolumeItem.setIsSystemVolume(true);
        OrderDetail.Ebsc sysVolume = new OrderDetail.Ebsc();
        sysVolume.setVolumeType("SATA");
        sysVolumeItem.setItemConfig(sysVolume);
        // 数据盘配置
        OrderDetail.Item dataVolumeItem = new OrderDetail.Item();
        dataVolumeItem.setMaster("false");
        dataVolumeItem.setResourceType("EBSC");
        dataVolumeItem.setServiceTag("HWS");
        dataVolumeItem.setItemValue(100);
        OrderDetail.Ebsc dataVolume = new OrderDetail.Ebsc();
        dataVolume.setVolumeType("SATA");
        dataVolumeItem.setItemConfig(dataVolume);
        OrderDetail.Item networkcItem = new OrderDetail.Item();
        networkcItem.setMaster("false");
        networkcItem.setResourceType("NETWORKC");
        networkcItem.setServiceTag("HWS");
        networkcItem.setItemValue(10);
        // 带宽配置
        OrderDetail.Networkc networkc = new OrderDetail.Networkc();
        networkc.setType("standalone");
        networkc.setName("testNetWork111111");
        networkc.setRegionId("cn-gzT");
        networkcItem.setItemConfig(networkc);

        order.setItems(Arrays.asList(vmcItem, sysVolumeItem, dataVolumeItem, networkcItem));
        orderDetail.setOrders(Arrays.asList(order));
        CustomInfo customInfo = new CustomInfo();
        customInfo.setType(0);
        customInfo.setName("fty");
        customInfo.setPhone("123456");
//        customInfo.setEmail("XX@XX.com");
//        customInfo.setIdentity(new CustomInfo.Identity());
        orderDetail.setCustomInfo(customInfo);
        request.setOrderDetailJson(orderDetail.toJson());
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void payOrder() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        PayGeneralUserOrderRequest request = new PayGeneralUserOrderRequest();
        request.setCash("800");
        request.setMasterOrderId("287dd27e53454cf0b873e407ad0e14be");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void createVM() {
        CtyunInstanceClient client = new CtyunInstanceClient();
        client.setCtyunAccount(ctyunAccount);
        CreateInstanceRequest request = new CreateInstanceRequest();
        request.setPayType("PostPaid");
        request.setRegionId("cn-gzT");
        request.setName("fty_test10");
        request.setAvailability_zone("cn-gzTa");
        request.setImageRef("3af8a1ec-94a8-4e39-adef-7a9cedd93a44");
        request.setOsType("linux");
        request.setVolumeSize(50);
        request.setVolumetype("SATA");
        request.setFlavorRef("c2.medium");
        request.setVpcid("3819d846-20bc-4159-bb7b-b434334707bd");
        request.setSecurityGroupId("1ae92871-e385-4a29-9db6-05413cdeffa2");
        request.setSubnet_id("b94ecea6-2eae-4f93-9aa1-4dcda9ea9ac4");
//        request.setIptype("5_telcom");
//        request.setBandwidthSize(1);
        request.setSharetype("PER");
        request.setAdminPass("Fu123456");
        request.setCount(1);
        request.setPublicipId("784992f5-441c-4c34-a499-99a951a98e49");
        CreateInstanceRequest response = client.createInstance(request);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    @SneakyThrows
    public void createVMOrder() {
        CtyunInstanceClient client = new CtyunInstanceClient();
        client.setCtyunAccount(ctyunAccount);
        CreateInstanceRequest request = new CreateInstanceRequest();
        request.setPayType("Subscription");
        request.setInstanceCnt("1");
        request.setCycleCnt("1");
        request.setCycleType("3");
        request.setInstanceServiceTag("HWS");
        request.setCount(1);
        request.setMemSize("1");
        request.setCpuNum("1");
        request.setFlavorType("CPU");
        request.setOsType("linux");
        request.setName("vegaopsTestFlowQ");
        request.setAvailability_zone("cn-gzTa");
        request.setImageRef("3af8a1ec-94a8-4e39-adef-7a9cedd93a44");
        request.setVpcid("3819d846-20bc-4159-bb7b-b434334707bd");
        request.setSecurityGroupId("1ae92871-e385-4a29-9db6-05413cdeffa2");
        request.setSubnet_id("b94ecea6-2eae-4f93-9aa1-4dcda9ea9ac4");
//        request.setAdminPass("Fu123456");
        request.setKeyPairId("ftyTest");
        request.setRegionId("cn-gzT");
        request.setSupport_auto_recovery("true");
        request.setVolumeServiceTag("HWS");
        request.setVolumeSize(50);
        request.setVolumetype("SATA");
        request.setCustomerInfoType(0);
        request.setCustomerName("fty");
        request.setCustomerPhone("123456");
        request.setBandwidthSize(10);
        request.setSharetype("PER");
        CreateInstanceRequest response = client.createInstance(request);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    @SneakyThrows
    public void queryInstance() {
        CtyunInstanceClient client = new CtyunInstanceClient();
        client.setCtyunAccount(ctyunAccount);
        GetVMsRequest request = new GetVMsRequest();
        request.setRegionId("cn-gzT");
        List<GetVMsResponse.Instance> instanceList = client.getInstances(request);

//        CtyunJsoupClient deteleclient = new CtyunJsoupClient();
//        deteleclient.setCtyunAccount(ctyunAccount);
//        DeleteVMRequest deleteRequest = new DeleteVMRequest();
//        for (GetVMsResponse.Instance instance : instanceList) {
//            deleteRequest.setRegionId("cn-gzT");
//            deleteRequest.setVmId(instance.getId());
//            CtyunApiResponse ctyunResponse = deteleclient.getCtyunResponse(deleteRequest);
//            System.out.println(JSON.toJSONString(ctyunResponse));
//            Thread.sleep(1000);
//        }
        System.out.println(JSON.toJSONString(instanceList));
    }

    @Test
    @SneakyThrows
    public void QueryVMsByOrderId() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        QueryVMsByOrderIdRequest request = new QueryVMsByOrderIdRequest();
        request.setOrderId("287dd27e53454cf0b873e407ad0e14be");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));

    }

    @Test
    @SneakyThrows
    public void queryOrderDetail(){
        CtyunInstanceClient client = new CtyunInstanceClient();
        client.setCtyunAccount(ctyunAccount);
        client.queryOrderDetail("47071954bca5464a94322507fb78bab3");
    }
}