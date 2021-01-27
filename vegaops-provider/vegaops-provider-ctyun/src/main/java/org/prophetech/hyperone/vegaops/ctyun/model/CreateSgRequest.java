package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateSgRequest {

    private String vpcId;
    private String regionId;
    private String name;

}
