package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;

import java.util.List;

@Setter
@Getter
public class QueryAvailableZonesResponse extends CtyunResponse {
    /**
     * 可用分区数组
     */
    private List<AvailableZones> available_zones;

    /**
     * 区域ID。
     */
    private String regionId;
    @Getter
    @Setter
    @NoArgsConstructor
    public static class AvailableZones{

        /**
         * 可用分区ID。
         */
        private String id;
        /**
         * 可用分区编码。
         */
        private String code;
        /**
         * 可用分区名称。
         */
        private String name;

        /**
         * 可用分区端口号。
         */
        private String port;

        /**
         * 分区上是否还有可用资源。
         * true：还有资源。
         * false：资源已售罄。
         */
        private String resource_availability;

        /**
         * 受否未默认可用区
         */
        private Boolean default_az;
    }

}
