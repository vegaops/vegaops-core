package org.prophetech.hyperone.vegaops.aws.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateInstanceParam {
    private String name;
    private String associatePublicIpAddress;
    private Integer cpuNum;
    private Integer threadsPerCore;
    private String imageId;
    private String flavorId;
    private String kernelId;
    private String keypairName;
    private Integer maxCount;
    private Integer minCount;
    private String zoneId;
    private String privateIpAddress;
    private String ramDiskId;
    private String securityGroups;
    private String subnetId;


}
