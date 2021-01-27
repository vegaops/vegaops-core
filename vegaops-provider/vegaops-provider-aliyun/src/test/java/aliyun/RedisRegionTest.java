package aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.r_kvstore.model.v20150101.DescribeRegionsRequest;
import com.aliyuncs.r_kvstore.model.v20150101.DescribeRegionsResponse;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.junit.Test;

public class RedisRegionTest {

    @Test
    @SneakyThrows
    public  void getRegionList() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        DescribeRegionsRequest request = new DescribeRegionsRequest();

        try {
            DescribeRegionsResponse response = client.getAcsResponse(request);
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

