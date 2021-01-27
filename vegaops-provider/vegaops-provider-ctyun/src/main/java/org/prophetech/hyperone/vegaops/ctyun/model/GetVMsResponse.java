package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;

import java.util.List;

@Setter
@Getter
public class GetVMsResponse extends CtyunResponse {
    /**
     * 磁盘信息列表
     */
    private List<Instance> servers;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Instance {
        /**
         * 主机id
         */
        private String id;
        /**
         * 按需云主机状态
         */
        private String status;
        /**
         * 主机名称
         */
        private String name;
        /**
         * 操作用户的租户id
         */
        private String tenant_id;
        /**
         * 磁盘修改时间
         */
        private String metadata;
        /**
         * 云服务器元数据
         */
        private String multiattach;
        /**
         * 镜像
         */
        private String image;
        /**
         * 主机规格
         */
        private String flavor;
        /**
         * 云服务器对应的vpc信息
         */
        private String addresses;
        /**
         * 分配IP地址方式（fixed 表示私网ip）
         */
        private String privateIp;
        /**
         * 私网IP地址
         */
        //private String addr;
        /**
         * IP地址类型，值为4或6。 4：IP地址类型是IPv4 6：IP地址类型是IPv6
         */
        //private String version;
        /**
         * MAC地址
         */
        //private String OS-EXT-IPS-MAC:mac_addr;
        /**
         * 用户id
         */
        private String user_id;
        /**
         * 创建时间
         */
        private String created;
        /**
         * 主机对应的订单id （只有包周期的主机才会返回该值）
         */
        private String masterOrderId;
        /**
         * 主机对应的虚拟资源id（只有包周期的主机才会返回该值）
         */
        private String workOrderResourceId;
        /**
         * 主机到期时间（只有包周期的主机才会返回该值）
         */
        private String expireTime;
        /**
         * 是否冻结 0未冻结 1已冻结 （只有包周期的主机才会返回该值）
         */
        private String isFreeze;
        /**
         * 安全组信息 tenantId+安全组name可确定唯一
         */
        private String security_groups;

        private String hostId;

        private String availability_zone;

        private String volumes_attached;

        private String vpcId;

        private String floatingIp;

        private String payType;
    }
}
