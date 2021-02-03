package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CtyunApiErrorResponse extends CtyunApiResponse {
    private String message;
}
