package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;

@Setter
@Getter
public class QueryVMsByOrderIdResponse extends CtyunResponse {
    private String id;
    private String resVmId;
    private String vmName;
    private String vmStatus;
    private String osStyle;
    private String regionId;
    private String accountId;
    private String userId;
    private String orderId;
    private String vlanId;
    private String createDate;
    private String dueDate;
    private String status;
    private String zoneId;
    private String zoneName;
    private String workOrderResourceId;
    private String cpuNum;
    private String memSize;
    private String isFreeze;
}
