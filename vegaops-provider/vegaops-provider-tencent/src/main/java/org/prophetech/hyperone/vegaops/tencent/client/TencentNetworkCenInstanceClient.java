package org.prophetech.hyperone.vegaops.tencent.client;

import com.alibaba.fastjson.JSONObject;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vpc.v20170312.VpcClient;
import com.tencentcloudapi.vpc.v20170312.models.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@Slf4j(topic = "vegaops")
public class TencentNetworkCenInstanceClient {

    private String key;
    private String secret;

    public CcnAttachedInstance queryCenInstance(String instanceId, String regionId, String cenId) {
        try {
            Credential cred = new Credential(key, secret);
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            VpcClient client = new VpcClient(cred, regionId, clientProfile);
            DescribeCcnAttachedInstancesRequest req = new DescribeCcnAttachedInstancesRequest();
            if (StringUtils.isNotBlank(instanceId)) {
                Filter filter = new Filter();
                filter.setName("instance-id");
                filter.setValues(new String[]{instanceId});
                req.setFilters(new Filter[]{filter});
            }
            if (StringUtils.isNotBlank(cenId)) {
                req.setCcnId(cenId);
            }
            DescribeCcnAttachedInstancesResponse resp = client.DescribeCcnAttachedInstances(req);
            if (resp.getInstanceSet().length > 0) {
                return resp.getInstanceSet()[0];
            }
            return null;
        } catch (TencentCloudSDKException e) {
            String message = String.format("查询云联网[%s]关联实例[%s],requestId:[%s],异常:%s",
                    cenId, instanceId, e.getRequestId(), e.getMessage());
            log.error(message, e);
            throw new RuntimeException(message);
        }
    }

    public void attachedInstance(String instanceId, String cenId, String regionId, String instanceType,
                                 String instanceAccountId, String cenAccountId, String instanceSecret,
                                 String instanceRegionId, String instanceKey) {
        log.info("关联实例参数instanceId:[{}],cenId:[{}],regionId:[{}],instanceType:[{}]," +
                        "instanceAccountId:[{}],cenAccountId:[{}],instanceRegionId:[{}],instanceKey:[{}]",
                instanceId, cenId, regionId, instanceType, instanceAccountId,
                cenAccountId, instanceRegionId, instanceKey);
        CcnAttachedInstance ccnAttachedInstance = queryCenInstance(instanceId, regionId, cenId);
        if (ccnAttachedInstance == null) {
            attachInstance(instanceId, cenId, regionId, instanceType, instanceRegionId, cenAccountId,
                    instanceKey, instanceSecret);
            ccnAttachedInstance = queryCenInstance(instanceId, regionId, cenId);
        }
        if (ccnAttachedInstance != null
                && ccnAttachedInstance.getState().equalsIgnoreCase("PENDING")) {
            // 不同账号,接受申请
            log.info("Cross account.Accept application");
            acceptAttachCcnInstance(cenId, regionId, instanceId, instanceType, instanceRegionId);
        }

    }

    private void acceptAttachCcnInstance(String cenId, String regionId, String instanceId, String instanceType,
                                         String instanceRegionId) {
        try {
            Credential cred = new Credential(key, secret);
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            VpcClient client = new VpcClient(cred, regionId, clientProfile);
            String params = "{}";
            AcceptAttachCcnInstancesRequest request = AcceptAttachCcnInstancesRequest.fromJsonString(params,
                    AcceptAttachCcnInstancesRequest.class);
            request.setCcnId(cenId);
            CcnInstance ccnInstance = new CcnInstance();
            ccnInstance.setInstanceRegion(instanceRegionId);
            ccnInstance.setInstanceId(instanceId);
            ccnInstance.setInstanceType(instanceType);
            request.setInstances(new CcnInstance[]{ccnInstance});
            log.info("云联网接受关联实例参数:{}", JSONObject.toJSONString(request));
            AcceptAttachCcnInstancesResponse response = client.AcceptAttachCcnInstances(request);
            log.info("云联网接受关联实例返回结果:{}", JSONObject.toJSONString(response));
        } catch (TencentCloudSDKException e) {
            String message = String.format("云联网[%s]接受关联实例[%s],requestId:[%s],异常:%s",
                    cenId, instanceId, e.getRequestId(), e.getMessage());
            log.error(message, e);
            throw new RuntimeException(message);
        }
    }

