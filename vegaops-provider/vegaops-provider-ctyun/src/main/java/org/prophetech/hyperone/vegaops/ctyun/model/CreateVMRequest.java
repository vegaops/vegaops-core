package org.prophetech.hyperone.vegaops.ctyun.model;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.annotation.IgnoreParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Setter
@Getter
public class CreateVMRequest extends CtyunRequest<GetJobIdResponse> {
    /**
     * 创建按需云主机信息
     */
    private String createVMInfo;
    /**
     * 可用区
     */
    @IgnoreParam(group = "server")
    private String availability_zone;
    /**
     * （只能由中文字符、英文字母、数字、下划线、中划线组成，且长度小于等于64个字符）
     */
    @IgnoreParam(group = "server")
    private String name;
    /**
     * 镜像请根据查询镜像列表接口返回值进行传参，获取“id”参数
     */
    @IgnoreParam(group = "server")
    private String imageRef;

    @IgnoreParam(group = "server")
    private String osType;
    /**
     * 系统盘相关配置
     */
    private Volume root_volume;
    /**
     * 数据盘相关配置
     */
    private List<Volume> data_volumes;
    /**
     * 主机规格ID，查询规格时返回的ID
     */
    @IgnoreParam(group = "server")
    private String flavorRef;
    /**
     * VPCID， 请根据查询VPC列表接口返回值进行传参，获取“resVpcId”参数 。注：（如果没有vpc需要先去创建vpc创建vpc ）
     */
    @IgnoreParam(group = "server")
    private String vpcid;
    /**
     * 安全组信息， 不传则默认主机没有设置任何访问规则，注：（如果没有安全组需要先去创建安全组创建安全组 ）
     */
    private List<SecurityGroup> security_groups;
    /**
     * 子网信息，注：（如果没有子网需要先去创建子网创建子网 ）
     */
    private List<Nic> nics;
    /**
     * 私网ip地址 如果不传表示由后台自动分配私网ip地址，传则以所传ip地址作为该主机的私网ip 。注：同一vpc 下私网ip不能重复，所传私网ip地址网段必须是该vpc网段范围内
     */
    @IgnoreParam(group = "server")
    private String ip_address;
    /**
     * 弹性ip信息。弹性IP有三种配置方式。不使用（无该字段）自动分配使用已有（查询公网ip返回的ID）iptype：类型枚举值，5_telcom。sharetype：共享类型枚举：PER。
     */
    private PublicIp publicip;
    /**
     * （主机管理员账户初始登录密码）----Linux管理员账户为root，Windows管理员账户为Administrator。密码复杂度要求：长度为8-26位。密码至少必须包含大写字母、小写字母、数字和特殊字符中的三种。密码不能包含用户名或用户名的逆序。Windows系统密码不能包含用户名或用户名的逆序，不能包含用户名中超过两个连续字符的部分。
     */
    @IgnoreParam(group = "server")
    private String adminPass;

    @IgnoreParam(group = "server")
    private String key_name;
    /**
     * 创建主机数量 ，默认值为1
     */
    @IgnoreParam(group = "server")
    private Integer count;
//    /**
//     * 创建主机附加信息
//     */
//    private Extendparam extendparam;
    /**
     * 资源池id 请根据查询资源池接口返回值进行传参，获取“regionId”参数
     */
    private String regionID;

    @Override
    public void init() {
        TreeMap serverTreeMap = getIgnoreParamMap("server");
        Map server = new HashMap();
        server.putAll(serverTreeMap);
        server.put("data_volumes", data_volumes);
        server.put("nics", nics);
        server.put("publicip", publicip);
        server.put("root_volume", root_volume);
        server.put("security_groups", security_groups);
        Map extendparam = new HashMap();
        extendparam.put("regionID", regionID);
        server.put("extendparam", extendparam);
        Map serverMap = new HashMap();
        serverMap.put("server", server);
        createVMInfo = JSON.toJSONString(serverMap);
    }

    @Override
    public String getUrl() {
        return "/apiproxy/v3/ondemand/createVM";
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

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Volume {
        private String volumetype;
        private Integer size;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SecurityGroup {
        private String id;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Nic {
        private String subnet_id;
//        private String ip_address;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PublicIp {
        /**
         * 已经存在的公网ip的ID。id字段和 eip字段二者取其一 （1、“使用已有”则id字段填已经存在的公网ip的ID，此时eip字段也不需要传值了。 2、“自动分配”id字段传“”（空字符串），eip字段的值都需要传。3、“不使用”则整个publicIp的json串都不传 ）
         */
        private String id;

        private Eip eip;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Eip {
        /**
         * 5_telcom是固定值
         */
        private String iptype = "5_telcom";

        private BandWidth bandwidth;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class BandWidth {
        private Integer size;
        /**
         * 取值为PER，表示独享带宽;WHOLE:共享带宽
         */
        private String sharetype;
    }
}
