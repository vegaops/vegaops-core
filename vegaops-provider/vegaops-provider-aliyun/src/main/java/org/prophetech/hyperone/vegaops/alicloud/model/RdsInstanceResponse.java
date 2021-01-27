package org.prophetech.hyperone.vegaops.alicloud.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RdsInstanceResponse {

    private String dBInstanceId;

    private String chargeType;

    private String dbType;

    private String engineVersion;

    private String regionId;

    private String engine;

    private String name;

    private String netType;

    private String connectionMode;

    private String instanceType;

    private String guardInstanceId;

    private String tempInstanceId;

    private String readonlyInstanceIds;

    private String vpcId;

    private String vswitchId;

    private String masterInstanceId;

    private String dbInstanceStatus;

    private String instanceNetworkType;

    private String autoUpgradeMinorVersion;

    private String category;

    private String dbInstanceClass;

    private String dbInstanceStorageType;

    private Boolean mutriOrsignle;

    private Integer maxiops;

    private Long dbInstanceMemory;

    private String zoneId;

    private Integer maxConnections;

    private String dbInstanceCpu;

    private String port;

    private Integer dbInstanceStorage;

    private String dbInstanceClassType;

    private String createTime;

    private String expireTime;

}
