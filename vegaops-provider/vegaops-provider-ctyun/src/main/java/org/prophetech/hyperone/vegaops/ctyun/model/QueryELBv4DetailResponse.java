package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CreateELBv4Response;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;

import java.util.List;

@Setter
@Getter
public class QueryELBv4DetailResponse extends CtyunResponse {
    /**
     * 负载均衡器信息列表
     */
    private List<CreateELBv4Response> loadbalancers;

}
