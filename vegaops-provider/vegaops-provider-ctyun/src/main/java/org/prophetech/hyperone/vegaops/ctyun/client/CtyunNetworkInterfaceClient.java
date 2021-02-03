package org.prophetech.hyperone.vegaops.ctyun.client;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.prophetech.hyperone.vegaops.ctyun.exception.ClientException;
import org.prophetech.hyperone.vegaops.ctyun.exception.ServerException;
import org.prophetech.hyperone.vegaops.ctyun.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@Slf4j(topic = "vegaops")
public class CtyunNetworkInterfaceClient {
    private CtyunAccount ctyunAccount;

    private String PAYASYOUGO = "PostPaid";

    private String SUBSCRIPTION = "PrePaid";


    public List<QueryNetworkCardsResponse.InterfaceAttachments> getAllNetworkInterface(String regionId, String instanceId) throws ServerException, ClientException {
        List<QueryNetworkCardsResponse.InterfaceAttachments> responses = new ArrayList<>();
        GetVMsRequest request = new GetVMsRequest();
        request.setRegionId(regionId);
        List<QueryNetworkCardsResponse.InterfaceAttachments> netList = getNetworkInterface(regionId,instanceId );
        responses.addAll(netList);
        return responses;
    }

    public List<QueryNetworkCardsResponse.InterfaceAttachments> getNetworkInterface(String regionId, String vmId) throws ServerException, ClientException {
        QueryNetworkCardsRequest request = new QueryNetworkCardsRequest();
        CtyunJsoupClient client = new CtyunJsoupClient();
        request.setRegionId(regionId);
        request.setVmId(vmId);
        client.setCtyunAccount(ctyunAccount);
        CtyunApiResponse response = client.getCtyunResponse(request);
        if (response.getStatusCode() == 800) {
            CtyunApiObjectResponse<QueryNetworkCardsResponse> ctyunResponse = (CtyunApiObjectResponse<QueryNetworkCardsResponse>) response;
            List<QueryNetworkCardsResponse.InterfaceAttachments> responses = ctyunResponse.getReturnObj().getInterfaceAttachments();
            responses.forEach(e -> {
                List<String> subnetId = new ArrayList<>();
                List<String> ipAddress = new ArrayList<>();
                e.getFixed_ips().forEach(n -> {
                    if (n.getSubnet_id() != null) {
                        subnetId.add(n.getSubnet_id());
                    }
                    if (n.getIp_address() != null) {
                        ipAddress.add(n.getIp_address());
                    }
                });
                String subnetIdsToStr = StringUtils.join(subnetId, ",");
                String ipAddressToStr = StringUtils.join(ipAddress, ",");
                e.setSubnet_ids(subnetIdsToStr);
                e.setIp_addresses(ipAddressToStr);
                e.setVmId(vmId);
            });

            return responses;
        }
        throw new ClientException("天翼云查询NetWorkInterFace出错");
    }


    public QueryNetworkCardsResponse.InterfaceAttachments createNetworkCard(CreateNetworkCardsParam param) throws ServerException, ClientException {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        AddNetworkCardsRequest request = new AddNetworkCardsRequest();
        request.setRegionId(param.getRegionId());
        request.setVmId(param.getInstanceId());
        List<AddNetworkCardsRequest.SecurityGroups> securityGroups = new ArrayList<>();
        AddNetworkCardsRequest.SecurityGroups securityGroups1 = new AddNetworkCardsRequest.SecurityGroups();
        securityGroups1.setId(param.getSecurityGroups());
        securityGroups.add(securityGroups1);
        List<AddNetworkCardsRequest.NetworkCardsInfo> networkCardsInfos = new ArrayList<>();
        AddNetworkCardsRequest.NetworkCardsInfo networkCardsInfos1 = new AddNetworkCardsRequest.NetworkCardsInfo();
        networkCardsInfos1.setSubnetId(param.getVswitchId());
        networkCardsInfos1.setSecurityGroups(securityGroups);
        networkCardsInfos.add(networkCardsInfos1);
        request.setNetworkCards(networkCardsInfos);
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        if (ctyunResponse.getStatusCode() == 800){
            CtyunApiObjectResponse<GetJobIdResponse> jobResponse = (CtyunApiObjectResponse<GetJobIdResponse>) ctyunResponse;
            CtyunApiObjectResponse<QueryJobStatusResponse> response = queryJobStatus(param.getRegionId(), jobResponse.getReturnObj().getData());
            log.info("轮询结果："+ JSON.toJSONString(response));
            String netId = response.getReturnObj().getEntities().getSub_jobs().get(0).getEntities().getNic_id();
            if (StringUtils.isNoneBlank(netId)){
                List<QueryNetworkCardsResponse.InterfaceAttachments> interfaceAttachments = getNetworkInterface(param.getRegionId(), param.getInstanceId());
                for (QueryNetworkCardsResponse.InterfaceAttachments interfaceAttachment : interfaceAttachments) {
                    if (netId.equals(interfaceAttachment.getPort_id())){
                        return interfaceAttachment;
                    }
                }
            }
        }
        log.error("天翼云创建NetWorkInterFace出错{}", JSON.toJSONString(ctyunResponse));
        throw new ClientException("天翼云创建NetWorkInterFace出错");
    }

    public CtyunApiObjectResponse<QueryJobStatusResponse> queryJobStatus(String regionId, String jobId) throws ServerException, ClientException {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        long now = System.currentTimeMillis();
        long waitInterval = 3000;
        long maxWait = 120000;
        do {
            QueryJobStatusRequest request = new QueryJobStatusRequest();
            request.setJobId(jobId);
            request.setRegionId(regionId);
            CtyunApiResponse response = client.getCtyunResponse(request);
            Map result = JSON.parseObject(response.getResultJSON(), HashMap.class);
            if (response.getStatusCode() == 800){
                CtyunApiObjectResponse<QueryJobStatusResponse> jobResponse = (CtyunApiObjectResponse)response;
                if ("SUCCESS".equals(jobResponse.getReturnObj().getStatus())) {
                    return jobResponse;
                }else if ("FAILED".equals(jobResponse.getReturnObj().getStatus())){
                    log.warn("天翼云资源{},Job执行失败,errorCode:{} 错误原因:{}", jobResponse.getReturnObj().getEntities(), jobResponse.getReturnObj().getError_code(), jobResponse.getReturnObj().getFail_reason());
                    throw new ClientException("天翼云资源Job执行失败 "+ jobResponse.getReturnObj().getFail_reason());
                }
            } else if (response.getStatusCode() == 900 && result.containsKey("message") && StringUtils.isBlank((String)result.get("message"))){
                log.info("天翼云后付费资源JOB:{}未查询到结果,等待{}毫秒后重新执行", jobId, waitInterval);
                try {
                    Thread.sleep(waitInterval);
                } catch (InterruptedException e) {
                    log.error("重试Job：{}等待出错", jobId);
                }
            }else {
                log.warn("天翼云资源,Job:{}执行失败,message:{}", jobId, result.get("message"));
                throw new ClientException("天翼云资源Job执行失败 "+ result);

            }
        } while (System.currentTimeMillis() < now + maxWait);
        throw new ClientException("天翼云JOB接口轮询超时,jobId:"+jobId+" regionId:"+regionId);
    }

}
