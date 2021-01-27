package aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.ecs.model.v20140526.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.junit.Test;

public class VswitchTest {

    @Test
    @SneakyThrows
    public  void createVswitch() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        CreateVSwitchRequest request = new CreateVSwitchRequest();
        request.setRegionId("cn-hangzhou");
        request.setCidrBlock("172.16.0.0/24");
        request.setVpcId("vpc-bp1f74pu5cjwf3mqc3qdf");
        request.setZoneId("cn-hangzhou-b");

        try {
            CreateVSwitchResponse response = client.getAcsResponse(request);
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
    public void deleteVswitch() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<accessKeyId>", "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        DeleteVSwitchRequest request = new DeleteVSwitchRequest();
        request.setRegionId("cn-hangzhou");
        request.setVSwitchId("vsw-bp1s7iah09jahnw9q1jh8");

        try {
            DeleteVSwitchResponse response = client.getAcsResponse(request);
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
    public void getVswitch() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        DescribeVSwitchesRequest request = new DescribeVSwitchesRequest();
        request.setRegionId("cn-hangzhou");
        request.setVSwitchId("vsw-bp161cqgv28dmxqmoxppw");

        try {
            DescribeVSwitchesResponse response = client.getAcsResponse(request);
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

