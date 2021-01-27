package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;

import java.util.List;

@Setter
@Getter
public class QueryOrdersResponse extends CtyunResponse {

    private String pageNo;
    private String pageSize;
    private String pageCount;
    private String rowCount;
    private String start;
    private List<Result> result;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Result{
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
}
