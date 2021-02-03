package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public  class CtyunApiStringResponse<T extends CtyunResponse> extends CtyunApiResponse {
    private String returnObj;
}
