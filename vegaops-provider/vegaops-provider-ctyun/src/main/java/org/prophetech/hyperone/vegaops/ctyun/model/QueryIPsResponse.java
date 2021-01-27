package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;

import java.util.List;

@Setter
@Getter
public class QueryIPsResponse extends CtyunResponse {
    /**
     * VPCId
     */
    private List<PublicIp> publicips;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PublicIp{
        private String ip_version;
        private String bandwidth_share_type;
        private String type;
        private String private_ip_address;
        private String enterprise_project_id;
        private String status;
        private String public_ip_address;
        private String id;
        private String tenant_id;
        private Profile profile;
        private String bandwidth_name;
        private String bandwidth_id;
        private String port_id;
        private String bandwidth_size;
        private String create_time;
        private String masterOrderId;
        private String workOrderResourceId;
        private String expireTime;
        private String isFreeze;
        private String name;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Profile{
        private String order_id;
        private String region_id;
        private String user_id;
        private String product_id;
    }
}
