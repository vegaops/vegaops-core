package org.prophetech.hyperone.vegaops.alicloud.client;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.rds.model.v20140815.DescribeDBInstanceAttributeRequest;
import com.aliyuncs.rds.model.v20140815.DescribeDBInstanceAttributeResponse;
import com.aliyuncs.rds.model.v20140815.DescribeDBInstancesRequest;
import com.aliyuncs.rds.model.v20140815.DescribeDBInstancesResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.prophetech.hyperone.vegaops.alicloud.model.CreateRdsInstanceRequest;
import org.prophetech.hyperone.vegaops.alicloud.model.RdsInstanceResponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j(topic = "vegaops")
public class AliyunRdsClient {

    public List<RdsInstanceResponse> getRdsInstanceList(CreateRdsInstanceRequest createRdsInstanceRequest) {
        DefaultProfile profile = DefaultProfile.getProfile(createRdsInstanceRequest.getRegionId(), createRdsInstanceRequest.getAccessKey(), createRdsInstanceRequest.getSecret());
        IAcsClient clients = new DefaultAcsClient(profile);
        List<RdsInstanceResponse> responseList = new ArrayList<>();
        Set<String> rdsIds = new HashSet<>();
        //查询rds列表
        DescribeDBInstancesRequest request = new DescribeDBInstancesRequest();
        request.setPageSize(100);
        request.setPageNumber(1);
        if(StringUtils.isNotEmpty(createRdsInstanceRequest.getDbInstanceId())){
            request.setDBInstanceId(createRdsInstanceRequest.getDbInstanceId());
        }
        try {
            DescribeDBInstancesResponse response = clients.getAcsResponse(request);
            response.getItems().forEach(e->{
                RdsInstanceResponse rdsInstanceResponse = new RdsInstanceResponse();
                rdsInstanceResponse.setNetType(e.getDBInstanceNetType());
                rdsInstanceResponse.setInstanceType(e.getDBInstanceType());
                rdsInstanceResponse.setDBInstanceId(e.getDBInstanceId());
                rdsInstanceResponse.setDbInstanceClass(e.getDBInstanceClass());
                rdsInstanceResponse.setConnectionMode(e.getConnectionMode());
                rdsInstanceResponse.setInstanceNetworkType(e.getInstanceNetworkType());
                rdsInstanceResponse.setEngineVersion(e.getEngineVersion());
                rdsInstanceResponse.setMutriOrsignle(e.getMutriORsignle());
                rdsInstanceResponse.setDbInstanceStatus(e.getDBInstanceStatus());
                rdsInstanceResponse.setEngine(e.getEngine());
                rdsInstanceResponse.setDbType(e.getDBInstanceClass());
                rdsInstanceResponse.setChargeType(e.getPayType());
                rdsInstanceResponse.setCreateTime(e.getCreateTime());
                rdsInstanceResponse.setZoneId(e.getZoneId());
                rdsInstanceResponse.setRegionId(createRdsInstanceRequest.getRegionId());
                rdsInstanceResponse.setExpireTime(e.getExpireTime());
                responseList.add(rdsInstanceResponse);
            });
            //将RDS的providerId存起来用于下一个接口的查询
            for (DescribeDBInstancesResponse.DBInstance item : response.getItems()) {
                rdsIds.add(item.getDBInstanceId());
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            throw new RuntimeException("ErrMsg:" + e.getErrMsg());
        }
        //查询rds具体属性的值
        rdsIds.forEach(k->{
            DescribeDBInstanceAttributeRequest describeRdsAttributeRequest = new DescribeDBInstanceAttributeRequest();
            describeRdsAttributeRequest.setDBInstanceId(k);
            try {
                DescribeDBInstanceAttributeResponse response = clients.getAcsResponse(describeRdsAttributeRequest);
                responseList.forEach(m->{
                    if(k.equalsIgnoreCase(m.getDBInstanceId())){
                        m.setDbInstanceMemory(response.getItems().get(0).getDBInstanceMemory());
                        m.setName(response.getItems().get(0).getDBInstanceDescription());
                        m.setPort(response.getItems().get(0).getPort());
                        m.setCategory(response.getItems().get(0).getCategory());
                        m.setDbInstanceStorage(response.getItems().get(0).getDBInstanceStorage());
                        m.setDbInstanceCpu(response.getItems().get(0).getDBInstanceCPU());
                        m.setMaxConnections(response.getItems().get(0).getMaxConnections());
                        m.setMaxiops(response.getItems().get(0).getMaxIOPS());
                        m.setVpcId(response.getItems().get(0).getVpcId());
                        m.setVswitchId(response.getItems().get(0).getVSwitchId());
                        m.setDbInstanceStorage(response.getItems().get(0).getDBInstanceStorage());
                        m.setDbInstanceClassType(response.getItems().get(0).getDBInstanceClassType());
                        m.setDbInstanceStorageType(response.getItems().get(0).getDBInstanceStorageType());
                    }
                });
            } catch (ServerException e) {
                e.printStackTrace();
            } catch (ClientException e) {
                throw new RuntimeException("ErrMsg:" + e.getErrMsg());
            }
        });
        return responseList;
    }
}
