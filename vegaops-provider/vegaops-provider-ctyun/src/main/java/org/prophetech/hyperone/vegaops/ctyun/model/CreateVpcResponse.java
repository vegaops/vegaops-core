package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateVpcResponse extends CtyunResponse {
    /**
     * uuid
     */
    private String id;
    /**
     * VPC名称
     */
    private String name;
    /**
     * 子网网段
     */
    private String cidr;
    /**
     * vpc状态
     */
    private String status;
}
