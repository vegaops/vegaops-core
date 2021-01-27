package org.prophetech.hyperone.vegaops.alicloud.client;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vpc.model.v20160428.DescribeVirtualBorderRoutersRequest;
import com.aliyuncs.vpc.model.v20160428.DescribeVirtualBorderRoutersResponse;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.prophetech.hyperone.vegaops.alicloud.model.DescribeVbrRequest;

import java.util.ArrayList;
import java.util.List;


@Slf4j(topic = "vegaops")
public class AliyunVbrClient {

    public DescribeVirtualBorderRoutersResponse.VirtualBorderRouterType getVbr(DescribeVbrRequest describeVbrRequest) {
        DefaultProfile profile = DefaultProfile.getProfile(describeVbrRequest.getRegionId(), describeVbrRequest.getAccessKey(), describeVbrRequest.getSecret());
        IAcsClient clients = new DefaultAcsClient(profile);
        DescribeVirtualBorderRoutersResponse.VirtualBorderRouterType vbrResponse = new DescribeVirtualBorderRoutersResponse.VirtualBorderRouterType();
        DescribeVirtualBorderRoutersRequest request = new DescribeVirtualBorderRoutersRequest();

        List<DescribeVirtualBorderRoutersRequest.Filter> filterList = new ArrayList<DescribeVirtualBorderRoutersRequest.Filter>();

        DescribeVirtualBorderRoutersRequest.Filter filter1 = new DescribeVirtualBorderRoutersRequest.Filter();
        filter1.setKey("VbrId");

        List<String> valueList1 = new ArrayList<String>();
        valueList1.add(describeVbrRequest.getVbrId());
        filter1.setValues(valueList1);
        filterList.add(filter1);
        request.setFilters(filterList);

        try {
            DescribeVirtualBorderRoutersResponse response = clients.getAcsResponse(request);
            if(!CollectionUtils.isEmpty(response.getVirtualBorderRouterSet())){
                return response.getVirtualBorderRouterSet().get(0);
            }
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
        return vbrResponse;
    }
}
