package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateDatadiskRequest {
    private String id;
    private Integer statusCode;
    private String payType;
    private String regionId;

    // 后付费
    private String zoneId;
    private String name;
    private String type;
    private Integer size;
    private String count;
    private String backupId;

    private String cycleCnt;
    private String cycleType = "3";
    private String serviceTag = "HWS";
    private Integer customerInfoType;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private String customerAccountId;
    private String customerCrmBizId;
}
