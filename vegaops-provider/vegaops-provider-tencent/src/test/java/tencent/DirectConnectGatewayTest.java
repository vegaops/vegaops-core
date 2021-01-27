package tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vpc.v20170312.VpcClient;
import com.tencentcloudapi.vpc.v20170312.models.*;
import lombok.SneakyThrows;
import org.junit.Test;

public class DirectConnectGatewayTest {

    @Test
    @SneakyThrows
    public void getDcGatewayListTest() {
        try {
            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "ap-beijing", clientProfile);

            String params = "{}";
            DescribeDirectConnectGatewaysRequest req = DescribeDirectConnectGatewaysRequest.fromJsonString(params, DescribeDirectConnectGatewaysRequest.class);

            DescribeDirectConnectGatewaysResponse resp = client.DescribeDirectConnectGateways(req);

            System.out.println(DescribeDirectConnectGatewaysRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

    @Test
    @SneakyThrows
    public void CreateDcGateway(){
        try{
            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "", clientProfile);

            String params = "{\"DirectConnectGatewayName\":\"aa\",\"NetworkType\":\"aa\",\"NetworkInstanceId\":\"aa\"}";
            CreateDirectConnectGatewayRequest req = CreateDirectConnectGatewayRequest.fromJsonString(params, CreateDirectConnectGatewayRequest.class);

            CreateDirectConnectGatewayResponse resp = client.CreateDirectConnectGateway(req);

            System.out.println(CreateDirectConnectGatewayRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    @SneakyThrows
    public void DeleteDcGateway(){
        try{

            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "ap-beijing", clientProfile);

            String params = "{\"DirectConnectGatewayId\":\"dcg-9o233ur\"}";
            DeleteDirectConnectGatewayRequest req = DeleteDirectConnectGatewayRequest.fromJsonString(params, DeleteDirectConnectGatewayRequest.class);

            DeleteDirectConnectGatewayResponse resp = client.DeleteDirectConnectGateway(req);

            System.out.println(DeleteDirectConnectGatewayRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }
}