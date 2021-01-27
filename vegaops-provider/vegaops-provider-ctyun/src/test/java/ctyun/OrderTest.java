package ctyun;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.ctyun.client.CtyunJsoupClient;
import org.prophetech.hyperone.vegaops.ctyun.model.*;

import java.util.Arrays;


public class OrderTest {
    private static CtyunAccount ctyunAccount = new CtyunAccount("xxxxx", "xxxxx");


    @Test
    @SneakyThrows
    public void payOrder() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        QueryOrdersRequest request = new QueryOrdersRequest();
        request.setPageNo(1);
        request.setPageSize(100);
        CtyunApiObjectResponse<QueryOrdersResponse> ctyunResponse = (CtyunApiObjectResponse)client.getCtyunResponse(request);
//        CancelOrderRequest cancelOrderRequest = new CancelOrderRequest();
//        for (QueryOrdersResponse.Result o : ctyunResponse.getReturnObj().getResult()) {
//                cancelOrderRequest.setMasterOrderId(o.getMasterOrderId());
//                CtyunApiResponse response = client.getCtyunResponse(cancelOrderRequest);
//                System.out.println("取消" + "o.getMasterOrderId()" + " " + JSON.toJSONString(response));
//        }
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void PM() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        QueryPMRequest request = new QueryPMRequest();
        request.setOrderId("9d8e9a0518f044c7af0cba1a07935ee5");
        CtyunApiResponse response = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    @SneakyThrows
    public void PlaceRefundOrder() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        PlaceRefundOrderRequest request = new PlaceRefundOrderRequest();
        request.setType("2");
        request.setResourceIds(Arrays.asList("287dd27e53454cf0b873e407ad0e14be"));
        request.setAutoApproval("true");
        CtyunApiResponse response = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(response));
    }
}