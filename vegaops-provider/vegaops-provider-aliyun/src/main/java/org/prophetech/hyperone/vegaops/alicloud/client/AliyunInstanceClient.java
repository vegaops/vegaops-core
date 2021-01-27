package org.prophetech.hyperone.vegaops.alicloud.client;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.ecs.model.v20140526.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.prophetech.hyperone.vegaops.alicloud.model.DeleteAliyunInstancesRequest;
import org.prophetech.hyperone.vegaops.alicloud.model.QueryNetworkInterfaceRequest;
import org.prophetech.hyperone.vegaops.alicloud.model.UpdateInstanceRequest;

import java.util.ArrayList;
import java.util.List;


@Slf4j(topic = "vegaops")
public class AliyunInstanceClient {


    public void updateInstance(UpdateInstanceRequest updateInstanceRequest) {
        DefaultProfile profile = DefaultProfile.getProfile(updateInstanceRequest.getRegionId(), updateInstanceRequest.getAccessKey(), updateInstanceRequest.getSecret());
        IAcsClient clients = new DefaultAcsClient(profile);
        if ("PostPaid".equalsIgnoreCase(updateInstanceRequest.getPayType())) {
            ModifyInstanceSpecRequest request = new ModifyInstanceSpecRequest();
            request.setInstanceId(updateInstanceRequest.getInstanceId());
            request.setInstanceType(updateInstanceRequest.getInstanceType());
            if(updateInstanceRequest.getInternetMaxBandwidthIn()!= null){
                request.setInternetMaxBandwidthIn(updateInstanceRequest.getInternetMaxBandwidthIn());
            }
            if(updateInstanceRequest.getInternetMaxBandwidthOut()!= null){
                request.setInternetMaxBandwidthOut(updateInstanceRequest.getInternetMaxBandwidthOut());
            }
            try {
                ModifyInstanceSpecResponse response = clients.getAcsResponse(request);
            } catch (ServerException e) {
                throw new RuntimeException("ErrMsg:" + e);
            } catch (ClientException e) {
                throw new RuntimeException("ErrMsg:" + e);
            }
        } else if("PrePaid".equalsIgnoreCase(updateInstanceRequest.getPayType())) {
            ModifyPrepayInstanceSpecRequest request = new ModifyPrepayInstanceSpecRequest();
            request.setInstanceId(updateInstanceRequest.getInstanceId());
            request.setInstanceType(updateInstanceRequest.getInstanceType());
            if(StringUtils.isNotEmpty(updateInstanceRequest.getOperatorType())){
                request.setOperatorType(updateInstanceRequest.getOperatorType());
            }
            request.setAutoPay(updateInstanceRequest.getAutoPay());
            try {
                ModifyPrepayInstanceSpecResponse response = clients.getAcsResponse(request);
            } catch (ServerException e) {
                throw new RuntimeException("ErrMsg:" + e);
            } catch (ClientException e) {
                throw new RuntimeException("ErrMsg:" + e.getErrMsg());
            }
        }

    }

    public void deleteInstance(DeleteAliyunInstancesRequest deleteAliyunInstancesRequest) {
        DefaultProfile profile = DefaultProfile.getProfile(deleteAliyunInstancesRequest.getRegionId(), deleteAliyunInstancesRequest.getAccessKey(), deleteAliyunInstancesRequest.getSecret());
        IAcsClient clients = new DefaultAcsClient(profile);
        DeleteInstancesRequest request = new DeleteInstancesRequest();

        List<String> instanceIdList = new ArrayList<String>();
        instanceIdList.add(deleteAliyunInstancesRequest.getInstanceId());
        request.setInstanceIds(instanceIdList);

        try {
            DeleteInstancesResponse response = clients.getAcsResponse(request);
        } catch (ServerException e) {
            throw new RuntimeException("ErrMsg:" + e);
        } catch (ClientException e) {
            throw new RuntimeException("ErrMsg:" + e.getErrMsg());
        }
        }

    public DescribeNetworkInterfacesResponse queryNetworkInterface(QueryNetworkInterfaceRequest queryNetworkInterfaceRequest){
        DefaultProfile profile = DefaultProfile.getProfile(queryNetworkInterfaceRequest.getRegionId(), queryNetworkInterfaceRequest.getAccessKey(), queryNetworkInterfaceRequest.getSecret());
        IAcsClient clients = new DefaultAcsClient(profile);
        DescribeNetworkInterfacesRequest request = new DescribeNetworkInterfacesRequest();
        List<String> networkInterfaceIdList = new ArrayList<String>();
        networkInterfaceIdList.add(queryNetworkInterfaceRequest.getNetworkInterfaceId());
        request.setNetworkInterfaceIds(networkInterfaceIdList);
        try {
            DescribeNetworkInterfacesResponse response = clients.getAcsResponse(request);
            return response;
        } catch (ServerException e) {
            throw new RuntimeException("ErrMsg:" + e);
        } catch (ClientException e) {
            throw new RuntimeException("ErrMsg:" + e.getErrMsg());
        }
    }

}
