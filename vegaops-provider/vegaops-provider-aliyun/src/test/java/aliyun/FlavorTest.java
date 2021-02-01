package aliyun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.ecs.model.v20140526.DescribeAvailableResourceRequest;
import com.aliyuncs.ecs.model.v20140526.DescribeAvailableResourceResponse;
import com.aliyuncs.ecs.model.v20140526.DescribeInstanceTypesRequest;
import com.aliyuncs.ecs.model.v20140526.DescribeInstanceTypesResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.junit.Test;

public class FlavorTest {


    @Test
    @SneakyThrows
    public void getFlavor() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        DescribeInstanceTypesRequest request = new DescribeInstanceTypesRequest();
        request.setRegionId("cn-hangzhou");

        try {
            DescribeInstanceTypesResponse response = client.getAcsResponse(request);
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
    public void getAvailableFlavor() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        DescribeAvailableResourceRequest request = new DescribeAvailableResourceRequest();
        request.setRegionId("cn-qingdao");
        request.setIoOptimized("optimized");
        request.setDestinationResource("InstanceType");
        request.setZoneId("cn-qingdao-b");
        try {
            DescribeAvailableResourceResponse acsResponse = client.getAcsResponse(request);
            System.out.println(JSON.toJSONString(acsResponse, SerializerFeature.PrettyFormat));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }

    }


}

