package org.prophetech.hyperone.vegaops.tencent.client;

import com.tencentcloudapi.cbs.v20170312.CbsClient;
import com.tencentcloudapi.cbs.v20170312.models.*;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.prophetech.hyperone.vegaops.tencent.model.CreateVolumeParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Slf4j(topic = "vegaops")
public class TencentVolumeClient {

    private String PREPAID = "PREPAID";
    private String POSTPAID_BY_HOUR = "POSTPAID_BY_HOUR";

    private String key;
    private String secret;

    public List<Disk> queryVolumes(String region, String volumeId) {
        Credential cred = new Credential(key, secret);

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("cbs.tencentcloudapi.com");

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        CbsClient client = new CbsClient(cred, region, clientProfile);

        Long pageSize = 100L;
        DescribeDisksRequest req = new DescribeDisksRequest();
        if (StringUtils.isNotBlank(volumeId)){
            String[] diskIds = new String[1];
            diskIds[0] = volumeId;
            req.setDiskIds(diskIds);
        }
        req.setLimit(pageSize);
        req.setOffset(0L);
        List<Disk> disks = new ArrayList<>();
        DescribeDisksResponse resp = new DescribeDisksResponse();
        do {
            try {
                resp = client.DescribeDisks(req);
                if (resp.getDiskSet().length != 0) {
                    disks.addAll(Arrays.asList(resp.getDiskSet()));
                }
                req.setOffset(pageSize+req.getOffset());
            } catch (TencentCloudSDKException e) {
                e.printStackTrace();
            }
        } while (resp.getDiskSet().length != 0);
        return disks;

    }

    public Disk createVolume(CreateVolumeParam param){
        Credential cred = new Credential(key, secret);

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("cbs.tencentcloudapi.com");

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        CbsClient client = new CbsClient(cred, param.getRegionId(), clientProfile);
        List<Disk> disks = new ArrayList<>();
        CreateDisksRequest req = new CreateDisksRequest();

        req.setClientToken(param.getClientToken());
        req.setDiskChargeType(param.getDiskChargeType());
        if (PREPAID.equals(param.getDiskChargeType())){
            DiskChargePrepaid chargePrepaid = new DiskChargePrepaid();
            chargePrepaid.setPeriod(param.getPeriod());
            req.setDiskChargePrepaid(chargePrepaid);
        }
        req.setDiskCount(param.getDiskCount());
        req.setDiskName(param.getDiskName());
        req.setDiskSize(param.getDiskSize());
        req.setEncrypt(param.getEncrypt());
        req.setShareable(param.getShareable());
        req.setTags(null);
        Placement placement = new Placement();
        placement.setZone(param.getZone());
        req.setPlacement(placement);
        req.setSnapshotId(param.getSnapshotId());
        req.setDiskType(param.getDiskType());
        try {
            CreateDisksResponse resp = client.CreateDisks(req);
            if (resp.getDiskIdSet().length != 0){
                 disks = pollingVolume(param.getRegionId(), resp.getDiskIdSet()[0]);
//                BeanUtils.copyProperties(resp.getDiskIdSet()[0], disk);
            }
            return disks.get(0);
        } catch (TencentCloudSDKException e) {
            log.error("创建腾讯云云盘失败："+e);
            throw new RuntimeException("创建腾讯云云盘失败");
        }
    }

    public List<Disk> pollingVolume(String regionId, String volumeId){
        long now = System.currentTimeMillis();
        long waitInterval = 3000;
        List<Disk> disks = new ArrayList<>();
        long maxWait = 24000;
        do {
            disks = queryVolumes(regionId, volumeId);
            if (CollectionUtils.isNotEmpty(disks)){
                return disks;
            }
            try {
                Thread.sleep(waitInterval);
            } catch (InterruptedException e) {
                log.error("等待查询腾讯云云盘时出错");
            }

        }while (System.currentTimeMillis() < now + maxWait);
        throw new RuntimeException("腾讯云查询新创建的云盘时超时");

    }
}
