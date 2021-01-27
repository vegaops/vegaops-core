package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;

@Setter
@Getter
public class QueryDataDiskDetailResponse extends CtyunResponse {
    /**
     * uuid
     */
    private String id;
    /**
     * 真实的磁盘id
     */
    private String resEbsId;
    /**
     * 磁盘大小
     */
    private Integer size;
    /**
     * 磁盘名称
     */
    private String name;
    /**
     * 资源池id
     */
    private String regionId;
    /**
     * 账户id
     */
    private String accountId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 磁盘对应的主机id
     */
    private String hostId;
    /**
     * 磁盘对应的虚拟资源状态
     */
    private Integer status;
    /**
     * 磁盘类型
     */
    private String type;
    /**
     * 磁盘状态
     */
    private Integer volumeStatus;
    /**
     * 创建时间
     */
    private Integer createDate;
    /**
     * 到期时间
     */
    private Integer dueDate;
    /**
     * 资源池可用区id
     */
    private String zoneId;
    /**
     * 资源池名称
     */
    private String zoneName;
    /**
     * 是否是系统盘 1 是系统盘 0数据盘
     */
    private Integer isSysVolume;
    /**
     * 是否是成套 1成套订购 0非成套订购
     */
    private Integer isPackaged;
    /**
     * 磁盘对应的虚拟资源id
     */
    private String workOrderResourceId;
}
