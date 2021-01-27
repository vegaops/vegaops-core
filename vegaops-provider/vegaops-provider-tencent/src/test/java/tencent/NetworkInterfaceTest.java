package tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vpc.v20170312.VpcClient;
import com.tencentcloudapi.vpc.v20170312.models.*;
import lombok.SneakyThrows;
import org.junit.Test;

public class NetworkInterfaceTest {

    @Test
    @SneakyThrows
    public void getNetworkInterfaceListTest() {
        try{

            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "ap-beijing", clientProfile);

            String params = "{}";
            DescribeNetworkInterfacesRequest req = DescribeNetworkInterfacesRequest.fromJsonString(params, DescribeNetworkInterfacesRequest.class);

            DescribeNetworkInterfacesResponse resp = client.DescribeNetworkInterfaces(req);

            System.out.println(DescribeNetworkInterfacesRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

    @Test
    @SneakyThrows
    public void CreateNetworkInterface(){
        try{

            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "ap-beijing", clientProfile);

            String params = "{\"VpcId\":\"vpc-gfmx67be\",\"NetworkInterfaceName\":\"xjh-test\",\"SubnetId\":\"subnet-4h3llcc1\"}";
            CreateNetworkInterfaceRequest req = CreateNetworkInterfaceRequest.fromJsonString(params, CreateNetworkInterfaceRequest.class);

            CreateNetworkInterfaceResponse resp = client.CreateNetworkInterface(req);

            System.out.println(CreateNetworkInterfaceRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    @SneakyThrows
    public void DeleteNetworkInterface(){
        try{

            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "ap-beijing", clientProfile);

            String params = "{\"NetworkInterfaceId\":\"eni-6ycibyhy\"}";
            DeleteNetworkInterfaceRequest req = DeleteNetworkInterfaceRequest.fromJsonString(params, DeleteNetworkInterfaceRequest.class);

            DeleteNetworkInterfaceResponse resp = client.DeleteNetworkInterface(req);

            System.out.println(DeleteNetworkInterfaceRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

}