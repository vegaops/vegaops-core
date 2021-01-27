package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;

import java.util.List;

@Setter
@Getter
public class CreateELBv4Response extends CtyunResponse {

    private Loadbalancer loadbalancer;

    @Getter
    @Setter
    public static class Loadbalancer{
        /**
         * 负载均衡器id
         */
        private String id;
        /**
         * vip端口id
         */
        private String vip_port_id;
        /**
         * 负载均衡器管理状态，取值范围：0、1，0为关闭，1为开启。
         */
        private String admin_state_up;
        /**
         * 更新时间
         */
        private String updated_at;
        /**
         * 供应者。使用说明：只支持vlb。
         */
        private String provider = "vlb";
        /**
         * 创建时间
         */
        private String created_at;
        /**
         * 分配vip的子网id。使用说明：仅支持内网类型。
         */
        private String vip_subnet_id;
        /**
         * operating状态，可以为ONLINE、OFFLINE、DEGRADED、DISABLED或NO_MONITOR。
         */
        private String operating_status;
        /**
         * 负载均衡器名称。
         */
        private String name;
        /**
         * 负载均衡的标签
         */
        private String tags;
        /**
         * 租户id
         */
        private String tenant_id;
        /**
         * provisioning状态，可以为ACTIVE、PENDING_CREATE 或者ERROR。
         */
        private String provisioning_status;
        /**
         * VIP的ip 地址。
         */
        private String vip_address;
        /**
         * 描述信息
         */
        private String description;
        /**
         * 关联的listener列表。
         */
        private List<String> listeners;

        private String project_id;
        private List<String> pools;
    }

}
