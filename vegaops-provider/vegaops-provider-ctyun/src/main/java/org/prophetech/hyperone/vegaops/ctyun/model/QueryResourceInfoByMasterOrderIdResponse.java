package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;

@Setter
@Getter
public class QueryResourceInfoByMasterOrderIdResponse extends CtyunResponse {
    private String orderItemId;
    private String instanceId;
    private String accountId;
    private String userId;
    private String innerOrderId;
    private String innerOrderItemId;
    private String productId;
    private String masterOrderId;
    private String orderId;
    private String masterResourceId;
    private String resourceId;
    private String serviceTag;
    private String resourceType;
    private String resourceInfo;
    private String startDate;
    private String expireDate;
    private String createDate;
    private String updateDate;
    private String status;
    private String workOrderId;
    private String workOrderItemId;
    private String salesEntryId;
    private String orderStatus;
    private String toOndemand;
    private String itemValue;
    private String chargingStatus;
    private String chargingDate;
    private String resourceConfig;
    private String autoToOnDemand;
    private String buildingChannel;
    private String isPlatformSpecific;
    private String billingOwner;
    private String isPackage;
    private String canRelease;
    private String isChargeOff;
    private String isPublicTest;
    private String master;
    private String resourceConfigMap;
}
