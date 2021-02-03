package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetImagesResponse extends CtyunResponse {
    /**
     * 镜像id
     */
    private String id;
    /**
     * 操作系统类型
     */
    private String osType;
    /**
     * 镜像平台分类
     */
    private String platform;
    /**
     * 镜像名称
     */
    private String name;
    /**
     * 操作系统位数
     */
    private Integer osBit;
    /**
     * true虚拟机 false不是虚拟机
     */
    private String virtual;
    /**
     * 镜像运行最小内存，单位为MB。
     */
    private String minRam;
    /**
     * 镜像最小系统盘大小，单位为GB
     */
    private String minDisk;
    /**
     * 镜像类型 gold 公有 Private 私有
     */
    private String imageType;
}
