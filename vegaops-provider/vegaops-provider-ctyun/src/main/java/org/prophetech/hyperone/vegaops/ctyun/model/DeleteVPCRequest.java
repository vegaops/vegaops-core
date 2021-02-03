package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteVPCRequest extends CtyunRequest<DeleteVPCResponse> {
    private String vpcId;
    private String regionId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/deleteVPC";
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
    public Class<DeleteVPCResponse> getResponseClass() {
        return DeleteVPCResponse.class;
    }
}
