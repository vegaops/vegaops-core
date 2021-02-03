package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public  class CtyunApiObjectResponse<T extends CtyunResponse> extends CtyunApiResponse {
    private T returnObj;
}
