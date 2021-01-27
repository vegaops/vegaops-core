package aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vpc.model.v20160428.*;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.junit.Test;

public class RouteTableTest {


    @Test
    @SneakyThrows
    public void getRouteTable() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        DescribeRouteTablesRequest request = new DescribeRouteTablesRequest();
        request.setRegionId("cn-hangzhou");

        try {
            DescribeRouteTablesResponse response = client.getAcsResponse(request);
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
    public void createRouteTable() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        CreateRouteTableRequest request = new CreateRouteTableRequest();
        request.setRegionId("cn-hangzhou");

        try {
            CreateRouteTableResponse response = client.getAcsResponse(request);
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
    public void deleteRouteTable() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        DeleteRouteTableRequest request = new DeleteRouteTableRequest();
        request.setRegionId("cn-hangzhou");

        try {
            DeleteRouteTableResponse response = client.getAcsResponse(request);
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
    public void describeVirtualBorderRouter() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-beijing", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        DescribeVirtualBorderRoutersRequest request = new DescribeVirtualBorderRoutersRequest();
        request.setRegionId("cn-hangzhou");

        try {
            DescribeVirtualBorderRoutersResponse response = client.getAcsResponse(request);
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

