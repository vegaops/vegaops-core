package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 绑定磁盘(按需)
 */
@Setter
@Getter
public class GetImagesRequest extends CtyunRequest<GetImagesResponse> {
    private String regionId;
    /**
     * 镜像类型，目前支持gold（公共镜像）或private（私有镜像）、shared（共享镜像）
     */
    private String imageType;
    /**
     * 取值为Windows，Ubuntu，RedHat，SUSE，CentOS，Other
     */
    private String platformType;
    /**
     * 32或64
     */
    private String osBit;
    /**
     * 固定值 true，不传默认查所有类型的镜像包括gpu镜像
     */
    private Boolean supportXen;
    /**
     * Xen虚拟化GPU类型，取值范围：M60_vGPU、M60_GPU
     */
    private String supportXenGpuType;
    /**
     * Kvm虚拟化GPU类型取值范围：M60、V100_vGPU、P100、P2V_V100
     */
    private String supportKvmGpuType;
    /**
     * 是否虚拟化，默认为true，表示云主机镜像，false表示物理机镜像。
     */
    private String isVirtual;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/order/getImages";
    }

    @Override
    public Method getMethod() {
        return Method.GET;
    }

    @Override
    public BodyType getBodyType() {
        return BodyType.ARRAY;
    }

    @Override
    public Class getResponseClass() {
        return GetImagesResponse.class;
    }
}
