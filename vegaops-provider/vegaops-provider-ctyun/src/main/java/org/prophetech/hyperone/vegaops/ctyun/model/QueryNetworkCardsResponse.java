package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;

import java.util.List;

@Setter
@Getter
public class QueryNetworkCardsResponse extends CtyunResponse {
    /**
     * 云服务器网卡信息列表
     */
    private List<InterfaceAttachments> interfaceAttachments;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class InterfaceAttachments {
        /**
         * 网卡私网IP信息列表
         */
        private List<FixedIps> fixed_ips;
        /**
         * 网卡端口ID
         */
        private String port_id;
        /**
         * 网卡端口所属网络ID
         */
        private String net_id;
        /**
         * 网卡端口状态
         */
        private String port_state;
        /**
         * mac地址
         */
        private String mac_addr;

        private String ip_addresses;
        /**
         * mac地址
         */
        private String subnet_ids;

        private String vmId;


    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class FixedIps {
        /**
         * 网卡私网IP信息
         */
        private String ip_address;
        /**
         * 网卡私网IP对应子网信息
         */
        private String subnet_id;

    }

}
