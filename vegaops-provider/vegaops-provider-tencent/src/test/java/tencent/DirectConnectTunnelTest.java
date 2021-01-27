package tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.dc.v20180410.DcClient;
import com.tencentcloudapi.dc.v20180410.models.*;
import lombok.SneakyThrows;
import org.junit.Test;

public class DirectConnectTunnelTest {

    @Test
    @SneakyThrows
    public void getDctListTest() {
        try {
            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("dc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            DcClient client = new DcClient(cred, "ap-beijing", clientProfile);

            String params = "{}";
            DescribeDirectConnectTunnelsRequest req = DescribeDirectConnectTunnelsRequest.fromJsonString(params, DescribeDirectConnectTunnelsRequest.class);

            DescribeDirectConnectTunnelsResponse resp = client.DescribeDirectConnectTunnels(req);

            System.out.println(DescribeDirectConnectTunnelsRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

    @Test
    @SneakyThrows
    public void CreateDct(){
        try{
            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("dc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            DcClient client = new DcClient(cred, "", clientProfile);

            String params = "{}";
            CreateDirectConnectTunnelRequest req = CreateDirectConnectTunnelRequest.fromJsonString(params, CreateDirectConnectTunnelRequest.class);

            CreateDirectConnectTunnelResponse resp = client.CreateDirectConnectTunnel(req);

            System.out.println(CreateDirectConnectTunnelRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    @SneakyThrows
    public void DeleteVpc(){
        try{

            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("dc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            DcClient client = new DcClient(cred, "ap-beijing", clientProfile);

            String params = "{}";
            DeleteDirectConnectTunnelRequest req = DeleteDirectConnectTunnelRequest.fromJsonString(params, DeleteDirectConnectTunnelRequest.class);

            DeleteDirectConnectTunnelResponse resp = client.DeleteDirectConnectTunnel(req);

            System.out.println(DeleteDirectConnectTunnelRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }
}