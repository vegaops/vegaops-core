package org.prophetech.hyperone.vegaops.ctyun.client;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.prophetech.hyperone.vegaops.ctyun.exception.ClientException;
import org.prophetech.hyperone.vegaops.ctyun.exception.ServerException;
import org.prophetech.hyperone.vegaops.ctyun.model.*;
import org.prophetech.hyperone.vegaops.engine.utils.FastBeanCopier;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Setter
@Getter
@Slf4j(topic = "vegaops")
public class CtyunVolumeClient {

    private String PAYASYOUGO = "PostPaid";

    private String SUBSCRIPTION = "PrePaid";

    private String ORDERCOMPLETE = "3";

    private CtyunAccount ctyunAccount;


    public CreateDatadiskRequest createVolume(CreateDatadiskRequest datadiskRequest) throws ServerException, ClientException {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        if (StringUtils.isBlank(datadiskRequest.getPayType())) {
            throw new ClientException("创建天翼云磁盘付费类型不可为空");
        }
        if (PAYASYOUGO.equals(datadiskRequest.getPayType())) {
            CreateVolumeRequest request = new CreateVolumeRequest();
            request.setRegionId(datadiskRequest.getRegionId());
            request.setZoneId(datadiskRequest.getZoneId());
            request.setName(datadiskRequest.getName());
            request.setType(datadiskRequest.getType());
            request.setSize(datadiskRequest.getSize() + "");
            request.setCount(datadiskRequest.getCount());
            request.setBackupId(datadiskRequest.getBackupId());
            CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
            datadiskRequest.setStatusCode(ctyunResponse.getStatusCode());
            if (ctyunResponse.getStatusCode() == 800) {
                CtyunApiObjectResponse<GetJobIdResponse> response = (CtyunApiObjectResponse) ctyunResponse;
                CtyunApiObjectResponse<QueryVolumeJobResponse> jobResponse = queryJobStatus(datadiskRequest.getRegionId(), response.getReturnObj().getData());
                String id = jobResponse.getReturnObj().getEntities().getVolume_id();
                datadiskRequest.setId(id);
                datadiskRequest.setName(jobResponse.getReturnObj().getEntities().getName());
                datadiskRequest.setType(jobResponse.getReturnObj().getEntities().getVolume_type());
                datadiskRequest.setSize(jobResponse.getReturnObj().getEntities().getSize());
                log.info("创建天翼云磁盘完成,"+datadiskRequest.getId());
                return datadiskRequest;
            }
            log.error("创建天翼云后付费磁盘失败" + JSON.toJSONString(ctyunResponse));
            throw new ClientException("创建天翼云后付费磁盘失败");
        } else {
            PlaceNewPurchaseOrderRequest request = new PlaceNewPurchaseOrderRequest();
            OrderDetail orderDetail = new OrderDetail();
            OrderDetail.Order order = new OrderDetail.Order();
            order.setInstanceCnt(datadiskRequest.getCount());
            order.setCycleCnt("");
            order.setCycleType("");
            OrderDetail.Item volumeItem = new OrderDetail.Item();
            volumeItem.setMaster("true");
            volumeItem.setResourceType("EBSC");
            volumeItem.setServiceTag("HWS");
            volumeItem.setItemValue(datadiskRequest.getSize());
            OrderDetail.Ebsc ebsc = new OrderDetail.Ebsc();
            ebsc.setVolumeType(datadiskRequest.getType());
            ebsc.setVolumeName(datadiskRequest.getName());
            ebsc.setSize(datadiskRequest.getSize());
            volumeItem.setItemConfig(ebsc);
            order.setItems(Arrays.asList(volumeItem));
            orderDetail.setOrders(Arrays.asList(order));
            CustomInfo customInfo = new CustomInfo();
            customInfo.setType(datadiskRequest.getCustomerInfoType());
            customInfo.setName(datadiskRequest.getCustomerName());
            customInfo.setPhone(datadiskRequest.getCustomerPhone());
            customInfo.setEmail(datadiskRequest.getCustomerEmail());
            CustomInfo.Identity identity = new CustomInfo.Identity();
            identity.setAccountId(datadiskRequest.getCustomerAccountId());
            identity.setCrmBizId(datadiskRequest.getCustomerCrmBizId());
            customInfo.setIdentity(identity);
            orderDetail.setCustomInfo(customInfo);
            request.setCustomInfo(customInfo);
            request.setOrderDetailJson(orderDetail.toJson());
            CtyunApiResponse response = client.getCtyunResponse(request);
            datadiskRequest.setStatusCode(response.getStatusCode());
            if (response.getStatusCode() == 800) {
                CtyunApiObjectResponse<PlaceNewPurchaseOrderResponse> orderResponse = (CtyunApiObjectResponse) response;
                PayGeneralUserOrderRequest payRequest = new PayGeneralUserOrderRequest();
                payRequest.setCash(orderResponse.getReturnObj().getTotalPrice());
                payRequest.setMasterOrderId(orderResponse.getReturnObj().getNewOrderId());
                CtyunApiResponse payResponse = client.getCtyunResponse(payRequest);
                datadiskRequest.setStatusCode(response.getStatusCode());
                if (payResponse.getStatusCode() == 800) {
                    CreateDatadiskRequest volume = queryOrderStatus(orderResponse.getReturnObj().getNewOrderId());
                    datadiskRequest.setId(volume.getId());
                    log.info("创建天翼云磁盘完成,"+datadiskRequest.getId());
                    return datadiskRequest;
                }
                log.error("支付天翼云预付费磁盘订单失败" + JSON.toJSONString(payResponse));
                throw new ClientException("支付天翼云预付费磁盘失败");
            }
            log.error("创建天翼云预付费磁盘订单失败" + JSON.toJSONString(response));
            throw new ClientException("创建天翼云预付费磁盘失败");
        }
    }

