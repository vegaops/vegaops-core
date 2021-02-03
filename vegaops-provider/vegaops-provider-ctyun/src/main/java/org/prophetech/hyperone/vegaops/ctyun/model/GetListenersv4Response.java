package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GetListenersv4Response extends CtyunResponse {
    /**
     * 负载均衡器信息列表
     */
    private List<Listener> listeners;
    private List<ListenersLinks> listeners_links;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Listener{
        private String id;
        private String tenant_id;
        private String name;
        private String protocol;
        private String protocol_port;
        private String loadbalancers;
        private String connection_limit;
        private String admin_state_up;
        private String http2_enable;
        private String default_pool_id;
        private String default_tls_container_ref;
        private String client_ca_tls_container_ref;
        private String sni_container_refs;
        private String tags;
        private String created_at;
        private String updated_at;
        private String listeners_links;
        private String insert_headers;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ListenersLinks{
        private String href;
        private String rel;
    }
}
