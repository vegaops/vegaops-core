package aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.slb.model.v20140515.DescribeAccessControlListsRequest;
import com.aliyuncs.slb.model.v20140515.DescribeAccessControlListsResponse;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.junit.Test;

public class SlbAclTest {

    @Test
    @SneakyThrows
    public void getSlbAcl() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        DescribeAccessControlListsRequest request = new DescribeAccessControlListsRequest();
        request.setRegionId("cn-hangzhou");

        try {
            DescribeAccessControlListsResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }


}

