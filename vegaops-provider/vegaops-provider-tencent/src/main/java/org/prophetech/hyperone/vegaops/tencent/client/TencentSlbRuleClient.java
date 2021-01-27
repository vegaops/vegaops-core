package org.prophetech.hyperone.vegaops.tencent.client;

import com.tencentcloudapi.clb.v20180317.ClbClient;
import com.tencentcloudapi.clb.v20180317.models.DescribeListenersRequest;
import com.tencentcloudapi.clb.v20180317.models.DescribeListenersResponse;
import com.tencentcloudapi.clb.v20180317.models.Listener;
import com.tencentcloudapi.clb.v20180317.models.RuleOutput;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Slf4j(topic = "vegaops")
public class TencentSlbRuleClient {

    private String key;
    private String secret;

    public List<RuleOutput> querySlbRule(String regionId, String slbId) {
        try {
            List<RuleOutput> rules = new ArrayList<>();
            Credential cred = new Credential(key, secret);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("clb.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            ClbClient client = new ClbClient(cred, regionId, clientProfile);

            DescribeListenersRequest req = new DescribeListenersRequest();
            req.setLoadBalancerId(slbId);
            DescribeListenersResponse resp = client.DescribeListeners(req);
            if (resp.getListeners().length != 0){
                for (Listener listener : resp.getListeners()) {
                    rules.addAll(Arrays.asList(listener.getRules()));
                }
            }
            return rules;
        } catch (TencentCloudSDKException e) {
            log.error("腾讯云接口请求失败 " + e);
            throw new RuntimeException("查询腾讯云负载均衡规则失败");
        }
    }

}
