package aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.cbn.model.v20170912.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.junit.Test;

public class CenInstanceTest {


    @Test
    @SneakyThrows
    public void getCenInstances() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        DescribeCenAttachedChildInstancesRequest request = new DescribeCenAttachedChildInstancesRequest();
        request.setCenId("cen-g8excp4rt31u5inh5g");
        try {
            DescribeCenAttachedChildInstancesResponse response = client.getAcsResponse(request);
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
    public void getCenInstanceDetail() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        DescribeCenAttachedChildInstanceAttributeRequest request = new DescribeCenAttachedChildInstanceAttributeRequest();
        request.setCenId("cen-g8excp4rt31u5inh5g");
        request.setChildInstanceId("vbr-2zepp6fxe0v7c5jdkutbf");
        request.setChildInstanceType("VBR");
        request.setChildInstanceRegionId("cn-beijing");
        try {
            DescribeCenAttachedChildInstanceAttributeResponse response = client.getAcsResponse(request);
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
    public void attachCenInstances() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        AttachCenChildInstanceRequest request = new AttachCenChildInstanceRequest();
        request.setCenId("cen-g8excp4rt31u5inh5g");
        request.setChildInstanceId("vbr-2zepp6fxe0v7c5jdkutbf");
        request.setChildInstanceType("VBR");
        request.setChildInstanceRegionId("cn-beijing");

        try {
            AttachCenChildInstanceResponse response = client.getAcsResponse(request);
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
    public void detachCenInstance() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        DetachCenChildInstanceRequest request = new DetachCenChildInstanceRequest();
        request.setCenId("cen-g8excp4rt31u5inh5g");
        request.setChildInstanceId("vbr-2zepp6fxe0v7c5jdkutbf");
        request.setChildInstanceType("VBR");
        request.setChildInstanceRegionId("cn-beijing");


        try {
            DetachCenChildInstanceResponse response = client.getAcsResponse(request);
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

