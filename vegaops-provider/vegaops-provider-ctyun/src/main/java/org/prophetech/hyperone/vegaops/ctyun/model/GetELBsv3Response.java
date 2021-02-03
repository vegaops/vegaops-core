package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GetELBsv3Response extends CtyunResponse {
    /**
     * 负载均衡器的个数
     */
    private String instance_num;
    /**
     * 负载均衡器信息列表
     */
    private List<loadbalancer> loadbalancers;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class loadbalancer extends CtyunResponse {
        /**
         * ELB对外服务IP
         */
        private String vip_address;
        /**
         * 创建时间
         */
        private String create_time;
        /**
         * 更新时间
         */
        private String update_time;
        /**
         * 负载均衡器的id
         */
        private String id;
        /**
         * 负载均衡器状态
         */
        private String status;
        /**
         * 带宽大小
         */
        private String bandwidth;
        /**
         * vpcid
         */
        private String vpc_id;
        /**
         * 管理状态，0为关闭，1为开启
         */
        private String admin_state_up;
        /**
         * 要加入的私网ID，type为Internal时该字段有效。
         */
        private String vip_subnet_id;
        /**
         * 负载均衡器类型：Internal, External。
         */
        private String type;
        /**
         * 负载均衡器名称
         */
        private String name;
        /**
         * 描述
         */
        private String description;
    }
}
