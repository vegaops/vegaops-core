package org.prophetech.hyperone.vegaops.ctyun.model;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AddNetworkCardsRequest extends CtyunRequest<GetJobIdResponse> {
    /**
     * 资源池ID，请根据查询资源池列表接口返回值进行传参，获取“regionId”参数
     */
    private String regionId;
    /**
     * 真实主机id
     */
    private String vmId;
    /**
     * 网卡信息
     */
    private List<NetworkCardsInfo>  networkCards;
    private String  networkCardsInfo;

    @Override
    public void init(){
        networkCardsInfo = JSON.toJSONString(networkCards);
    }

    @Override
    public String getUrl() {
        return "/apiproxy/v3/addNetworkCards";
    }

    @Override
    public Method getMethod() {
        return Method.POST;
    }

    @Override
    public BodyType getBodyType() {
        return BodyType.OBJECT;
    }

    @Override
    public Class getResponseClass() {
        return GetJobIdResponse.class;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class NetworkCardsInfo{
        private String subnetId;
        private List<SecurityGroups> securityGroups;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SecurityGroups{
        private String id;
    }
}