    public CtyunApiObjectResponse<QueryVolumeJobResponse> queryJobStatus(String regionId, String jobId) throws ServerException, ClientException {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        long now = System.currentTimeMillis();
        long waitInterval = 3000;
        long maxWait = 180000;
        do {
            QueryVolumeJobRequest request = new QueryVolumeJobRequest();
            request.setJobId(jobId);
            request.setRegionId(regionId);
            CtyunApiResponse response = client.getCtyunResponse(request);
            Map result = JSON.parseObject(response.getResultJSON(), HashMap.class);
            if (response.getStatusCode() == 800) {
                CtyunApiObjectResponse<QueryVolumeJobResponse> jobResponse = (CtyunApiObjectResponse) response;
                if ("SUCCESS".equals(jobResponse.getReturnObj().getStatus())) {
                    return jobResponse;
                } else if ("FAILED".equals(jobResponse.getReturnObj().getStatus())) {
                    log.warn("天翼云磁盘,Job执行失败,errorCode:{} 错误原因:{}", jobResponse.getReturnObj().getError_code(), jobResponse.getReturnObj().getFail_reason());
                    throw new ClientException("天翼云资源Job执行失败 " + jobResponse.getReturnObj().getFail_reason());
                }
            } else if (response.getStatusCode() == 900 && result.containsKey("message") && StringUtils.isBlank((String) result.get("message"))) {
                log.info("天翼云磁盘JOB:{}未查询到结果,等待{}毫秒后重新执行", jobId, waitInterval);
                try {
                    Thread.sleep(waitInterval);
                } catch (InterruptedException e) {
                    log.error("重试Job：{}等待出错", jobId);
                }
            } else {
                log.warn("天翼云磁盘,Job:{}执行失败,message:{}", jobId, result.get("message"));
                throw new ClientException("天翼云资源Job执行失败 " + result);

            }
        } while (System.currentTimeMillis() < now + maxWait);
        throw new ClientException("天翼云JOB接口轮询超时,jobId:" + jobId + " regionId:" + regionId);
    }


