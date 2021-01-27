package tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.cvm.v20170312.CvmClient;
import com.tencentcloudapi.cvm.v20170312.models.DescribeImagesRequest;
import com.tencentcloudapi.cvm.v20170312.models.DescribeImagesResponse;
import lombok.SneakyThrows;
import org.junit.Test;

public class ImageTest {

    @Test
    @SneakyThrows
    public void getImageListTest() {
        try {

            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cvm.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CvmClient client = new CvmClient(cred, "ap-beijing", clientProfile);

            String params = "{}";
            DescribeImagesRequest req = DescribeImagesRequest.fromJsonString(params, DescribeImagesRequest.class);

            DescribeImagesResponse resp = client.DescribeImages(req);

            System.out.println(DescribeImagesRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

}