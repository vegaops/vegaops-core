package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateSgResponse {

    /**
     * uuid
     */
    private String id;
    /**
     * VPC名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;


    private String vpcId;

}
