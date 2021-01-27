package org.prophetech.hyperone.vegaops.aws.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEipParam {
    private String domainType;
    private String name;
    private String publicIpv4Pool;
    private String networkBorderGroup;
}
