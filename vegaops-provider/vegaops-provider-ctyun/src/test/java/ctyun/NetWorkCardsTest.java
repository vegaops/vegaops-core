package ctyun;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.ctyun.client.CtyunJsoupClient;
import org.prophetech.hyperone.vegaops.ctyun.client.CtyunNetworkInterfaceClient;
import org.prophetech.hyperone.vegaops.ctyun.model.*;

import java.util.ArrayList;
import java.util.List;

public class NetWorkCardsTest {
    private static CtyunAccount ctyunAccount = new CtyunAccount("xxxxx", "xxxxx");

    @Test
    @SneakyThrows
    public void queryNetworkCardsDetail() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        QueryNetworkCardsRequest request = new QueryNetworkCardsRequest();
        request.setRegionId("cn-gzT");
        request.setVmId("ea5c357e-cf24-4464-ba50-2aac3fae8cc1");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void AddNetWorkCards() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        AddNetworkCardsRequest request = new AddNetworkCardsRequest();
        request.setRegionId("cn-gzT");
        request.setVmId("ea5c357e-cf24-4464-ba50-2aac3fae8cc1");
        List<AddNetworkCardsRequest.SecurityGroups> securityGroups = new ArrayList<>();
        AddNetworkCardsRequest.SecurityGroups securityGroups1 = new AddNetworkCardsRequest.SecurityGroups();
        securityGroups1.setId("1ae92871-e385-4a29-9db6-05413cdeffa2");
        securityGroups.add(securityGroups1);
        List<AddNetworkCardsRequest.NetworkCardsInfo> networkCardsInfos = new ArrayList<>();
        AddNetworkCardsRequest.NetworkCardsInfo networkCardsInfos1 = new AddNetworkCardsRequest.NetworkCardsInfo();
        networkCardsInfos1.setSubnetId("b94ecea6-2eae-4f93-9aa1-4dcda9ea9ac4");
        networkCardsInfos1.setSecurityGroups(securityGroups);
        networkCardsInfos.add(networkCardsInfos1);
        request.setNetworkCards(networkCardsInfos);
        System.out.println(JSON.toJSONString(request));
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void deleteNetWorkCard() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        DeleteNetworkCardRequest request = new DeleteNetworkCardRequest();
        request.setRegionId("cn-gzT");
        request.setVmId("fc6b3886-6e5e-4bcc-800d-9b55394b9947");
        request.setNetworkCardId("841d0292-d0d4-4775-8bb6-ca762a42cca7");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void getsNetWorkCards() {
        CtyunNetworkInterfaceClient client = new CtyunNetworkInterfaceClient();
        client.setCtyunAccount(ctyunAccount);
        List<QueryNetworkCardsResponse.InterfaceAttachments> ctyunResponse = client.getAllNetworkInterface("cn-gzT","32527975-bbd6-45f8-af19-98d95526008d");
        System.out.println(JSON.toJSONString(ctyunResponse));
    }
}
