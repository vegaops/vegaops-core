package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpgradeNetworkRequest extends CtyunRequest<CtyunResponse> {

    /**
     * 1、取值范围：1-64，支持数字、字母、中文、_(下划线)、-（中划线），为空或者为null表示不修改名称 2、功能说明：带宽名称 3、约束：和参数size必须有一个参数有值
     */
    private String name;
    /**
     * 资源池id 请根据查询资源池接口返回值进行传参，获取“regionId”参数
     */
    private String regionId;
    /**
     * 取值范围：1-2000Mbit/s，不带或者为0时表示不修改大小2、功能说明：带宽大小 3、约束：和参数name必须有一个参数有值
     */
    private String size;
    /**
     * 公网id
     */
    private String publicIpId;

    @Override
    public String getUrl() {
        return "/apiproxy/v3/ondemand/upgradeNetwork";
    }

    @Override
    public Method getMethod() {
        return Method.POST;
    }

    @Override
    public BodyType getBodyType() {
        return BodyType.OBJECT;
    }

    @Override
    public Class<CtyunResponse> getResponseClass() {
        return CtyunResponse.class;
    }
}
