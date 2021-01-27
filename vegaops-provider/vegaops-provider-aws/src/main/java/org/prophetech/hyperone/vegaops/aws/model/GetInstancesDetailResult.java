package org.prophetech.hyperone.vegaops.aws.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetInstancesDetailResult {
    private Integer statusCode;
    private InstanceParser instances;

}
