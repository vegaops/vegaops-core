package org.prophetech.hyperone.vegaops.tencent.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateVolumeParam {

    private String regionId;
    private Long period;
    private String clientToken;
    private String diskChargeType;
    private Long diskCount;
    private String diskName;
    private Long diskSize;
    private String encrypt;
    private Boolean shareable;
    //private String tags;
    private String zone;
    private String snapshotId;
    private String diskType;
}
