package aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.ecs.model.v20140526.DescribeZonesRequest;
import com.aliyuncs.ecs.model.v20140526.DescribeZonesResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.junit.Test;

public class ZoneTest {

    @Test
    @SneakyThrows
    public  void getRegionList() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        DescribeZonesRequest request = new DescribeZonesRequest();

        try {
            DescribeZonesResponse response = client.getAcsResponse(request);
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

