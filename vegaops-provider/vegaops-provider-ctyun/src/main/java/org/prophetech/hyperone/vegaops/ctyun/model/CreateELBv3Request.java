package org.prophetech.hyperone.vegaops.ctyun.model;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.annotation.IgnoreParam;

/**
 * !!! 天翼云经典型负载均衡已不支持购买 !!!
 */
@Setter
@Getter
public class CreateELBv3Request extends CtyunRequest<GetJobIdResponse> {
    /**
     * 资源池ID，请根据查询资源池列表接口返回值进行传参，获取“regionId”参数
     */
    private String createELBInfo;

    @IgnoreParam
    private String regionId;

    @IgnoreParam
    private String zoneId;
    @IgnoreParam
    private String name;
    @IgnoreParam
    private String description;
    @IgnoreParam
    private String vpcId;
    @IgnoreParam
    private String bandwidth;
    @IgnoreParam
    private String type;
    @IgnoreParam
    private String adminStateUp;
    @IgnoreParam
    private String vipSubnetId;

    @Override
    public void init() {
        createELBInfo = JSON.toJSONString(getIgnoreParamMap());
    }

    @Override
    public String getUrl() {
        return "/apiproxy/v3/createELB";
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
    public Class<GetJobIdResponse> getResponseClass() {
        return GetJobIdResponse.class;
    }
}
