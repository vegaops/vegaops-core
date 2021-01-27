package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;
import org.prophetech.hyperone.vegaops.ctyun.model.QueryVMDetailResponse;

@Setter
@Getter
public class QueryVMDetailRequest extends CtyunRequest<QueryVMDetailResponse> {
    /**
     * 主机id
     */
    private String vmId;
    /**
     * 资源池id 请根据查询资源池接口返回值进行传参，获取“regionId”参数
     */
    private String regionId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/ondemand/queryVMDetail";
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
    public Class<QueryVMDetailResponse> getResponseClass() {
        return QueryVMDetailResponse.class;
    }
}
