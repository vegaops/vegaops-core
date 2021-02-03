package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GetVMOrdersResponse extends CtyunResponse {
    /**
     * 磁盘信息列表
     */
    private List<Instance> result;

    private Integer pageNo;
    private Integer pageSize;
    private Integer pageCount;
    private Integer rowCount;
    private Integer start;
    private Integer totalCount;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Instance {
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
        private String status;
        private String zoneId;
        private String zoneName;
        private String workOrderResourceId;
        private String cpuNum;
        private String memSize;
        private String isFreeze;
    }
}
