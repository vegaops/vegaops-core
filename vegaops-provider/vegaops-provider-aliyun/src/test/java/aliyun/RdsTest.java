package aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.rds.model.v20140815.*;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.junit.Test;

public class RdsTest {

    @Test
    @SneakyThrows
    public  void createRds() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        CreateDBInstanceRequest request = new CreateDBInstanceRequest();
        request.setRegionId("cn-hangzhou");
        request.setEngine("MySQL");
        request.setEngineVersion("5.5/5.6/5.7/8.0");
        request.setDBInstanceClass("mysql.n1.micro.1");
        request.setDBInstanceStorage(20);
        request.setDBInstanceNetType("Internet");
        request.setSecurityIPList("10.23.12.24");
        request.setPayType("Postpaid");

        try {
            CreateDBInstanceResponse response = client.getAcsResponse(request);
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
    public void deleteRds() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        DeleteDBInstanceRequest request = new DeleteDBInstanceRequest();
        request.setRegionId("cn-hangzhou");
        request.setDBInstanceId("rm-uf6wjk5xxxxxxxxxx");

        try {
            DeleteDBInstanceResponse response = client.getAcsResponse(request);
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
    public void getRds() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-wulanchabu", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        DescribeDBInstancesRequest request = new DescribeDBInstancesRequest();
        //request.setRegionId("cn-hangzhou");

        try {
            DescribeDBInstancesResponse response = client.getAcsResponse(request);
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

