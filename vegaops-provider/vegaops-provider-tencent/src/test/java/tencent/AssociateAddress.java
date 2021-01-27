package tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vpc.v20170312.VpcClient;
import com.tencentcloudapi.vpc.v20170312.models.AssociateAddressRequest;
import com.tencentcloudapi.vpc.v20170312.models.AssociateAddressResponse;

public class AssociateAddress
{
    public static void main(String [] args) {
        try{

            Credential cred = new Credential("", "");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, "", clientProfile);

            String params = "{}";
            AssociateAddressRequest req = AssociateAddressRequest.fromJsonString(params, AssociateAddressRequest.class);

            AssociateAddressResponse resp = client.AssociateAddress(req);

            System.out.println(AssociateAddressRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

}
