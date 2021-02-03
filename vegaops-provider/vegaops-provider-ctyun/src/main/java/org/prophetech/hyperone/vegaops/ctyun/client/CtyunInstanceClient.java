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
import org.prophetech.hyperone.vegaops.engine.bean.FastBeanCopier;

import java.util.*;
import java.util.stream.Collectors;

@Setter
@Getter
@Slf4j(topic = "vegaops")
public class CtyunInstanceClient {

    private String PAYASYOUGO = "PostPaid";

    private String SUBSCRIPTION = "PrePaid";

    private String ORDERCOMPLETE = "3";

    private CtyunAccount ctyunAccount;

    //查询预付费主机的主资源Id
    public String getInstancesMasterOrderId(String instanceId,String regionId) throws ServerException, ClientException {
        GetVMsRequest getVMsRequest = new GetVMsRequest();
        getVMsRequest.setVmId(instanceId);
        getVMsRequest.setRegionId(regionId);
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        CtyunApiResponse response = client.getCtyunResponse(getVMsRequest);
        if (response.getStatusCode() == 800) {
            Map map = JSON.parseObject(response.getResultJSON());
            Map returnObj = (Map) map.get("returnObj");
            List<Map> servers = (List<Map>) returnObj.get("servers");
            for (Map server : servers) {
                if (server.containsKey("workOrderResourceId")) {
                    return (String) server.get("workOrderResourceId");
                }
            }
        } else {
            throw new ClientException("天翼云查询预付费主机的主资源Id出错");
        }
        throw new ClientException("天翼云未查询到预付费主机的主资源Id");
    }

    //查询资源详细信息的resourceId
    public String queryResourceInfoByMasterOrderId(String masterOrderId) throws ServerException, ClientException {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        QueryResourceInfoByMasterOrderIdRequest request = new QueryResourceInfoByMasterOrderIdRequest();
        request.setMasterOrderId(masterOrderId);
        CtyunApiResponse response = client.getCtyunResponse(request);
        if (response.getStatusCode() == 800) {
            CtyunApiListResponse<QueryResourceInfoByMasterOrderIdResponse> orderResponse = (CtyunApiListResponse) response;
            QueryResourceInfoByMasterOrderIdResponse queryResourceInfoByMasterOrderIdResponse = orderResponse.getReturnObj().get(0);
            String resourceId = queryResourceInfoByMasterOrderIdResponse.getResourceId();
            return resourceId;
        } else {
            log.error("天翼云查询订单失败:" + JSON.toJSONString(response));
            throw new ClientException("天翼云查询订单失败");
        }
    }


    public CreateInstanceRequest reNewInstance(RenewOrderRequest renewOrderRequest) throws ServerException, ClientException {
        CreateInstanceRequest instanceRequest = new CreateInstanceRequest();
        String masterOrderId = getInstancesMasterOrderId(renewOrderRequest.getInstanceId(),renewOrderRequest.getRegionId());
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        PlaceRenewOrderRequest request = new PlaceRenewOrderRequest();
        ResourceDetailJson resourceDetailJson = new ResourceDetailJson();
        resourceDetailJson.setCycleCount(renewOrderRequest.getCycleCount());
        resourceDetailJson.setCycleType(renewOrderRequest.getCycleType());
        List<String> resourceIds = new ArrayList<>();
        resourceIds.add(masterOrderId);
        resourceDetailJson.setResourceIds(resourceIds);
        request.setResourceDetailJson(JSON.toJSONString(resourceDetailJson));
        CtyunApiResponse response = client.getCtyunResponse(request);
        instanceRequest.setStatusCode(response.getStatusCode());
        if (response.getStatusCode() == 800) {
            CtyunApiObjectResponse<PlaceRenewOrderResponse> orderResponse = (CtyunApiObjectResponse) response;
            // 支付订单
            PayGeneralUserOrderRequest payRequest = new PayGeneralUserOrderRequest();
            payRequest.setCash(orderResponse.getReturnObj().getOrderPlacedEvents().get(0).getTotalPrice());
            payRequest.setMasterOrderId(orderResponse.getReturnObj().getOrderPlacedEvents().get(0).getNewOrderId());
            CtyunApiResponse payResponse = client.getCtyunResponse(payRequest);
            if (payResponse.getStatusCode() == 800) {
                // 查询订单状态->然后查询详情
//                CreateInstanceRequest instance = queryOrderStatus(orderResponse.getReturnObj().getOrderPlacedEvents().get(0).getNewOrderId());
                CreateInstanceRequest instance = queryRenewOrderStatus(orderResponse.getReturnObj().getOrderPlacedEvents().get(0).getNewOrderId());
                instance.setId(renewOrderRequest.getInstanceId());
                FastBeanCopier.copy(instance, instanceRequest);
                log.info("续费天翼预付费云主机完成," + instance.getId());
                return instanceRequest;
            }
            log.error("支付天翼云预付费主机订单失败" + JSON.toJSONString(payResponse));
            throw new ClientException("支付天翼云预付费主机失败");
        }
        log.error("创建天翼云预付费主机订单失败" + JSON.toJSONString(response));
        throw new ClientException("续费天翼云预付费主机失败");
    }