    public CreateDatadiskRequest queryOrderStatus(String orderId) throws ServerException, ClientException {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        QueryMasterOrderRequest request = new QueryMasterOrderRequest();
        long now = System.currentTimeMillis();
        long waitInterval = 3000;
        long maxWait = 180000;
        do {
            request.setMasterOrderId(orderId);
            CtyunApiResponse response = client.getCtyunResponse(request);
            if (response.getStatusCode() == 800) {
                CtyunApiObjectResponse<QueryVMsByOrderIdResponse> orderResponse = (CtyunApiObjectResponse) response;
                String status = orderResponse.getReturnObj().getStatus();
                if (ORDERCOMPLETE.equals(status)) {
                    return queryOrderDetail(orderId);
                }
                try {
                    log.info("天翼云云磁盘订单{}未完成,等待{}毫秒后重新执行", orderId, waitInterval);
                    Thread.sleep(waitInterval);
                } catch (InterruptedException e) {
                    log.error("天翼云磁盘订单{}查询，等待出错", orderId);
                }
            } else {
                log.error("天翼云查询订单失败:" + JSON.toJSONString(response));
                throw new ClientException("天翼云查询订单失败");
            }
        } while (System.currentTimeMillis() < now + maxWait);
        throw new ClientException("天翼云订单" + orderId + "长时间未完成,已终止");
    }

    public CreateDatadiskRequest queryOrderDetail(String orderId) throws ServerException, ClientException {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        QueryResourceInfoByMasterOrderIdRequest request = new QueryResourceInfoByMasterOrderIdRequest();
        request.setMasterOrderId(orderId);
        CtyunApiResponse response = client.getCtyunResponse(request);
        if (response.getStatusCode() == 800) {
            CtyunApiListResponse<QueryResourceInfoByMasterOrderIdResponse> orderResponse = (CtyunApiListResponse) response;
            CreateDatadiskRequest volume = new CreateDatadiskRequest();
            QueryResourceInfoByMasterOrderIdResponse order = orderResponse.getReturnObj().get(0);
            volume.setId(order.getMasterResourceId());
            return volume;
        } else {
            log.error("天翼云查询订单详情失败:" + JSON.toJSONString(response));
            throw new ClientException("天翼云查询订单详情失败");
        }
    }

    public Integer deleteVolume(DeleteVolumeRequest request) throws ServerException, ClientException {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        CtyunApiResponse response = client.getCtyunResponse(request);
        if (response.getStatusCode() == 800) {
            CtyunApiObjectResponse<GetJobIdResponse> jobResponse = (CtyunApiObjectResponse) response;
            CtyunApiObjectResponse<QueryVolumeJobResponse> resultResponse = queryJobStatus(request.getRegionId(), jobResponse.getReturnObj().getData());
            return resultResponse.getStatusCode();
        }
        log.error("删除天翼云磁盘失败" + JSON.toJSONString(response));
        throw new ClientException("删除天翼云磁盘失败");
    }

    public List<VolumeResult> getVolumes(GetVolumesRequest request) throws ServerException, ClientException {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        if(ctyunResponse.getStatusCode() == 800){
            CtyunApiObjectResponse<GetVolumesResponse> response = (CtyunApiObjectResponse) ctyunResponse;
            List<VolumeResult> results = response.getReturnObj().getVolumes().stream().map(o->{
                VolumeResult result = new VolumeResult();
                result.setRegionId(request.getRegionId());
                FastBeanCopier.copy(o, result);
                if (StringUtils.isNotBlank(o.getMasterOrderId())){
                    result.setPayType(SUBSCRIPTION);
                }else {
                    result.setPayType(PAYASYOUGO);
                }
                if (CollectionUtils.isNotEmpty(o.getAttachments())){
                    result.setServer_id(o.getAttachments().get(0).getServer_id());
                }
                return result;
            }).collect(Collectors.toList());
            return results;
        }
        log.error("查询云盘失败" + JSON.toJSONString(ctyunResponse));
        throw new ClientException("查询云盘失败");
    }
}
