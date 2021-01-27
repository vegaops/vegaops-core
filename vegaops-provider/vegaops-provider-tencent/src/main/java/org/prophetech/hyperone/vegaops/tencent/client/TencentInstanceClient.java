package org.prophetech.hyperone.vegaops.tencent.client;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.cvm.v20170312.CvmClient;
import com.tencentcloudapi.cvm.v20170312.models.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.prophetech.hyperone.vegaops.tencent.model.CreateInstanceParam;
import org.prophetech.hyperone.vegaops.tencent.model.RenewInstanceParam;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Slf4j(topic = "vegaops")
public class TencentInstanceClient {

    private String PREPAID = "PREPAID";
    private String POSTPAID_BY_HOUR = "POSTPAID_BY_HOUR";

    private String key;
    private String secret;


    public Instance createInstance(CreateInstanceParam param) {
        Credential cred = new Credential(key, secret);

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("cvm.tencentcloudapi.com");

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        CvmClient client = new CvmClient(cred, param.getRegionId(), clientProfile);

        RunInstancesRequest req = new RunInstancesRequest();
        // 别忘了↓↓↓↓↓↓↓↓↓↓↓↓↓↓
//        req.setDryRun(true);
        req.setHostName(param.getHostName());
        req.setImageId(param.getImageId());
        req.setInstanceChargeType(param.getInstanceChargeType());
        if (PREPAID.equals(param.getInstanceChargeType())) {
            InstanceChargePrepaid prepaid = new InstanceChargePrepaid();
            prepaid.setPeriod(param.getPeriod());
            prepaid.setRenewFlag(param.getRenewFlag());
            req.setInstanceChargePrepaid(prepaid);
        }
        req.setInstanceCount(param.getInstanceCount());
//            req.setInstanceMarketOptions();
        req.setInstanceName(param.getInstanceName());
        req.setInstanceType(param.getInstanceType());
        InternetAccessible internetAccessible = new InternetAccessible();
        internetAccessible.setInternetChargeType(param.getInternetChargeType());
        internetAccessible.setInternetMaxBandwidthOut(param.getInternetMaxBandwidthOut());
//        internetAccessible.setBandwidthPackageId(param.getBandwidthPackageId());
        // 是否分配公网IP。取值范围：
//        internetAccessible.setPublicIpAssigned(param.getPublicIpAssigned());
        req.setInternetAccessible(internetAccessible);
        LoginSettings loginSettings = new LoginSettings();
        loginSettings.setPassword(param.getPassword());
        if (StringUtils.isBlank(param.getPassword())) {
            String[] keyName = new String[1];
            keyName[0] = param.getKeyName();
            loginSettings.setKeyIds(keyName);
        }
//            loginSettings.setKeepImageLogin();
        req.setLoginSettings(loginSettings);
        Placement placement = new Placement();
        placement.setZone(param.getZone());
        req.setPlacement(placement);

        if (StringUtils.isNotBlank(param.getSecurityGroupId())) {
            req.setSecurityGroupIds(new String[]{param.getSecurityGroupId()});
        }
        SystemDisk systemDisk = new SystemDisk();
        systemDisk.setDiskSize(param.getDiskSize());
        systemDisk.setDiskType(param.getDiskType());
//            systemDisk.setDiskId();
        req.setSystemDisk(systemDisk);
//            req.setTagSpecification();
        req.setUserData(param.getUserData());
        VirtualPrivateCloud cloud = new VirtualPrivateCloud();
        cloud.setVpcId(param.getVpcId());
        cloud.setSubnetId(param.getSubnetId());
        if (StringUtils.isNotBlank(param.getPrivateIpAddresses())) {
            cloud.setPrivateIpAddresses(new String[]{param.getPrivateIpAddresses()});
        }
        // 是否用作公网网关。
        if (param.getAsVpcGateway() == null){
            param.setAsVpcGateway(false);
        }
        cloud.setAsVpcGateway(param.getAsVpcGateway());
        // 为弹性网卡指定随机生成的 IPv6 地址数量。
//        cloud.setIpv6AddressCount(param.getIpv6AddressCount());
//            cloud.setPrivateIpAddresses();
        req.setVirtualPrivateCloud(cloud);

        try {
            RunInstancesResponse resp = client.RunInstances(req);
            Instance instance =  queryInstance(resp.getInstanceIdSet(), param.getRegionId()).get(0);
            return instance;
        } catch (TencentCloudSDKException e) {
            log.error("创建腾讯云主机失败：" + e);
            throw new RuntimeException("创建腾讯云主机失败");
        }
    }

