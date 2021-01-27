package tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vpc.v20170312.VpcClient;
import com.tencentcloudapi.vpc.v20170312.models.*;
import lombok.SneakyThrows;
import org.junit.Test;

public class RouteTableEntryTest {

    private String key = "xxxxx";

    private String secret = "xxxxx";

    @Test
    @SneakyThrows
    public void getRouteListTest() {
        try {

            Credential cred = new Credential("", "");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "", clientProfile);

            CreateRoutesRequest req = new CreateRoutesRequest();

            CreateRoutesResponse resp = client.CreateRoutes(req);

            System.out.println(CreateRoutesRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

    @Test
    @SneakyThrows
    public void createRouteTable() {
        try {

            Credential cred = new Credential(key, secret);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "ap-beijing", clientProfile);

            CreateRouteTableRequest req = new CreateRouteTableRequest();

            req.setRouteTableName("");
            req.setVpcId("");

            CreateRouteTableResponse resp = client.CreateRouteTable(req);

            System.out.println(CreateRouteTableRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    @SneakyThrows
    public void deleteRouteTable() {
        try {

            Credential cred = new Credential("", "");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "", clientProfile);

            String params = "{}";
            DeleteRoutesRequest req = DeleteRoutesRequest.fromJsonString(params, DeleteRoutesRequest.class);

            DeleteRoutesResponse resp = client.DeleteRoutes(req);

            System.out.println(DeleteRoutesRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }
}