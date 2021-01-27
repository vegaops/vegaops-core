package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunApiResponse;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;

@Setter
@Getter
public  class CtyunApiStringResponse<T extends CtyunResponse> extends CtyunApiResponse {
    private String returnObj;
}
