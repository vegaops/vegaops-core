package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GetELBsv4Response extends CtyunResponse {
    /**
     * 负载均衡器信息列表
     */
    private List<CreateELBv4Response.Loadbalancer> loadbalancers;

}
