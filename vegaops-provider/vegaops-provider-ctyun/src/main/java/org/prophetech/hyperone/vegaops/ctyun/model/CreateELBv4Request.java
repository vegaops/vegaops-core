package org.prophetech.hyperone.vegaops.ctyun.model;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.annotation.IgnoreParam;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class CreateELBv4Request extends CtyunRequest<CreateELBv4Response> {
    /**
     * 资源池ID，请根据查询资源池列表接口返回值进行传参，获取“regionId”参数
     */
    private String createELBInfo;

    @IgnoreParam(group = "createELBInfo")
    private String regionId;
//    @IgnoreParam
//    private String jsonstr;

//    /**
//     * 负载均衡信息列表
//     */
//    @IgnoreParam(group = "jsonstr")
//    private Map loadbalancer;
    /**
     * 名称，取值范围：1-64，中英文、数字、下划线、中划线。
     */
    @IgnoreParam(group = "loadbalancer")
    private String name;
    /**
     * 描述
     */
    @IgnoreParam(group = "loadbalancer")
    private String description;
    /**
     * 负载均衡所在子网id 请根据查询子网列表接口返回值进行传参，获取“neutronSubnetId”参数
     */
    @IgnoreParam(group = "loadbalancer")
    private String vip_subnet_id;
    /**
     * 负载均衡的生产者，取值范围：vlb
     */
    @IgnoreParam(group = "loadbalancer")
    private String provider = "vlb";
    /**
     * 负载均衡器的内网IP。 该IP必须为vip_subnet_id字段指定的 子网网段中的IP。 若不指定（不传该字段），则自动从vip_subnet_id字段指定的子网网段中生成一个IP地址。
     */
    @IgnoreParam(group = "loadbalancer")
    private String vip_address;
    /**
     * 负载均衡器的管理状态。 该字段为预留字段，暂未启用。默认为true。
     */
    @IgnoreParam(group = "loadbalancer")
    private String admin_state_up = "true";


    @Override
    public void init() {
        Map loadbalancer = getIgnoreParamMap("loadbalancer");
        Map jsonstrMap = new HashMap();
        jsonstrMap.put("loadbalancer", loadbalancer);
        Map createELBInfoMap = new HashMap();
        createELBInfoMap.put("regionId", regionId);
        createELBInfoMap.put("jsonstr", jsonstrMap);
        createELBInfo = JSON.toJSONString(createELBInfoMap);
    }

    @Override
    public String getUrl() {
        return "/apiproxy/v4/elb/createELB";
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
        return CreateELBv4Response.class;
    }
}
