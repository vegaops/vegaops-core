package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunApiResponse;

@Setter
@Getter
public class CtyunApiErrorResponse extends CtyunApiResponse {
    private String message;
}
