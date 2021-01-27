package tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vpc.v20170312.VpcClient;
import com.tencentcloudapi.vpc.v20170312.models.*;
import lombok.SneakyThrows;
import org.junit.Test;

public class VpcTest {

    @Test
    @SneakyThrows
    public void getVpcListTest() {
        try {
            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "ap-guangzhou", clientProfile);

            String params = "{}";
            DescribeVpcsRequest req = DescribeVpcsRequest.fromJsonString(params, DescribeVpcsRequest.class);

            DescribeVpcsResponse resp = client.DescribeVpcs(req);

            System.out.println(DescribeVpcsRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

    @Test
    @SneakyThrows
    public void CreateVpc(){
        try{
            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "ap-guangzhou", clientProfile);

            String params = "{\"VpcName\":\"xjh-test\",\"CidrBlock\":\"10.0.0.0/16\"}";
            CreateVpcRequest req = CreateVpcRequest.fromJsonString(params, CreateVpcRequest.class);

            CreateVpcResponse resp = client.CreateVpc(req);

            System.out.println(CreateVpcRequest.toJsonString(resp));
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
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "ap-guangzhou", clientProfile);

            String params = "{\"VpcId\":\"vpc-7r2mwp1n\"}";
            DeleteVpcRequest req = DeleteVpcRequest.fromJsonString(params, DeleteVpcRequest.class);

            DeleteVpcResponse resp = client.DeleteVpc(req);

            System.out.println(DeleteVpcRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }
}