    public CreateInstanceRequest createInstance(CreateInstanceRequest instanceRequest) throws ServerException, ClientException {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        if (StringUtils.isBlank(instanceRequest.getPayType())) {
            throw new ClientException("创建天翼云主机付费类型不可为空");
        }
        if (instanceRequest.getCount() == 0) {
            instanceRequest.setCount(1);
        }
        if (StringUtils.isBlank(instanceRequest.getIptype())) {
            instanceRequest.setIptype("5_telcom");
        }
        if (PAYASYOUGO.equals(instanceRequest.getPayType())) {
            CreateVMRequest request = new CreateVMRequest();
            request.setName(instanceRequest.getName());
            request.setAvailability_zone(instanceRequest.getAvailability_zone());
            request.setImageRef(instanceRequest.getImageRef());
            request.setOsType(instanceRequest.getOsType());
            CreateVMRequest.Volume volume = new CreateVMRequest.Volume();
            volume.setSize(instanceRequest.getVolumeSize());
            volume.setVolumetype(instanceRequest.getVolumetype());
            request.setRoot_volume(volume);
            if(StringUtils.isNotEmpty(instanceRequest.getDataVolumetype())&&instanceRequest.getDataVolumeSize()!=null){
                List<CreateVMRequest.Volume> dataVolumes = new ArrayList<>();
                CreateVMRequest.Volume dataVolume = new CreateVMRequest.Volume();
                dataVolume.setSize(instanceRequest.getDataVolumeSize());
                dataVolume.setVolumetype(instanceRequest.getDataVolumetype());
                dataVolumes.add(dataVolume);
                request.setData_volumes(dataVolumes);
            }
            request.setFlavorRef(instanceRequest.getFlavorRef());
            request.setVpcid(instanceRequest.getVpcid());
            CreateVMRequest.SecurityGroup securityGroup = new CreateVMRequest.SecurityGroup();
            securityGroup.setId(instanceRequest.getSecurityGroupId());
            request.setSecurity_groups(Arrays.asList(securityGroup));
            CreateVMRequest.Nic nic = new CreateVMRequest.Nic();
            nic.setSubnet_id(instanceRequest.getSubnet_id());
            request.setNics(Arrays.asList(nic));
            CreateVMRequest.PublicIp publicIp = new CreateVMRequest.PublicIp();
            if (StringUtils.isNotEmpty(instanceRequest.getPublicipId())) {
                publicIp.setId(instanceRequest.getPublicipId());
            } else if (instanceRequest.getBandwidthSize() != null) {
                CreateVMRequest.Eip eip = new CreateVMRequest.Eip();
                eip.setIptype(instanceRequest.getIptype());
                CreateVMRequest.BandWidth bandWidth = new CreateVMRequest.BandWidth();
                bandWidth.setSize(instanceRequest.getBandwidthSize());
                bandWidth.setSharetype(instanceRequest.getSharetype());
                eip.setBandwidth(bandWidth);
                publicIp.setEip(eip);
            }
            request.setPublicip(publicIp);
            if (StringUtils.isNotBlank(instanceRequest.getKeyPairId())) {
                request.setKey_name(instanceRequest.getKeyPairId());
            } else {
                request.setAdminPass(instanceRequest.getAdminPass());
            }
            request.setCount(instanceRequest.getCount());
            request.setRegionID(instanceRequest.getRegionId());
            CtyunApiResponse response = client.getCtyunResponse(request);
            instanceRequest.setStatusCode(response.getStatusCode());
            if (response.getStatusCode() == 800) {
                CtyunApiObjectResponse<GetJobIdResponse> ctyunResponse = (CtyunApiObjectResponse) response;
                CtyunApiObjectResponse<QueryJobStatusResponse> jobResponse = queryJobStatus(instanceRequest.getRegionId(), ctyunResponse.getReturnObj().getData());
                String id = jobResponse.getReturnObj().getEntities().getSub_jobs().get(0).getEntities().getServer_id();
                instanceRequest.setId(id);
                if (StringUtils.isNotBlank(instanceRequest.getSecurityGroupId())) {
                    instanceRequest.setSecurityGroupId(JSON.toJSONString(Arrays.asList(instanceRequest.getSecurityGroupId())));
                }
                CtyunApiObjectResponse<QueryVMDetailResponse> vmDetail = queryVMDetail(instanceRequest.getRegionId(), id);
                instanceRequest.setStatus(vmDetail.getReturnObj().getStatus());
                instanceRequest.setCreateDate(vmDetail.getReturnObj().getCreated());
                if (CollectionUtils.isNotEmpty(vmDetail.getReturnObj().getPrivateIps())) {
                    List<String> priIPs = vmDetail.getReturnObj().getPrivateIps().stream().map(o -> {
                        return o.getAddress();
                    }).collect(Collectors.toList());
                    instanceRequest.setPrivateIp(JSON.toJSONString(priIPs));
                }
                if (CollectionUtils.isNotEmpty(vmDetail.getReturnObj().getPublicIps())) {
                    List<String> pubIPs = vmDetail.getReturnObj().getPublicIps().stream().map(o -> {
                        return o.getAddress();
                    }).collect(Collectors.toList());
                    instanceRequest.setPublicipId(JSON.toJSONString(pubIPs));
                }
                //增加子网
                instanceRequest.setSubnet_id(instanceRequest.getSubnet_id());
                instanceRequest.setCpuNum(vmDetail.getReturnObj().getFlavorObj().getCpuNum());
                instanceRequest.setMemSize(vmDetail.getReturnObj().getFlavorObj().getMemSize());
                log.info("创建天翼云主机完成," + instanceRequest.getId());
                return instanceRequest;
            }
            log.error("创建天翼云后付费主机失败" + JSON.toJSONString(response));
            throw new ClientException("创建天翼云后付费主机失败");
        } else {
            PlaceNewPurchaseOrderRequest request = new PlaceNewPurchaseOrderRequest();
            OrderDetail orderDetail = new OrderDetail();
            OrderDetail.Order order = new OrderDetail.Order();
            order.setInstanceCnt(instanceRequest.getInstanceCnt());
            order.setCycleCnt(instanceRequest.getCycleCnt());
            order.setCycleType(instanceRequest.getCycleType());
            // 云主机配置
            OrderDetail.Item vmcItem = new OrderDetail.Item();
            vmcItem.setMaster("true");
            vmcItem.setResourceType("VMC");
            if (StringUtils.isBlank(instanceRequest.getInstanceServiceTag())) {
                vmcItem.setServiceTag("HWS");
            } else {
                vmcItem.setServiceTag(instanceRequest.getInstanceServiceTag());
            }
            vmcItem.setItemValue(1);
            OrderDetail.Vmc vmc = new OrderDetail.Vmc();
            vmc.setMemSize(instanceRequest.getMemSize());
            vmc.setCpuNum(instanceRequest.getCpuNum());
            vmc.setFlavorType(instanceRequest.getFlavorType());
            vmc.setOsType(instanceRequest.getOsType());
            vmc.setAvailability_zone(instanceRequest.getAvailability_zone());
            vmc.setName(instanceRequest.getName());
            vmc.setImageRef(instanceRequest.getImageRef());
            vmc.setVpcid(instanceRequest.getVpcid());
            CreateVMRequest.SecurityGroup securityGroup = new CreateVMRequest.SecurityGroup();
            securityGroup.setId(instanceRequest.getSecurityGroupId());
            vmc.setSecurity_groups(Arrays.asList(securityGroup));
            CreateVMRequest.Nic nic = new CreateVMRequest.Nic();
            nic.setSubnet_id(instanceRequest.getSubnet_id());
//            nic.setIp_address(instanceRequest.getIp_address());
            vmc.setNics(Arrays.asList(nic));
            vmc.setRegionId(instanceRequest.getRegionId());
            vmc.setSupport_auto_recovery(instanceRequest.getSupport_auto_recovery());
            if (StringUtils.isNotBlank(instanceRequest.getKeyPairId())) {
                vmc.setKey_name(instanceRequest.getKeyPairId());
            } else {
                vmc.setAdminPass(instanceRequest.getAdminPass());
            }
            vmcItem.setItemConfig(vmc);

            OrderDetail.Item sysVolumeItem = new OrderDetail.Item();
            sysVolumeItem.setMaster("false");
            sysVolumeItem.setResourceType("EBSC");
            if (StringUtils.isBlank(instanceRequest.getVolumeServiceTag())) {
                sysVolumeItem.setServiceTag("HWS");
            } else {
                sysVolumeItem.setServiceTag(instanceRequest.getVolumeServiceTag());
            }
            sysVolumeItem.setItemValue(instanceRequest.getVolumeSize());
            sysVolumeItem.setIsSystemVolume(true);
            OrderDetail.Ebsc sysVolume = new OrderDetail.Ebsc();
            sysVolume.setVolumeType(instanceRequest.getVolumetype());
            sysVolumeItem.setItemConfig(sysVolume);

            if (instanceRequest.getBandwidthSize() != null && StringUtils.isNotBlank(instanceRequest.getSharetype())) {
                OrderDetail.Item bandwidthItem = new OrderDetail.Item();
                bandwidthItem.setMaster("false");
                bandwidthItem.setResourceType("NETWORKC");
                bandwidthItem.setServiceTag("HWS");
                OrderDetail.Networkc networkc = new OrderDetail.Networkc();
                networkc.setType("standalone");
                networkc.setName(instanceRequest.getName() + "networkc");
                networkc.setRegionId(instanceRequest.getRegionId());
                bandwidthItem.setItemConfig(networkc);
                bandwidthItem.setItemValue(instanceRequest.getBandwidthSize());
                order.setItems(Arrays.asList(vmcItem, sysVolumeItem, bandwidthItem));
            } else {
                order.setItems(Arrays.asList(vmcItem, sysVolumeItem));

            }
            orderDetail.setOrders(Arrays.asList(order));
            CustomInfo customInfo = new CustomInfo();
            customInfo.setType(instanceRequest.getCustomerInfoType());
            customInfo.setName("ShangHai-Telecom");
            customInfo.setPhone("123456789");
            customInfo.setEmail(instanceRequest.getCustomerEmail());
            CustomInfo.Identity identity = new CustomInfo.Identity();
            identity.setAccountId(instanceRequest.getCustomerAccountId());
            identity.setCrmBizId(instanceRequest.getCustomerCrmBizId());
            customInfo.setIdentity(identity);
            orderDetail.setCustomInfo(customInfo);
            request.setCustomInfo(customInfo);
            request.setOrderDetailJson(orderDetail.toJson());
            CtyunApiResponse response = client.getCtyunResponse(request);
            instanceRequest.setStatusCode(response.getStatusCode());
            if (response.getStatusCode() == 800) {
                CtyunApiObjectResponse<PlaceNewPurchaseOrderResponse> orderResponse = (CtyunApiObjectResponse) response;
                // 支付订单
                PayGeneralUserOrderRequest payRequest = new PayGeneralUserOrderRequest();
                payRequest.setCash(orderResponse.getReturnObj().getTotalPrice());
                payRequest.setMasterOrderId(orderResponse.getReturnObj().getNewOrderId());
                CtyunApiResponse payResponse = client.getCtyunResponse(payRequest);
                instanceRequest.setStatusCode(response.getStatusCode());
                if (payResponse.getStatusCode() == 800) {
                    // 查询订单状态->然后查询详情
                    CreateInstanceRequest instance = queryOrderStatus(orderResponse.getReturnObj().getNewOrderId());
                    FastBeanCopier.copy(instance, instanceRequest);
                    log.info("创建天翼云主机完成," + instanceRequest.getId());
                    return instanceRequest;
                }
                log.error("支付天翼云预付费主机订单失败" + JSON.toJSONString(payResponse));
                throw new ClientException("支付天翼云预付费主机失败");
            }
            log.error("创建天翼云预付费主机订单失败" + JSON.toJSONString(response));
            throw new ClientException("创建天翼云预付费主机失败");
        }
    }

