package aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vpc.model.v20160428.DescribeEipAddressesRequest;
import com.aliyuncs.vpc.model.v20160428.DescribeEipAddressesResponse;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.junit.Test;

public class EipVpcTest {

    @Test
    @SneakyThrows
    public void getEip() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-beijing", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        DescribeEipAddressesRequest request = new DescribeEipAddressesRequest();

        try {
            DescribeEipAddressesResponse response = client.getAcsResponse(request);
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

