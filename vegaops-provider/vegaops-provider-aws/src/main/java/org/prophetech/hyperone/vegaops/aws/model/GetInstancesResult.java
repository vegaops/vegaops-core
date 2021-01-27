package org.prophetech.hyperone.vegaops.aws.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetInstancesResult {
    private Integer statusCode;
    private List<InstanceParser> instances;

}
