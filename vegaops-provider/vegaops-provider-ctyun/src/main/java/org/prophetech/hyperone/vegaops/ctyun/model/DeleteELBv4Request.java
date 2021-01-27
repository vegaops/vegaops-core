package org.prophetech.hyperone.vegaops.ctyun.model;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.annotation.IgnoreParam;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunApiResponse;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;

@Setter
@Getter
public class DeleteELBv4Request extends CtyunRequest<CtyunApiResponse> {
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
        return "/apiproxy/v4/deleteELB";
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
    public Class<CtyunApiResponse> getResponseClass() {
        return CtyunApiResponse.class;
    }
}
