package aliyun;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.ecs.model.v20140526.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.ram.model.v20150501.CreateRoleRequest;
import com.aliyuncs.ram.model.v20150501.CreateRoleResponse;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.Arrays;

public class InstanceTest {

    @Test
    @SneakyThrows
    public void list() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-qingdao", "xxxx", "xxxx");

        DefaultAcsClient client = new DefaultAcsClient(profile);
        DescribeInstancesRequest request = new DescribeInstancesRequest();
//        request.setPageNumber(2);
//        request.setPageSize(100);

        request.setInstanceIds(JSON.toJSONString(Arrays.asList("i-m5e0lghgjbelk9xifr3b")));
        try {
            DescribeInstancesResponse response = client.getAcsResponse(request);
            System.out.println(JSON.toJSONString(response));
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
    public void query() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-qingdao", "xxxxx", "xxxxx");

        DefaultAcsClient client = new DefaultAcsClient(profile);

        DescribeInstancesRequest request = new DescribeInstancesRequest();
        try {
            DescribeInstancesResponse response = client.getAcsResponse(request);
            System.out.println(JSON.toJSONString(response));
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
    public void create() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxx", "xxxxx");
        DefaultAcsClient client = new DefaultAcsClient(profile);


        RunInstancesRequest request = new RunInstancesRequest();
        request.setImageId("centos_6_09_64_20G_alibase_20180326.vhd");
        request.setInstanceType("ecs.n1.tiny");
        request.setSecurityGroupId("sg-m5effiy6dveokioud00h");
        request.setVSwitchId("vsw-m5elvlnonmfqfcoc8mktj");
        request.setInstanceName("xjhtest");
        request.setZoneId("cn-qingdao-b");
        request.setInternetChargeType("PayByTraffic");

        try {
            RunInstancesResponse response = client.getAcsResponse(request);
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
    public void update() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        DefaultAcsClient client = new DefaultAcsClient(profile);

        ModifyInstanceSpecRequest request = new ModifyInstanceSpecRequest();

        try {
            ModifyInstanceSpecResponse response = client.getAcsResponse(request);
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
    public void updatePrepaid() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        DefaultAcsClient client = new DefaultAcsClient(profile);

        ModifyPrepayInstanceSpecRequest request = new ModifyPrepayInstanceSpecRequest();
        request.setRegionId("cn-hangzhou");

        try {
            ModifyPrepayInstanceSpecResponse response = client.getAcsResponse(request);
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
    public void flavorList() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-zhangjiakou", "xxxxx", "xxxxx");
        DefaultAcsClient client = new DefaultAcsClient(profile);

        DescribeResourcesModificationRequest request = new DescribeResourcesModificationRequest();
        request.setRegionId("cn-zhangjiakou");
        request.setResourceId("i-8vbcthzqpmrj0380517v");
        request.setDestinationResource("InstanceType");
        request.setOperationType("Upgrade");
        request.setCores(2);
        request.setMemory(4f);

        try {
            DescribeResourcesModificationResponse response = client.getAcsResponse(request);
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
    public void createRole(){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        CreateRoleRequest request = new CreateRoleRequest();
        request.setRoleName("zhangDeShuai");
        request.setAssumeRolePolicyDocument("{\"Statement\":[{\"Action\":\"sts:AssumeRole\",\"Effect\":\"Allow\",\"Principal\":{\"RAM\":\"acs:ram::1298452886129592:root\"}}],\"Version\":\"1\"}");

        try {
            CreateRoleResponse response = client.getAcsResponse(request);
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
