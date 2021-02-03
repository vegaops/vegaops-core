package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryMasterOrderResponse extends CtyunResponse {
    private String masterOrderId;
    private String masterOrderNo;
    private String masterOrderOrigId;
    private String masterOrderType;
    private String accountId;
    private String userId;
    private String accountType;
    private String totalPrice;
    private String payType;
    private String createDate;
    private String updateDate;
    private String status;
    private String isVirtualOrder;
    private String isTrialOrder;
    private String finalPrice;
    private String isAgencyOrder;
    private String chargingStatus;
    private String isOnDemand;
    private String orderChannel;
    private String buildingChannel;
    private String packageType;
    private String trail;
    private String virtualOrder;
    private String hasContract;
    private String abandonStatus;
    private String paymentSite;
    private String paymentChannel;
    private String paymentLimitation;
    private String payablePrice;
    private String payable;
    private String cancelable;
    private String canApplyTrial;
    private String canForceToComplete;
}
