package tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vpc.v20170312.VpcClient;
import com.tencentcloudapi.vpc.v20170312.models.*;
import lombok.SneakyThrows;
import org.junit.Test;

public class VswitchTest {

    @Test
    @SneakyThrows
    public void getVswitchListTest() {
        try{
            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "ap-beijing", clientProfile);

            String params = "{}";
            DescribeSubnetsRequest req = DescribeSubnetsRequest.fromJsonString(params, DescribeSubnetsRequest.class);

            DescribeSubnetsResponse resp = client.DescribeSubnets(req);

            System.out.println(DescribeSubnetsRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

    @Test
    @SneakyThrows
    public void CreateVswitch(){
        try{
            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "ap-beijing", clientProfile);

            String params = "{\"VpcId\":\"vpc-gfmx67be\",\"SubnetName\":\"xjh-test\",\"CidrBlock\":\"10.0.0.0/16\",\"Zone\":\"ap-guangzhou-1\"}";
            CreateSubnetRequest req = CreateSubnetRequest.fromJsonString(params, CreateSubnetRequest.class);

            CreateSubnetResponse resp = client.CreateSubnet(req);

            System.out.println(CreateSubnetRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    @SneakyThrows
    public void DeleteVswitch(){
        try{
            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "ap-beijing", clientProfile);

            String params = "{\"SubnetId\":\"subnet-kg08r8n3\"}";
            DeleteSubnetRequest req = DeleteSubnetRequest.fromJsonString(params, DeleteSubnetRequest.class);

            DeleteSubnetResponse resp = client.DeleteSubnet(req);

            System.out.println(DeleteSubnetRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }
}