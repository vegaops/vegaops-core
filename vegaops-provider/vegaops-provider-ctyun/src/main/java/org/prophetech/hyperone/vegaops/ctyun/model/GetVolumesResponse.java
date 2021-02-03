package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GetVolumesResponse extends CtyunResponse {
    /**
     * 磁盘信息列表
     */
    private List<volume> volumes;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class volume{
        /**
         * 真实磁盘id
         */
        private String id;
        /**
         * 按需磁盘状态
         */
        private String status;
        /**
         * 磁盘名称
         */
        private String name;
        /**
         * 磁盘创建时间
         */
        private String created_at;
        /**
         * 磁盘修改时间
         */
        private String updated_at;
        /**
         * 是否为共享云硬盘 默认为false
         */
        private String multiattach;
        /**
         * 磁盘大小
         */
        private String size;
        /**
         * 磁盘元数据
         */
        private String metadata;

        private String volume_type;
        /**
         * 用户id
         */
        private String user_id;
        /**
         * 是否为共享盘
         */
        private String shareable;

        private String encrypted;

        private String bootable;
        /**
         * 资源池可用区id
         */
        private String availability_zone;
        /**
         * 挂载信息对应的id
         */
        private String attachment_id;
        /**
         * 挂载时间
         */
        private String attached_at;
        /**
         * 磁盘挂载对应的主机id
         */
        private String server_id;
        /**
         * 磁盘挂载点
         */
        private String device;
        /**
         * 是否冻结
         */
        private String isFreeze;

        private String type;

        private List<Attachment> attachments;

        private String masterOrderId;
        private String workOrderResourceId;
        private String expireTime;
    }

    @Getter
    @Setter
    public static class Attachment{
        private String volume_id;
        private String attachment_id;
        private String attached_at;
        private String server_id;
        private String device;
        private String id;
    }
}
