package org.prophetech.hyperone.vegaops.aws.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateVirtualInterfaceParam {

    @ApiModelProperty(value = "取值：private | public | transit")
    private String type;

    private String connectId;
    @ApiModelProperty(value = "取值：ipv4 | ipv6")
    private String addressFamily;
    private String address;
    @ApiModelProperty(value = " 有效值：1-2147483647")
    private Integer asn;
    private String authKey;
    private String customerAddress;
    @ApiModelProperty(value = "private/Transit型有效")
    private String directConnectGatewayId;
    @ApiModelProperty(value = " 有效值1500-9001,默认1500 private/Transit型有效")
    private Integer mtu;

    private String virtualGatewayId;
    private String name;
    private Integer vlan;

    @ApiModelProperty(value = "public类型有效")
    private String cidr;


}
