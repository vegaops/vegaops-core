package org.prophetech.hyperone.vegaops.tencent.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RenewInstanceParam {

    private String instanceId;
    private String renewFlag;
    private String regionId;
    private Long period;
    private Boolean renewPortableDataDisk;
}
