package tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vpc.v20170312.VpcClient;
import com.tencentcloudapi.vpc.v20170312.models.AllocateAddressesRequest;
import com.tencentcloudapi.vpc.v20170312.models.AllocateAddressesResponse;

public class AllocateAddresses
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
            AllocateAddressesRequest req = AllocateAddressesRequest.fromJsonString(params, AllocateAddressesRequest.class);
           String t = "T(org.prophetech.hyperone.vegaops.tencent.util.RequestBuilder).build().put(\"InternetServiceProvider\", #internetServiceProvider).put(\"InternetChargeType\", #internetChargeType).put(\"InternetMaxBandwidthOut\", #internetMaxBandwidthOut).put(\"AddressType\", #addressType).put(\"AnycastZone\", #zone).put(\"ApplicableForCLB\", #applicableForCLB).build(T(Class).forName(\"com.tencentcloudapi.vpc.v20170312.models.AllocateAddressesRequest\"))";

            AllocateAddressesResponse resp = client.AllocateAddresses(req);

            System.out.println(AllocateAddressesRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

}
