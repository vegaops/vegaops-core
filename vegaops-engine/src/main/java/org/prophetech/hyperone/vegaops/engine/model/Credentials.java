package org.prophetech.hyperone.vegaops.engine.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Credentials {
    @ApiModelProperty(value = "云账号code")
    private String vendor;

    @ApiModelProperty(value = "云账号账号Key")
    private String key;

    @ApiModelProperty(value = "云账号账号Key对应的secret")
    private String secret;

    @ApiModelProperty(value = "账号")
    private String accountId;

    @ApiModelProperty(value = "地域id")
    private String regionId;

    @ApiModelProperty(value = "zoneId")
    private String zoneId;
}
