package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetFlavorsResponse extends CtyunResponse {
    /**
     * 规格id
     */
    private String id;
    /**
     * CPU
     */
    private String cpuNum;
    /**
     * 内存
     */
    private String memSize;
    /**
     * 规格状态normal（正常使用）、abandon（下线）、sellout（售罄）、obt（公测）、promotion（推荐=normal），没有状态值则为正常的
     */
    private String status;
    /**
     * 可用区 normal（正常使用）、abandon（下线）、sellout（售罄）、obt（公测）、promotion（推荐=normal）
     */
    private String az;

    private String flavorType;

    private String flavorName;

    private String gpuNum;
}
