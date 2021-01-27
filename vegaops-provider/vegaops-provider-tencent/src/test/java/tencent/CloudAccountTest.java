package tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.organization.v20181225.OrganizationClient;
import com.tencentcloudapi.organization.v20181225.models.ListOrganizationMembersRequest;
import com.tencentcloudapi.organization.v20181225.models.ListOrganizationMembersResponse;
import com.tencentcloudapi.sts.v20180813.StsClient;
import com.tencentcloudapi.sts.v20180813.models.AssumeRoleRequest;
import com.tencentcloudapi.sts.v20180813.models.AssumeRoleResponse;
import lombok.SneakyThrows;
import org.junit.Test;

public class CloudAccountTest {

    private static String key = "";
    private static String secret = "";

    @Test
    @SneakyThrows
    public void list() {
    }

    @Test
    @SneakyThrows
    public void member() {
        try {

            Credential cred = new Credential(key, secret);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("organization.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            OrganizationClient client = new OrganizationClient(cred, "", clientProfile);

            ListOrganizationMembersRequest req = new ListOrganizationMembersRequest();

            ListOrganizationMembersResponse resp = client.ListOrganizationMembers(req);

            System.out.println(ListOrganizationMembersRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    @SneakyThrows
    public void assumeUser(){
//        Credential cred = new Credential("xxxxx", "xxxxx");
        Credential cred = new Credential("xxxxx", "xxxxx");

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("sts.tencentcloudapi.com");

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        StsClient client = new StsClient(cred, "ap-beijing", clientProfile);

        AssumeRoleRequest request = new AssumeRoleRequest();
        request.setRoleArn("qcs::cam::uin/100015342139:roleName/channel_QcsSpRole");
//        request.setDurationSeconds();
//        request.setPolicy();
        request.setRoleSessionName("channelCUCC");
        AssumeRoleResponse response = client.AssumeRole(request);
        response.getCredentials();

    }

}