    private void applyAttacheInstance(String cenId, String regionId, String cenAccountId, String instanceId,
                                      String instanceRegionId, String instanceType, String instanceKey,
                                      String instanceSecret) {
        try {
            Credential cred = new Credential(instanceKey, instanceSecret);
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            VpcClient client = new VpcClient(cred, regionId, clientProfile);
            String params = "{}";
            ResetAttachCcnInstancesRequest request = ResetAttachCcnInstancesRequest.fromJsonString(params,
                    ResetAttachCcnInstancesRequest.class);
            request.setCcnId(cenId);
            request.setCcnUin(cenAccountId);
            CcnInstance ccnInstance = new CcnInstance();
            ccnInstance.setInstanceRegion(instanceRegionId);
            ccnInstance.setInstanceId(instanceId);
            ccnInstance.setInstanceType(instanceType);
            request.setInstances(new CcnInstance[]{ccnInstance});
            log.info("申请关联云联网参数:{}", JSONObject.toJSONString(request));
            ResetAttachCcnInstancesResponse response = client.ResetAttachCcnInstances(request);
            log.info("申请关联云联网返回结果:{}", JSONObject.toJSONString(response));
        } catch (TencentCloudSDKException e) {
            String message = String.format("[%s]申请关联云联网[%s],requestId:[%s],异常:%s",
                    instanceId, cenId, e.getRequestId(), e.getMessage());
            log.error(message, e);
            throw new RuntimeException(message);
        }
    }


    private void attachInstance(String instanceId, String cenId, String regionId, String instanceType,
                                String instanceRegionId, String cenAccountId, String instanceKey, String instanceSecret) {
        try {
            Credential cred = new Credential(instanceKey, instanceSecret);
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, regionId, clientProfile);

            AttachCcnInstancesRequest request = new AttachCcnInstancesRequest();
            CcnInstance ccnInstance = new CcnInstance();
            ccnInstance.setInstanceRegion(instanceRegionId);
            ccnInstance.setInstanceId(instanceId);
            ccnInstance.setInstanceType(instanceType);
            request.setInstances(new CcnInstance[]{ccnInstance});
            request.setCcnId(cenId);
            if (StringUtils.isNotEmpty(cenAccountId)) {
                request.setCcnUin(cenAccountId);
            }
            log.info("同账号网络实例互通参数:{}", JSONObject.toJSONString(request));
            AttachCcnInstancesResponse response = client.AttachCcnInstances(request);
            log.info("同账号网络实例互通返回结果:{}", JSONObject.toJSONString(response));
        } catch (TencentCloudSDKException e) {
            String message = String.format("同账号网络实例互通,云联网[%s],实例[%s],requestId:[%s],异常:%s",
                    cenId, instanceId, e.getRequestId(), e.getMessage());
            log.error(message, e);
            throw new RuntimeException(message);
        }
    }

    public void detachInstance(String instanceId, String cenId, String regionId, String instanceType, String instanceRegionId) {
        try {

            Credential cred = new Credential(key, secret);
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vpc.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            VpcClient client = new VpcClient(cred, regionId, clientProfile);

            DetachCcnInstancesRequest req = new DetachCcnInstancesRequest();
            req.setCcnId(cenId);
            CcnInstance ccnInstance = new CcnInstance();
            ccnInstance.setInstanceId(instanceId);
            ccnInstance.setInstanceRegion(instanceRegionId);
            ccnInstance.setInstanceType(instanceType);
            req.setInstances(new CcnInstance[]{ccnInstance});
            client.DetachCcnInstances(req);
        } catch (TencentCloudSDKException e) {
            log.error("腾讯云接口请求失败 " + e);
            throw new RuntimeException("查询腾讯云云企业网实例失败");
        }
    }
}