    public CtyunApiObjectResponse<QueryJobStatusResponse> queryJobStatus(String regionId, String jobId) throws ServerException, ClientException {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        long now = System.currentTimeMillis();
        long waitInterval = 3000;
        long maxWait = 240000;
        do {
            QueryJobStatusRequest request = new QueryJobStatusRequest();
            request.setJobId(jobId);
            request.setRegionId(regionId);
            CtyunApiResponse response = client.getCtyunResponse(request);
            Map result = JSON.parseObject(response.getResultJSON(), HashMap.class);
            if (response.getStatusCode() == 800) {
                CtyunApiObjectResponse<QueryJobStatusResponse> jobResponse = (CtyunApiObjectResponse) response;
                if ("SUCCESS".equals(jobResponse.getReturnObj().getStatus())) {
                    return jobResponse;
                } else if ("FAILED".equals(jobResponse.getReturnObj().getStatus())) {
                    log.warn("天翼云资源{},Job执行失败,errorCode:{} 错误原因:{}", jobResponse.getReturnObj().getEntities(), jobResponse.getReturnObj().getError_code(), jobResponse.getReturnObj().getFail_reason());
                    throw new ClientException("天翼云资源Job执行失败 " + jobResponse.getReturnObj().getFail_reason());
                }
            } else if (response.getStatusCode() == 900 && result.containsKey("message") && StringUtils.isBlank((String) result.get("message"))) {
                log.info("天翼云资源JOB:{}未查询到结果,等待{}毫秒后重新执行", jobId, waitInterval);
                try {
                    Thread.sleep(waitInterval);
                } catch (InterruptedException e) {
                    log.error("重试Job：{}等待出错", jobId);
                }
            } else {
                log.warn("天翼云资源,Job:{}执行失败,message:{}", jobId, result.get("message"));
                throw new ClientException("天翼云资源Job执行失败 " + result);

            }
        } while (System.currentTimeMillis() < now + maxWait);
        throw new ClientException("天翼云JOB接口轮询超时,jobId:" + jobId + " regionId:" + regionId);
    }

