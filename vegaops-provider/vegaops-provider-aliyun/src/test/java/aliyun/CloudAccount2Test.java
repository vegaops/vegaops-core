package aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.bssopenapi.model.v20171214.*;
import com.aliyuncs.crm.model.v20150408.QueryBidUserCertifiedInfoRequest;
import com.aliyuncs.crm.model.v20150408.QueryBidUserCertifiedInfoResponse;
import com.aliyuncs.crm.model.v20150408.RemoveIdentityCertifiedForBidUserRequest;
import com.aliyuncs.crm.model.v20150408.RemoveIdentityCertifiedForBidUserResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.ram.model.v20150501.GetUserRequest;
import com.aliyuncs.ram.model.v20150501.GetUserResponse;
import lombok.SneakyThrows;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Test;

public class CloudAccount2Test {



    @Test
    @SneakyThrows
    public void getCustomerList() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);
        GetCustomerListRequest request = new GetCustomerListRequest();
        GetCustomerListResponse response = client.getAcsResponse(request);
        response.getData();
    }

    @Test
    @SneakyThrows
    public void getSigninToken(){

        String key = "STS.NTa68Q6wQTsdThHFRqiUWLa88";
        String secret = "xxxxx";
        String token = "CAISiQJ1q6Ft5B2yfSjIr5fUfYLl26hw47GPVk75olIkZdp7o6ST2jz2IHBJenJqCOEdsv8wlGhW7f0flrkqEM4YFR2bNpIotMwKr1P/O0BxM3pjq+5qsoasPETOITyZtZagToeUZdfZfejXGDKgvyRvwLz8WCy/Vli+S/OggoJmadJlNWvRL0AxZrFsKxBltdUROFbIKP+pKWSKuGfLC1dysQcO4gEWq4bHm5bNt0qP0wajkrVN+tqhfKLJNZc8YM1NNP6ux/Fze6b71ypd1gNH7q8ejtYfqWqZ5YvMUgINuErWa7SPqYB1XgZ9Z7knHa9I6fHz0Odxv6nJkID6jh9BOOBTQ/4ImlArdESuGoABD0/rTpzD7Wxypn/4cUql95Sjd71ZMg8YDpllJg0Xr5vgBdDf0pOHl/mpaXqzuVueUPWZUod0QcJgnmvOEqTBjUwzjRxFK1bPXKkyODk3GMAlRdDJE5SMK/U9h1X7IFG7BupAVnVHVvqQedo82EE3E1YUfwus+5AMMbb9nN65WqU=";

        String url = "https://signin.aliyun.com/federation?Action=GetSigninToken" +
                "&AccessKeyId=" + key +
                "&AccessKeySecret=" + secret +
                "&SecurityToken=" + token +
                "&TicketType=mini";
        Connection connect = Jsoup.connect(url);
        connect.header("Content-Type", "x-www-form-urlencoded");
        connect.method(Connection.Method.GET);
        connect.ignoreContentType(true);
        connect.ignoreHttpErrors(true);
        Connection.Response execute = null;
        execute = connect.execute();
        execute.body();
    }

    @Test
    @SneakyThrows
    public void login(){

        String token = "";

        String url = String.format("https://signin.aliyun.com/federation?Action=Login" +
                "&LoginUrl=https%3a%2f%2flogin.samplecompany.com%2flogin_aliyun.php" +
                "&Destination=https://homenew.console.aliyun.com" +
                "&SigninToken=5aW96ZW/5a*******9rZW7lkYA=     ", token);
        Connection connect = Jsoup.connect(url);
        connect.header("Content-Type", "x-www-form-urlencoded");
        connect.method(Connection.Method.GET);
        connect.ignoreContentType(true);
        connect.ignoreHttpErrors(true);
        Connection.Response execute = null;
        execute = connect.execute();
        execute.body();

    }

    @Test
    @SneakyThrows
    public void queryIdentityCertifiedForBidUser() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);
        QueryBidUserCertifiedInfoRequest request = new QueryBidUserCertifiedInfoRequest();
        request.setSysEndpoint("crm-cn-hangzhou.aliyuncs.com");
        request.setPK("1442098325661561");
        request.setBidType("LIGHTWEIGHT");

        try {
            QueryBidUserCertifiedInfoResponse response = client.getAcsResponse(request);
            response.getRequestId();
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
    public void deleteIdentityCertifiedForBidUser() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);
        RemoveIdentityCertifiedForBidUserRequest request = new RemoveIdentityCertifiedForBidUserRequest();
        request.setSysEndpoint("crm-cn-hangzhou.aliyuncs.com");
        request.setPK("1395897310205686");
        request.setBidType("LIGHTWEIGHT");

        try {
            RemoveIdentityCertifiedForBidUserResponse response = client.getAcsResponse(request);
            response.getRequestId();
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
    public void queryUser() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);
        GetUserRequest request = new GetUserRequest();
        request.setUserName("WeiChen");
        GetUserResponse response = client.getAcsResponse(request);
        response.getUser();
    }


    @Test
    @SneakyThrows
    public void queryResellerUserQuota() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);
        QueryResellerAvailableQuotaRequest request = new QueryResellerAvailableQuotaRequest();
        request.setOwnerId(1442098325661561L);
        request.setItemCodes("[\"CREDIT_QUOTA_BOOK_ITEM\",\"CREDIT_CONSUME_BOOK_ITEM\"]");
        QueryResellerAvailableQuotaResponse response = client.getAcsResponse(request);
        response.getRequestId();
    }


    @Test
    @SneakyThrows
    public void setResellerUserQuota() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);
        SetResellerUserQuotaRequest request = new SetResellerUserQuotaRequest();
        request.setOwnerId(1395897310205686L);
        request.setAmount("2000");
        request.setCurrency("CNY");
        SetResellerUserQuotaResponse response = client.getAcsResponse(request);
        response.getRequestId();
    }


    @Test
    @SneakyThrows
    public void queryInstanceBill() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);
        QueryInstanceBillRequest request = new QueryInstanceBillRequest();
//        request.setOwnerId(1395897310205686L);
        request.setBillingCycle("2020-08");
        QueryInstanceBillResponse response = client.getAcsResponse(request);
        response.getRequestId();
    }

    @Test
    @SneakyThrows
    public void changeResellerUserQuota() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);
        ChangeResellerConsumeAmountRequest request = new ChangeResellerConsumeAmountRequest();
        request.setOwnerId(1442098325661561L);
        request.setAdjustType("decrease");
        request.setAmount("100");
        request.setCurrency("CNY");
        request.setBusinessType("TRUSTEESHIP");
        request.setSource("credit");
        request.setOutBizId("cb3*******40b");
        ChangeResellerConsumeAmountResponse response = client.getAcsResponse(request);
        response.getRequestId();
    }
}