    public List<Instance> queryInstance(String[] ids, String regionId) {
        try {
            List<Instance> instances = new ArrayList<>();
            if (ids == null || ids.length == 0) {
                return instances;
            }
            Credential cred = new Credential(key, secret);
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cvm.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            CvmClient client = new CvmClient(cred, regionId, clientProfile);
            DescribeInstancesRequest req = new DescribeInstancesRequest();
            long waitInterval = 3000;
            long maxWait = 120000;
            for (String id : ids) {
                req.setInstanceIds(new String[]{id});
                long now = System.currentTimeMillis();
                do {
                    DescribeInstancesResponse resp = client.DescribeInstances(req);
                    if (resp.getInstanceSet().length == 0) {
                        log.warn("腾讯云云主机" + id + "不存在");
                        break;
                    }
                    if ("PENDING".equals(resp.getInstanceSet()[0].getInstanceState())) {
                        log.info("腾讯云云主机正在创建中等待" + waitInterval + "毫秒后重新查询状态");
                        try {
                            Thread.sleep(waitInterval);
                        } catch (InterruptedException e) {
                            log.error("重试等待出错");
                        }
                    } else {
                        instances.add(resp.getInstanceSet()[0]);
                        break;
                    }
                } while (System.currentTimeMillis() < now + maxWait);
                if (System.currentTimeMillis() >= now + maxWait) {
                    log.warn("腾讯云云主机" + id + "查询超时");
                }
            }
            return instances;
        } catch (TencentCloudSDKException e) {
            log.error("腾讯云接口请求失败 " + e);
            throw new RuntimeException("创建腾讯云云主机失败");
        }
    }

    public InstanceStatus queryStatus(String regionId,String instanceId){
        try{

            Credential cred = new Credential(key, secret);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cvm.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CvmClient client = new CvmClient(cred, regionId, clientProfile);

            DescribeInstancesStatusRequest req = new DescribeInstancesStatusRequest();
            req.setInstanceIds(new String[]{instanceId});

            DescribeInstancesStatusResponse resp = client.DescribeInstancesStatus(req);
            if (resp.getTotalCount() !=0){
                return resp.getInstanceStatusSet()[0];
            }
        } catch (TencentCloudSDKException e) {
            log.error("腾讯云接口请求失败 " + e);
            throw new RuntimeException("查询腾讯云云主机状态失败");
        }
        return new InstanceStatus();
    }

    //续费预付费实例
    public void renewInstance(RenewInstanceParam param){
        try{

            Credential cred = new Credential(key, secret);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cvm.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CvmClient client = new CvmClient(cred, param.getRegionId(), clientProfile);

            RenewInstancesRequest  req = new RenewInstancesRequest ();
            req.setInstanceIds(new String[]{param.getInstanceId()});
            InstanceChargePrepaid instanceChargePrepaid = new InstanceChargePrepaid();
            instanceChargePrepaid.setPeriod(param.getPeriod());
            if(StringUtils.isNotEmpty(param.getRenewFlag())){
                instanceChargePrepaid.setRenewFlag(param.getRenewFlag());
            }
            req.setInstanceChargePrepaid(instanceChargePrepaid);
            if (param.getRenewPortableDataDisk() != null) {
                req.setRenewPortableDataDisk(param.getRenewPortableDataDisk());
            }
            RenewInstancesResponse resp = client.RenewInstances(req);
        } catch (TencentCloudSDKException e) {
            log.error("腾讯云接口请求失败 " + e);
            throw new RuntimeException("续费预付费主机失败");
        }

    }
}
