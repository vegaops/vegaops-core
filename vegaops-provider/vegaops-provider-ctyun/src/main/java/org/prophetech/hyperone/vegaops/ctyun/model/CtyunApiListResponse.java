package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public  class CtyunApiListResponse<T extends CtyunResponse> extends CtyunApiResponse<T> {
    private List<T> returnObj;
}
