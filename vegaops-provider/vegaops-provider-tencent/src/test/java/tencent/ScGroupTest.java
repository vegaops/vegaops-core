package tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.cvm.v20170312.CvmClient;
import com.tencentcloudapi.cvm.v20170312.models.AssociateSecurityGroupsRequest;
import com.tencentcloudapi.cvm.v20170312.models.AssociateSecurityGroupsResponse;
import com.tencentcloudapi.cvm.v20170312.models.DisassociateSecurityGroupsRequest;
import com.tencentcloudapi.cvm.v20170312.models.DisassociateSecurityGroupsResponse;
import com.tencentcloudapi.vpc.v20170312.VpcClient;
import com.tencentcloudapi.vpc.v20170312.models.DescribeSecurityGroupsRequest;
import com.tencentcloudapi.vpc.v20170312.models.DescribeSecurityGroupsResponse;
import lombok.SneakyThrows;
import org.junit.Test;

public class ScGroupTest {

    @Test
    @SneakyThrows
    public void getScGroupListTest() {
        try{

            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "ap-beijing", clientProfile);

            String params = "{}";
            DescribeSecurityGroupsRequest req = DescribeSecurityGroupsRequest.fromJsonString(params, DescribeSecurityGroupsRequest.class);

            DescribeSecurityGroupsResponse resp = client.DescribeSecurityGroups(req);

            System.out.println(DescribeSecurityGroupsRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

    @Test
    @SneakyThrows
    public void bindScGroupTest(){
        try{

            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cvm.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CvmClient client = new CvmClient(cred, "ap-beijing", clientProfile);

            String params = "{\"SecurityGroupIds\":[\"sg-9selks2f\"],\"InstanceIds\":[\"ins-ntubkkdn\"]}";
            AssociateSecurityGroupsRequest req = AssociateSecurityGroupsRequest.fromJsonString(params, AssociateSecurityGroupsRequest.class);

            AssociateSecurityGroupsResponse resp = client.AssociateSecurityGroups(req);

            System.out.println(AssociateSecurityGroupsRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    @SneakyThrows
    public void UnbindScGroupTest(){
        try{

            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cvm.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CvmClient client = new CvmClient(cred, "ap-beijing", clientProfile);

            String params = "{\"SecurityGroupIds\":[\"sg-9selks2f\"],\"InstanceIds\":[\"ins-ntubkkdn\"]}";
            DisassociateSecurityGroupsRequest req = DisassociateSecurityGroupsRequest.fromJsonString(params, DisassociateSecurityGroupsRequest.class);

            DisassociateSecurityGroupsResponse resp = client.DisassociateSecurityGroups(req);

            System.out.println(DisassociateSecurityGroupsRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

}