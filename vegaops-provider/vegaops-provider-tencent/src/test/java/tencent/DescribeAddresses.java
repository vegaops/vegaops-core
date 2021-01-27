package tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vpc.v20170312.VpcClient;
import com.tencentcloudapi.vpc.v20170312.models.DescribeAddressesRequest;
import com.tencentcloudapi.vpc.v20170312.models.DescribeAddressesResponse;

public class DescribeAddresses
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
            DescribeAddressesRequest req = DescribeAddressesRequest.fromJsonString(params, DescribeAddressesRequest.class);

            DescribeAddressesResponse resp = client.DescribeAddresses(req);

            System.out.println(DescribeAddressesRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

}
