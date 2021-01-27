package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;

@Setter
@Getter
public abstract class CtyunApiResponse<T extends CtyunResponse> extends CtyunResponse {
    private int statusCode;

    private String resultJSON;
}
