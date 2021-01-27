package org.prophetech.hyperone.vegaops.tencent.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteSgRuleParam {

    private String securityGroupId;
    private String direction;
    private String cidrBlock;
    private String ipv6CidrBlock;
    private String action;
    private String index;
    private String port;
    private String protocol;
    private String regionId;
    private String accessKey;
    private String secret;
    private String providerId;

}