    public CreateInstanceRequest queryOrderStatus(String orderId) throws ServerException, ClientException {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        QueryMasterOrderRequest request = new QueryMasterOrderRequest();
        long now = System.currentTimeMillis();
        long waitInterval = 3000;
        long maxWait = 240000;
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
                    log.info("天翼云云主机订单{}未完成,等待{}毫秒后重新执行", orderId, waitInterval);
                    Thread.sleep(waitInterval);
                } catch (InterruptedException e) {
                    log.error("天翼云主机订单{}查询，等待时出错", orderId);
                }
            } else {
                log.error("天翼云查询订单失败:" + JSON.toJSONString(response));
                throw new ClientException("天翼云查询订单失败");
            }
        } while (System.currentTimeMillis() < now + maxWait);
        throw new ClientException("天翼云订单" + orderId + "长时间未完成,已终止");
    }

    public CreateInstanceRequest queryRenewOrderStatus(String orderId) throws ServerException, ClientException {
        CreateInstanceRequest instanceRequest = new CreateInstanceRequest();
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        QueryMasterOrderRequest request = new QueryMasterOrderRequest();
        long now = System.currentTimeMillis();
        long waitInterval = 3000;
        long maxWait = 240000;
        do {
            request.setMasterOrderId(orderId);
            CtyunApiResponse response = client.getCtyunResponse(request);
            if (response.getStatusCode() == 800) {
                CtyunApiObjectResponse<QueryVMsByOrderIdResponse> orderResponse = (CtyunApiObjectResponse) response;
                String status = orderResponse.getReturnObj().getStatus();
                if (ORDERCOMPLETE.equals(status)) {
                    return instanceRequest;
                }
                try {
                    log.info("天翼云云主机订单{}未完成,等待{}毫秒后重新执行", orderId, waitInterval);
                    Thread.sleep(waitInterval);
                } catch (InterruptedException e) {
                    log.error("天翼云主机订单{}查询，等待时出错", orderId);
                }
            } else {
                log.error("天翼云查询订单失败:" + JSON.toJSONString(response));
                throw new ClientException("天翼云查询订单失败");
            }
        } while (System.currentTimeMillis() < now + maxWait);
        throw new ClientException("天翼云订单" + orderId + "长时间未完成,已终止");
    }

    public CreateInstanceRequest queryOrderDetail(String orderId) throws ServerException, ClientException {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        QueryVMsByOrderIdRequest request = new QueryVMsByOrderIdRequest();
        request.setOrderId(orderId);
        CtyunApiResponse response = client.getCtyunResponse(request);
        if (response.getStatusCode() == 800) {
            CtyunApiListResponse<QueryVMsByOrderIdResponse> orderResponse = (CtyunApiListResponse) response;
            CreateInstanceRequest instance = new CreateInstanceRequest();
            QueryVMsByOrderIdResponse order = orderResponse.getReturnObj().get(0);
            instance.setId(order.getResVmId());
            instance.setStatus(order.getStatus());
            instance.setName(order.getVmName());
            instance.setOsType(order.getOsStyle());
            instance.setRegionId(order.getRegionId());
            instance.setSubnet_id(order.getVlanId());
            instance.setCreateDate(order.getCreateDate());
            instance.setExpireTime(order.getDueDate());
            instance.setAvailability_zone(order.getZoneId());
            instance.setCpuNum(order.getCpuNum());
            instance.setMemSize(order.getMemSize());
            return instance;
        } else {
            log.error("天翼云查询订单详情失败:" + JSON.toJSONString(response));
            throw new ClientException("天翼云查询订单详情失败");
        }
    }

    public List<GetVMsResponse.Instance> getInstances(GetVMsRequest request) throws ServerException, ClientException {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        CtyunApiResponse response = client.getCtyunResponse(request);
        if (response.getStatusCode() == 800) {
            Map map = JSON.parseObject(response.getResultJSON());
            Map returnObj = (Map) map.get("returnObj");
            List<Map> servers = (List<Map>) returnObj.get("servers");
            List<GetVMsResponse.Instance> instanceList = new ArrayList<>();
            for (Map server : servers) {
                GetVMsResponse.Instance instance = new GetVMsResponse.Instance();
                FastBeanCopier.copy(server, instance);
                Map<String, String> image = (Map) server.get("image");
                instance.setImage(image.get("id"));
                Map<String, String> flavor = (Map) server.get("flavor");
                instance.setFlavor(flavor.get("id"));
                Map<String, List<Map<String, String>>> addresses = (Map) server.get("addresses");
                for (String key : addresses.keySet()) {
                    List<String> privateIp = new ArrayList<>();
                    List<String> floatingIp = new ArrayList<>();
                    instance.setVpcId(key);
                    addresses.get(key).forEach(ip -> {
                        if ("fixed".equals(ip.get("OS-EXT-IPS:type"))) {
                            privateIp.add(ip.get("addr"));
                        }
                        if ("floating".equals(ip.get("OS-EXT-IPS:type"))) {
                            floatingIp.add(ip.get("addr"));
                        }
                    });
                    instance.setFloatingIp(JSON.toJSONString(floatingIp));
                    instance.setPrivateIp(JSON.toJSONString(privateIp));
                    break;
                }
                if (server.containsKey("masterOrderId")) {
                    instance.setPayType(SUBSCRIPTION);
                } else {
                    instance.setPayType(PAYASYOUGO);
                }
                List<Map<String, String>> securityGroup = (List) server.get("security_groups");
                List<String> securityGroups = new ArrayList<>();
                securityGroup.forEach(o -> {
                    securityGroups.add(o.get("name") + "," + server.get("tenant_id"));
                });
                instance.setSecurity_groups(JSON.toJSONString(securityGroups));
                instance.setAvailability_zone((String) server.get("OS-EXT-AZ:availability_zone"));
                instance.setVolumes_attached(JSON.toJSONString(server.get("os-extended-volumes:volumes_attached")));
                instanceList.add(instance);
            }
            return instanceList;
        } else {
            throw new ClientException("天翼云查询queryVMs出错");
        }
    }

    public CtyunApiObjectResponse<QueryJobStatusResponse> deleteInstance(DeleteVMRequest request) throws ServerException, ClientException {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        CtyunApiResponse response = client.getCtyunResponse(request);
        if (response.getStatusCode() == 800) {
            CtyunApiObjectResponse<GetJobIdResponse> jobResponse = (CtyunApiObjectResponse) response;
            CtyunApiObjectResponse<QueryJobStatusResponse> result = queryJobStatus(request.getRegionId(), jobResponse.getReturnObj().getData());
            log.info("删除天翼云后付费云主机{}成功", request.getVmId());
            return result;
        }
        log.warn("删除天翼云后付费云主机{}失败,{}", request.getVmId(), response.getResultJSON());
        throw new ClientException("删除天翼云后付费云主机" + request.getVmId() + "失败");
    }

    public CtyunApiObjectResponse<QueryVMDetailResponse> queryVMDetail(String regionId, String vmId) throws ServerException, ClientException {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        QueryVMDetailRequest request = new QueryVMDetailRequest();
        request.setRegionId(regionId);
        request.setVmId(vmId);
        CtyunApiObjectResponse<QueryVMDetailResponse> ctyunResponse = (CtyunApiObjectResponse) client.getCtyunResponse(request);
        return ctyunResponse;
    }

    public CtyunApiResponse stop(String instanceChargeType, String instanceId, String type, String regionId) {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        CtyunApiResponse response;
        if ("PostPaid".equals(instanceChargeType)) {
            OndemandStopVMRequest request = new OndemandStopVMRequest();
            request.setRegionId(regionId);
            request.setVmId(instanceId);
            request.setType(type);
            try {
                response = client.getCtyunResponse(request);
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new RuntimeException("resource.instance.shutdown.error");
            }
        } else {
            StopVMRequest request = new StopVMRequest();
            request.setRegionId(regionId);
            request.setVmId(instanceId);
            request.setType(type);
            try {
                response = client.getCtyunResponse(request);
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new RuntimeException("resource.instance.shutdown.error");
            }
        }
        return response;
    }

    public CtyunApiResponse start(String instanceChargeType, String instanceId, String regionId) {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        CtyunApiResponse response;
        if ("PostPaid".equals(instanceChargeType)) {
            OndemandStartVMRequest request = new OndemandStartVMRequest();
            request.setRegionId(regionId);
            request.setVmId(instanceId);
            try {
                response = client.getCtyunResponse(request);
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new RuntimeException("resource.instance.boot.error");
            }
        } else {
            StartVMRequest request = new StartVMRequest();
            request.setRegionId(regionId);
            request.setVmId(instanceId);
            try {
                response = client.getCtyunResponse(request);
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new RuntimeException("resource.instance.boot.error");
            }
        }
        return response;
    }


    public CtyunApiObjectResponse<QueryJobStatusResponse> upgradeVm(UpgradeVMRequest request) throws ServerException, ClientException {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        CtyunApiResponse response = client.getCtyunResponse(request);
        if (response.getStatusCode() == 800) {
            CtyunApiObjectResponse<GetJobIdResponse> jobResponse = (CtyunApiObjectResponse) response;
            CtyunApiObjectResponse<QueryJobStatusResponse> result = queryJobStatus(request.getRegionId(), response.getResultJSON().substring(response.getResultJSON().indexOf("data")+7,response.getResultJSON().indexOf("message")-3));
            log.info("升级后付费云主机{}规格成功", request.getVmId());
            return result;
        }
        log.warn("升级天翼云后付费云主机{}失败,{}", request.getVmId(), response.getResultJSON());
        throw new ClientException("升级天翼云后付费云主机" + request.getVmId() + "失败");
    }



}
