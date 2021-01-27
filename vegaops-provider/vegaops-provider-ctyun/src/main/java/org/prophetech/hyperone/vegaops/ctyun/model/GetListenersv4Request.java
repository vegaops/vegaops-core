package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.GetListenersv4Response;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;

@Setter
@Getter
public class GetListenersv4Request extends CtyunRequest<GetListenersv4Response> {
    /**
     * 资源池ID，请根据查询资源池列表接口返回值进行传参，获取“regionId”参数
     */
    private String regionId;
    private String loadBalancerId;
    private String projectId;

    @Override
    public String getUrl() {
        return "/apiproxy/v4/queryListeners";
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
    public Class<GetListenersv4Response> getResponseClass() {
        return GetListenersv4Response.class;
    }
}
