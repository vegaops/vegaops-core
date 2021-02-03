package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetVolumesRequest extends CtyunRequest<GetVolumesResponse> {
    private String regionId;
    /**
     * 专属云资源池ID，请根据查询projectId接口返回值进行传参，获取“id”参数
     */
    private String projectId;
    /**
     * 磁盘id
     */
    private String volumeId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/ondemand/queryVolumes";
    }

    @Override
    public Method getMethod() {
        return Method.GET;
    }

    @Override
    public BodyType getBodyType() {
        return BodyType.OBJECT;
    }

    @Override
    public Class getResponseClass() {
        return GetVolumesResponse.class;
    }
}
