package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;

@Setter
@Getter
public class DeleteNetworkCardRequest extends CtyunRequest<CtyunResponse> {
    /**
     *主机id
     */
    private String vmId;
   /**
    *网卡ID 请通过根据主机ID查询网卡列表接口去获取port_id字段
    */
    private String networkCardId;
    /**
     *资源池ID
     */
    private String regionId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/deleteNetworkCard";
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
    public Class<CtyunResponse> getResponseClass() {
        return CtyunResponse.class;
    }
}
