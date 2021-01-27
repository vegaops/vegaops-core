package tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.cvm.v20170312.CvmClient;
import com.tencentcloudapi.cvm.v20170312.models.*;
import lombok.SneakyThrows;
import org.junit.Test;

public class KeyPairTest {

    @Test
    @SneakyThrows
    public void getKeyPairListTest() {
        try{

            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cvm.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CvmClient client = new CvmClient(cred, "ap-beijing", clientProfile);

            DescribeKeyPairsRequest req = new DescribeKeyPairsRequest();

            DescribeKeyPairsResponse resp = client.DescribeKeyPairs(req);

            System.out.println(DescribeKeyPairsRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    @SneakyThrows
    public void CreateKeypairTest(){
        try{

            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cvm.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CvmClient client = new CvmClient(cred, "ap-beijing", clientProfile);

            String params = "{\"KeyName\":\"xjhtest\",\"ProjectId\":0}";
            CreateKeyPairRequest req = CreateKeyPairRequest.fromJsonString(params, CreateKeyPairRequest.class);

            CreateKeyPairResponse resp = client.CreateKeyPair(req);
            System.out.println(CreateKeyPairRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

    @Test
    @SneakyThrows
    public void DeleteKeyPairTest(){
        try{

            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cvm.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CvmClient client = new CvmClient(cred, "ap-beijing", clientProfile);

            String params = "{\"KeyIds\":[\"skey-dou3l3vj\"]}";
            DeleteKeyPairsRequest req = DeleteKeyPairsRequest.fromJsonString(params, DeleteKeyPairsRequest.class);

            DeleteKeyPairsResponse resp = client.DeleteKeyPairs(req);

            System.out.println(DeleteKeyPairsRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    @SneakyThrows
    public void BindKeypairTest(){
        try{

            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cvm.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CvmClient client = new CvmClient(cred, "ap-shanghai", clientProfile);

            String params = "{\"InstanceIds\":[\"ins-6ahxfyq3\"],\"KeyIds\":[\"skey-k0xv47fd\"]}";
            AssociateInstancesKeyPairsRequest req = AssociateInstancesKeyPairsRequest.fromJsonString(params, AssociateInstancesKeyPairsRequest.class);

            AssociateInstancesKeyPairsResponse resp = client.AssociateInstancesKeyPairs(req);

            System.out.println(AssociateInstancesKeyPairsRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

    @Test
    @SneakyThrows
    public void UnBindKeypairTest(){
        try{

            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cvm.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CvmClient client = new CvmClient(cred, "ap-shanghai", clientProfile);

            String params = "{\"InstanceIds\":[\"ins-6ahxfyq3\"],\"KeyIds\":[\"skey-k0xv47fd\"]}";
            DisassociateInstancesKeyPairsRequest req = DisassociateInstancesKeyPairsRequest.fromJsonString(params, DisassociateInstancesKeyPairsRequest.class);

            DisassociateInstancesKeyPairsResponse resp = client.DisassociateInstancesKeyPairs(req);

            System.out.println(DisassociateInstancesKeyPairsRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

}