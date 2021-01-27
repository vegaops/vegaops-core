package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;

import java.util.List;

@Setter
@Getter
public class QueryVMDetailResponse extends CtyunResponse {
    /**
     * 主机id
     */
    private String hostId;
    /**
     * 主机名称
     */
    private String name;
    /**
     * 私有ip对象列表
     */
    private List<Ip> privateIps;
    /**
     * 主机状态
     */
    private String status;
    /**
     * 公网对象列表
     */
    private List<Ip> publicIps;
    /**
     * 磁盘对象列表
     */
    private List<Volume> volumes;
    /**
     * 创建时间
     */
    private String created;
    /**
     * 对象
     */
    private FlavorObj flavorObj;

    @Setter
    @Getter
    public static class FlavorObj {
        /**
         * 规格
         */
        private String name;
        /**
         * cpu核数
         */
        private String cpuNum;
        /**
         * 内存大小
         */
        private String memSize;
        /**
         * 规格
         */
        private String id;
    }

    @Setter
    @Getter
    public static class Ip{
        /**
         * ip真实id
         */
        private String id;
        /**
         * ip地址
         */
        private String address;
        /**
         * 带宽大小
         */
        private String bandwidth;
    }

    @Setter
    @Getter
    public static class Volume{
        /**
         * 磁盘id
         */
        private String id;
        /**
         * 磁盘状态
         */
        private String status;
        /**
         * 磁盘类型
         */
        private String type;
        /**
         * 磁盘大小
         */
        private String size;
        /**
         * 磁盘名称
         */
        private String name;
        /**
         * 是否为系统盘 true：是；false：否
         */
        private Boolean bootable;
    }
}
