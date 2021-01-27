instanceChargeType instance_charge_type PREPAID, POSTPAID_BY_HOUR, The default is POSTPAID_BY_HOUR
period instance_charge_type_prepaid_period  month
renewalStatus instance_charge_type_prepaid_renew_flag  NOTIFY_AND_AUTO_RENEW NOTIFY_AND_MANUAL_RENEW DISABLE_NOTIFY_AND_MANUAL_RENEW
internetChargeType internet_charge_type BANDWIDTH_PREPAID, TRAFFIC_POSTPAID_BY_HOUR, BANDWIDTH_POSTPAID_BY_HOUR and BANDWIDTH_PACKAGE. Default is TRAFFIC_POSTPAID_BY_HOUR
internetMaxBandwidthOut internet_max_bandwidth_out [0-200]
systemDiskCategory system_disk_type CLOUD_BASIC, CLOUD_SSD and CLOUD_PREMIUM.
DiskChargeType charge_type PREPAID,POSTPAID_BY_HOUR,CDCPAID    PREPAID：预付费，即包年包月 POSTPAID_BY_HOUR：按小时后付费 CDCPAID：独享集群付费

### 示例
```json
{
  "cloudAccountId": "1130756940749545473",
  "zoneid": "1145598985851678720",
  "regionId": "1145596722535301120",
  "componentId": "1150917920457363456",
  "containerType": "tencent",
  "nodes": [
    {
      "componentId": "securityGroupRule_l72e0pk16xo0",
      "leafNode": true,
      "nodeType": "securityGroupRule",
      "properties": [
        {
          "key": "protocol",
          "value": "tcp"
        },
        {
          "key": "portStart",
          "value": "80"
        },
        {
          "key": "cidr",
          "value": "0.0.0.0/0"
        },
        {
          "key": "portEnd",
          "value": "80"
        },
        {
          "key": "policy",
          "value": "1"
        },
        {
          "key": "direction",
          "value": "ingress"
        }
      ],
      "relations": []
    },
    {
      "componentId": "vswitch-1",
      "leafNode": false,
      "nodeType": "vswitch",
      "properties": [
        {
          "key": "cidrBlock",
          "value": "10.0.0.0/16"
        },
        {
          "key": "vpcId"
        },
        {
          "key": "name",
          "value": "hyberbinTestVpc"
        },
        {
          "key": "description"
        }
      ],
      "relations": [
        {
          "componentId": "1131446588495323140",
          "principalId": "vswitch-1",
          "relationType": "child",
          "targetId": "securityGroup-1"
        }
      ]
    },
    {
      "componentId": "securityGroup-1",
      "createdId": "sg-m5eeyl9i13bfsdpn1hv6",
      "leafNode": false,
      "nodeType": "securityGroup",
      "properties": [
        {
          "key": "providerId",
          "value": ""
        },
        {
          "key": "vpcId"
        },
        {
          "key": "name",
          "value": "hyberbinTestSg"
        },
        {
          "key": "description",
          "value": "hyberbinTestSg"
        },
        {
          "key": "type"
        }
      ],
      "relations": [
        {
          "componentId": "relation_20avofr4ky1s0",
          "principalId": "securityGroup-1",
          "relationType": "child",
          "targetId": "securityGroupRule_l72e0pk16xo0"
        },
        {
          "componentId": "relation_9sp58qwmmm00",
          "principalId": "securityGroup-1",
          "relationType": "child",
          "targetId": "securityGroupRule_1qc64hknvgzk0"
        }
      ]
    },
    {
      "componentId": "instance-1",
      "leafNode": false,
      "nodeType": "instance",
      "properties": [
        {
          "key": "instanceChargeType",
          "value": "POSTPAID_BY_HOUR"
        },
        {
          "key": "imageId",
          "value": "img-9qabwvbn"
        },
        {
          "key": "internetChargeType",
          "value": "TRAFFIC_POSTPAID_BY_HOUR"
        },
        {
          "key": "userData"
        },
        {
          "key": "internetMaxBandwidthOut",
          "value": "1"
        },
        {
          "key": "name",
          "value": "hyberbinTest"
        },
        {
          "key": "flavorId",
          "value": "S4.SMALL1"
        },
        {
          "key": "description"
        },
        {
          "key": "internetMaxBandwidthIn"
        },
        {
          "key": "keyPairName",
          "value": ""
        },
        {
          "key": "password",
          "value": "root123."
        },
        {
          "key": "periodUnit",
          "value": "Month"
        },
        {
          "key": "systemDiskCategory",
          "value": "CLOUD_PREMIUM"
        },
        {
          "key": "systemDiskSize",
          "value": "50"
        }
      ],
      "relations": [
        {
          "componentId": "1131446588495323137",
          "principalId": "instance-1",
          "relationType": "child",
          "targetId": "vpc-1"
        },
        {
          "componentId": "relation_1qhiv45fzcxs",
          "principalId": "instance-1",
          "relationType": "child",
          "targetId": "volume_6bmvn8sbq280"
        }
      ]
    },
    {
      "componentId": "vpc-1",
      "leafNode": false,
      "nodeType": "vpc",
      "properties": [
        {
          "key": "cidrBlock",
          "value": "10.0.0.0/16"
        },
        {
          "key": "name",
          "value": "hyberbinTest"
        },
        {
          "key": "description"
        }
      ],
      "relations": [
        {
          "componentId": "1131446588495323139",
          "principalId": "vpc-1",
          "relationType": "child",
          "targetId": "vswitch-1"
        }
      ]
    },
    {
      "componentId": "securityGroupRule_1qc64hknvgzk0",
      "leafNode": true,
      "nodeType": "securityGroupRule",
      "properties": [
        {
          "key": "protocol",
          "value": "tcp"
        },
        {
          "key": "portStart",
          "value": "80"
        },
        {
          "key": "cidr",
          "value": "0.0.0.0/0"
        },
        {
          "key": "portEnd",
          "value": "80"
        },
        {
          "key": "policy",
          "value": "1"
        },
        {
          "key": "direction",
          "value": "egress"
        }
      ],
      "relations": []
    },
    {
      "componentId": "volume_6bmvn8sbq280",
      "leafNode": true,
      "nodeType": "volume",
      "properties": [
        {
          "key": "chargeType",
          "value": "POSTPAID_BY_HOUR"
        },
        {
          "key": "size",
          "value": "40"
        },
        {
          "key": "name"
        },
        {
          "key": "description"
        },
        {
          "key": "category",
          "value": "CLOUD_PREMIUM"
        }
      ],
      "relations": []
    }
  ],
  "relations": [
    {
      "componentId": "relation_20avofr4ky1s0",
      "principalId": "securityGroup-1",
      "relationType": "child",
      "targetId": "securityGroupRule_l72e0pk16xo0"
    },
    {
      "componentId": "1131446588495323137",
      "principalId": "instance-1",
      "relationType": "child",
      "targetId": "vpc-1"
    },
    {
      "componentId": "1131446588495323139",
      "principalId": "vpc-1",
      "relationType": "child",
      "targetId": "vswitch-1"
    },
    {
      "componentId": "relation_9sp58qwmmm00",
      "principalId": "securityGroup-1",
      "relationType": "child",
      "targetId": "securityGroupRule_1qc64hknvgzk0"
    },
    {
      "componentId": "1131446588495323140",
      "principalId": "vswitch-1",
      "relationType": "child",
      "targetId": "securityGroup-1"
    },
    {
      "componentId": "relation_1qhiv45fzcxs",
      "principalId": "instance-1",
      "relationType": "child",
      "targetId": "volume_6bmvn8sbq280"
    }
  ]
}
```

