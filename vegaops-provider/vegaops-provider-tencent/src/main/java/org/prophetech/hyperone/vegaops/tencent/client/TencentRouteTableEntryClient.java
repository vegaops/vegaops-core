package org.prophetech.hyperone.vegaops.tencent.client;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vpc.v20170312.VpcClient;
import com.tencentcloudapi.vpc.v20170312.models.DescribeRouteTablesRequest;
import com.tencentcloudapi.vpc.v20170312.models.DescribeRouteTablesResponse;
import com.tencentcloudapi.vpc.v20170312.models.Route;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Slf4j(topic = "vegaops")
public class TencentRouteTableEntryClient {

    private String key;
    private String secret;

    public List<Route> queryRouteTbleEntry(String regionId, String routeTableId) {
        try {

            Credential cred = new Credential(key, secret);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, regionId, clientProfile);

            DescribeRouteTablesRequest req = new DescribeRouteTablesRequest();

            req.setRouteTableIds(new String[]{routeTableId});

            DescribeRouteTablesResponse resp = client.DescribeRouteTables(req);
            if (resp.getRouteTableSet().length != 0 && resp.getRouteTableSet()[0].getRouteSet().length != 0) {
                return Arrays.asList(resp.getRouteTableSet()[0].getRouteSet());
            }
        } catch (TencentCloudSDKException e) {
            log.error("腾讯云接口请求失败 " + e);
            throw new RuntimeException("查询腾讯云路由表策略失败");
        }
        return new ArrayList<>();
    }

}
