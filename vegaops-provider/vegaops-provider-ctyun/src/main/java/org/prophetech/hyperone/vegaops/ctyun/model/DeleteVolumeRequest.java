package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.GetJobIdResponse;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;

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
