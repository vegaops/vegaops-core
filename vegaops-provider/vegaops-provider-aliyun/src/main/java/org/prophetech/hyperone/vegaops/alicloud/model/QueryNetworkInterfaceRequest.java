package org.prophetech.hyperone.vegaops.alicloud.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryNetworkInterfaceRequest {

    private String networkInterfaceId;

    private String regionId;

    private String accessKey;

    private String secret;

}
