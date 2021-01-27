package org.prophetech.hyperone.vegaops.alicloud.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteAliyunInstancesRequest {

    private String instanceId;

    private String regionId;

    private String accessKey;

    private String secret;

}
