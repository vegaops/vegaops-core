package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.BodyType;
import org.prophetech.hyperone.vegaops.ctyun.model.CreateKeyPairResponse;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunRequest;
import org.prophetech.hyperone.vegaops.ctyun.model.Method;

@Setter
@Getter
public class CreateKeyPairRequest extends CtyunRequest<CreateKeyPairResponse> {
    private String regionId;
    /**
     * 密钥名称。
     */
    private String name;
    /**
     * 导入的公钥信息。导入公钥最大长度为1024字节。注：长度超过1024字节会导致云服务器注入该密钥失败。
     */
    private String publicKey;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/createSSH";
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
        return CreateKeyPairResponse.class;
    }
}
