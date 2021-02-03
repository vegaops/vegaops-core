package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetJobIdResponse extends CtyunResponse {
    /**
     * 虚拟资源状态
     */
    private String status;
    /**
     * JOB ID
     */
    private String data;
    /**
     * 信息
     */
    private String message;
}
