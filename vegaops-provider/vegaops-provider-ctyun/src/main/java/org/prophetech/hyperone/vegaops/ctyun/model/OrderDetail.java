package org.prophetech.hyperone.vegaops.ctyun.model;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CreateVMRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.CustomInfo;

import java.util.List;
import java.util.TreeMap;

@Getter
@Setter
public class OrderDetail {

    private List<Order> orders;

    private CustomInfo customInfo;

    public String toJson() {
        TreeMap map = new TreeMap();
        map.put("orders", orders);
        map.put("customInfo", customInfo);
        return JSON.toJSONString(map);
    }


    @Getter
    @Setter
    @NoArgsConstructor
    public static class Order {
        /**
         * 订购数量，取值范围：值需大于零且订购数量不能超过50台
         */
        private String instanceCnt;
        /**
         * 订购周期，取值范围：值需大于零，不超过384个月
         */
        private String cycleCnt;
        /**
         * 订购周期类型，取值范围：固定参数3，3表示按月订购，cycleCnt属性为1表示订购1个月
         */
        private String cycleType = "3";
        /**
         * 订单项信息
         */
        private List<Item> items;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Item {
        /**
         * 是否为主资源，取值范围：true或false，为true则为主资源，每个订单里每个成套资源只能有一个主资源
         */
        private String master;
        /**
         * 资源类型，取值范围：VMC（主机）、EBSC（磁盘）、NETWORKC（带宽）
         */
        private String resourceType;
        /**
         * 资源服务标签，3.0合营资源固定参数为HWS
         */
        private String serviceTag;

        private ItemConfig itemConfig;

        private Integer itemValue;

        /**
         * 是否系统盘，取值范围：true。注：若购买了主机则必须一起新购系统盘，且系统盘只能有一个，不传itemValue，系统盘默认大小为镜像最小系统盘大小（minDisk[通过查询镜像列表获取]）。传itemValue时，系统盘取值范围：镜像最小系统盘大小（minDisk）--1024GB。详情参照参数示例传
         */
        private Boolean isSystemVolume;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    private static class ItemConfig {
        private String availability_zone;
        private String regionId;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Vmc extends ItemConfig {
        private String memSize;
        private String cpuNum;
        private String flavorType;
        private String flavorRef;
        private String osType;
        private String name;
        private String imageRef;
        private String vpcid;
        private List<CreateVMRequest.SecurityGroup> security_groups;
        private List<CreateVMRequest.Nic> nics;
        private String adminPass;
        private String key_name;
        private String support_auto_recovery;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Ebsc extends ItemConfig {
        private String volumeType;
        private String volumeName;
        private Integer size;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Networkc extends ItemConfig {
        private String type;
        private String name;
        private String ipVersion;

    }
}
