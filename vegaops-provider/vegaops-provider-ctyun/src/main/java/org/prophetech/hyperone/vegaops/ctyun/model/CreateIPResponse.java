package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateIPResponse extends CtyunResponse {
    private String bandwidthId;
    private String public_ip_address;
    private String type;
    private String status;
    private String create_time;
    private String id;
    private String bandwidth_size;
}
