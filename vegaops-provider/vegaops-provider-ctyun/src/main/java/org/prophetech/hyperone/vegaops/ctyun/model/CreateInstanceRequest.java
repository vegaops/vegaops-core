package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateInstanceRequest {
    private String id;
    private String status;
    private String createDate;
    private String expireTime;
    private Integer statusCode;
    private String payType;
    private String regionId;
    private String privateIp;

    // 后付费
    private String availability_zone;
    private String name;
    private String imageRef;
    private String osType;
    private String volumetype;
    private Integer volumeSize;
    private String dataVolumetype;
    private Integer dataVolumeSize;
    private String flavorRef;
    private String vpcid;
    private String securityGroupId;
    private String subnet_id;
    private String ip_address;
    private String publicipId;
    private String iptype = "5_telcom";
    private Integer bandwidthSize;
    private String sharetype;
    private String adminPass;
    private Integer count = 1;

    // 预付费
    private String instanceCnt;
    private String cycleCnt;
    private String cycleType = "3";
    private String instanceServiceTag;
    private String volumeServiceTag;
    private String memSize;
    private String cpuNum;
    private String flavorType;
    private String support_auto_recovery;
    private Integer customerInfoType;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private String customerAccountId;
    private String customerCrmBizId;
    private String keyPairId;

}
