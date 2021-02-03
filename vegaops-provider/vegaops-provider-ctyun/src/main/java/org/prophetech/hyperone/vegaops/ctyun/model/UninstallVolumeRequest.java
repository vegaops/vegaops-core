package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 解绑磁盘(按需)
 */
@Setter
@Getter
public class UninstallVolumeRequest extends CtyunRequest<GetJobIdResponse> {
    private String regionId;
    private String volumeId;
    /**
     * 专属云资源池ID，请根据查询projectId接口返回值进行传参，获取“id”参数
     */
    private String projectId;
    /**
     * 真实主机id
     */
    private String vmId;
    /**
     * 磁盘挂载点，如/dev/sda，/dev/sdb 挂载点a到z 但目前底层不支持/dev/sdy或/dev/sdz 挂载点
     */
    private String device;

    private String payType;

    @Override
    public String getUrl() {
        if ("PostPaid".equals(payType)) {
            return "/apiproxy/v3/ondemand/uninstallVolume";
        } else {
            return "/apiproxy/v3/uninstallVolume";
        }
    }

    @Override
    public Method getMethod() {
        return Method.POST;
    }

    @Override
    public BodyType getBodyType() {
        return BodyType.OBJECT;
    }

    @Override
    public Class getResponseClass() {
        return GetJobIdResponse.class;
    }
}
