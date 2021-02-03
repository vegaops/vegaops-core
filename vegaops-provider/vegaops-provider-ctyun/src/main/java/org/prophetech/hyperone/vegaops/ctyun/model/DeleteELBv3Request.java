package org.prophetech.hyperone.vegaops.ctyun.model;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.annotation.IgnoreParam;

@Setter
@Getter
public class DeleteELBv3Request extends CtyunRequest<GetJobIdResponse> {
    /**
     * 资源池ID，请根据查询资源池列表接口返回值进行传参，获取“regionId”参数
     */
    private String deleteEBLInfo;

    @IgnoreParam
    private String regionId;

    @IgnoreParam
    private String elbId;

    @Override
    public void init() {
        deleteEBLInfo = JSON.toJSONString(getIgnoreParamMap());
    }

    @Override
    public String getUrl() {
        return "/apiproxy/v3/deleteELB";
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
    public Class<GetJobIdResponse> getResponseClass() {
        return GetJobIdResponse.class;
    }
}
