package org.prophetech.hyperone.vegaops.alicloud.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DescribeVbrRequest {

    private String vbrId;

    private String regionId;

    private String accessKey;

    private String secret;

}
