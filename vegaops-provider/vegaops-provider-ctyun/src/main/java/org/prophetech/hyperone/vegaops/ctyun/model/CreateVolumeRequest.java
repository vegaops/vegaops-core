package org.prophetech.hyperone.vegaops.ctyun.model;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.annotation.IgnoreParam;

/**
 * 绑定磁盘(按需)
 */
@Setter
@Getter
public class CreateVolumeRequest extends CtyunRequest<GetJobIdResponse> {
    /**
     * 专属云资源池ID，请根据查询projectId接口返回值进行传参，获取“id”参数
     */
    private String projectId;

    private String createVolumeInfo;

    /**
     * 资源池id 请根据查询资源池接口返回值进行传参，获取“regionId”参数
     */
    @IgnoreParam
    private String regionId;
    /**
     * 资源池可用区id 请根据查询资源池接口返回值进行传参，获取“zoneId”参数
     */
    @IgnoreParam
    private String zoneId;
    /**
     * 磁盘名称
     */
    @IgnoreParam
    private String name;
    /**
     * 系统盘相关配置 目前支持“SSD”，“SAS”和“SATA”三种，测试床只支持SATA类型
     */
    @IgnoreParam
    private String type;
    /**
     * 磁盘大小-----单位为GB，取值范围：10GB-32768GB创建建空白磁盘和从镜像创建磁盘时，size为必选，且磁盘大小不能小于镜像大小。从备份创建磁盘时，size为可选，不指定size时，磁盘大小和备份大小一致。
     */
    @IgnoreParam
    private String size;
    /**
     * 批量创磁盘的个数 如果无该参数，表明只创建1个磁盘，目前最多支持批量创建100个。
     */
    @IgnoreParam
    private String count;
    /**
     * 备份id （从备份创建磁盘时为必选）
     */
    @IgnoreParam
    private String backupId;

    @Override
    public void init() {
        createVolumeInfo = JSON.toJSONString(getIgnoreParamMap());
    }


    @Override
    public String getUrl() {
        return "/apiproxy/v3/ondemand/createVolume";
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
    public Class getResponseClass() {
        return GetJobIdResponse.class;
    }
}
