package org.prophetech.hyperone.vegaops.tencent.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DescribeImagesParam {

    private String imageType;

    private String regionId;

    private Long offset ;

    private Long limit;

}
