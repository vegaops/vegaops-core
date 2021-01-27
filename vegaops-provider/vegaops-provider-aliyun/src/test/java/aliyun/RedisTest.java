package aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.r_kvstore.model.v20150101.*;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.junit.Test;

public class RedisTest {

    @Test
    @SneakyThrows
    public  void createRedis() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-qingdao", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        CreateInstanceRequest request = new CreateInstanceRequest();
        request.setRegionId("cn-qingdao");
        request.setInstanceName("vegaopsTest");
        request.setPassword("Abc999@1");
        request.setInstanceClass("redis.master.small.default");
        request.setZoneId("cn-qingdao-b");
        request.setChargeType("PostPaid");
        request.setNodeType("MASTER_SLAVE");
        request.setInstanceType("Redis");
        request.setEngineVersion("5.0");

        try {
            CreateInstanceResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }

    }

    @Test
    @SneakyThrows
    public void deleteRedis() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        DeleteInstanceRequest request = new DeleteInstanceRequest();
        request.setRegionId("cn-hangzhou");
        request.setInstanceId("r-j6cxxxxxxxxxxxxx");

        try {
            DeleteInstanceResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }

    @Test
    @SneakyThrows
    public void getRedis() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-wulanchabu", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        DescribeInstancesRequest request = new DescribeInstancesRequest();
        request.setRegionId("cn-wulanchabu");

        try {
            DescribeInstancesResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }

    }

    @Test
    @SneakyThrows
    public void getRedisAvaliableRegion() {
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

