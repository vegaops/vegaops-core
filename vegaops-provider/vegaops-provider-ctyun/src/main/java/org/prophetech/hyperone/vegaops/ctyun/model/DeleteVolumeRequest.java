package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 绑定磁盘(按需)
 */
@Setter
@Getter
public class DeleteVolumeRequest extends CtyunRequest<GetJobIdResponse> {
    private String regionId;
    private String volumeId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/ondemand/deleteVolume";
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
