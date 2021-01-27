package org.prophetech.hyperone.vegaops.aws.client;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.prophetech.hyperone.vegaops.aws.model.CreateInstanceParam;
import org.prophetech.hyperone.vegaops.aws.model.GetInstancesResult;
import org.prophetech.hyperone.vegaops.aws.model.InstanceParser;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j(topic = "vegaops")
public class AwsInstanceClient {
    public GetInstancesResult getInstances(Ec2Client ec2) {
        List<Instance> instances = new ArrayList();
        GetInstancesResult result = new GetInstancesResult();
        try {
            DescribeInstancesRequest.Builder builder = DescribeInstancesRequest.builder();
            DescribeInstancesRequest request = builder.build();
            DescribeInstancesResponse response;
            do {
                response = ec2.describeInstances(request);
                result.setStatusCode(response.sdkHttpResponse().statusCode());
                response.reservations().forEach(reservation -> {
                    instances.addAll(reservation.instances());
                });
                if (response.nextToken() != null){
                    request = builder.nextToken(response.nextToken()).build();
                }
            } while (response.nextToken() != null);
            ec2.close();
        } catch (Ec2Exception e) {
            log.error("获取AWS云主机出错", e);
        }
        List<InstanceParser> instanceParsers = parserInstance(instances);
        result.setInstances(instanceParsers);
        return result;
    }

    public GetInstancesResult getInstanceDetail(Ec2Client ec2, String instanceId) {
        List<Instance> instances = new ArrayList();
        GetInstancesResult result = new GetInstancesResult();
        try {
            DescribeInstancesRequest.Builder builder = DescribeInstancesRequest.builder();
            DescribeInstancesRequest request = builder.instanceIds(instanceId).build();
            DescribeInstancesResponse response;
            do {
                response = ec2.describeInstances(request);
                result.setStatusCode(response.sdkHttpResponse().statusCode());
                response.reservations().forEach(reservation -> {
                    instances.addAll(reservation.instances());
                });
            } while (response.nextToken() != null);
            ec2.close();
        } catch (Ec2Exception e) {
            log.error("获取AWS云主机出错", e);
        }
        List<InstanceParser> instanceParsers = parserInstance(instances);
        result.setInstances(instanceParsers);
        return result;
    }

    private List<InstanceParser> parserInstance(List<Instance> instances) {
        List<InstanceParser> instanceParsers = instances.stream().map(o -> {
            InstanceParser instanceParser = new InstanceParser();
            instanceParser.setId(o.instanceId());
            instanceParser.setImage(o.imageId());
            instanceParser.setFlavor(o.instanceTypeAsString());
            instanceParser.setVpcId(o.vpcId());
            instanceParser.setPrivateIp(o.privateIpAddress());
            List<String> securityGroups = o.securityGroups().stream().map(groupIdentifier -> groupIdentifier.groupId()).collect(Collectors.toList());
            instanceParser.setSecurityGroups(JSON.toJSONString(securityGroups));
            instanceParser.setZoneId(o.placement().availabilityZone());
            instanceParser.setRegionId("");
            instanceParser.setFloatingIp(o.publicIpAddress());
            instanceParser.setPayType(o.instanceLifecycleAsString());
            instanceParser.setOsType(o.platformAsString());
            instanceParser.setVswithId(o.subnetId());
            o.tags().forEach(m->{
                if("Name".equalsIgnoreCase(m.key())){
                    instanceParser.setName(m.value());
                }
            });
            instanceParser.setCreated(String.valueOf(o.launchTime()));
//            instanceParser.setMemory();
            instanceParser.setCpu(o.cpuOptions().coreCount());
            instanceParser.setStatus(o.state().nameAsString());
//            instanceParser.setExpireTime();
            instanceParser.setKeyPairName(o.keyName());
            return instanceParser;
        }).collect(Collectors.toList());
        return instanceParsers;
    }

    public GetInstancesResult createInstance(Ec2Client ec2, CreateInstanceParam param) {
        try {
            RunInstancesRequest.Builder builder = RunInstancesRequest.builder();
            RunInstancesRequest request = builder
//                    .additionalInfo()
//                    .blockDeviceMappings()
//                    .capacityReservationSpecification()
//                    .clientToken()
//                    .cpuOptions(CpuOptionsRequest.builder().coreCount(param.getCpuNum()).threadsPerCore().build())
//                    .creditSpecification()
//                    .disableApiTermination()
//                    .ebsOptimized()
//                    .elasticGpuSpecification()
//                    .elasticInferenceAccelerators()
//                    .hibernationOptions()
                    .imageId(param.getImageId())
//                    .instanceInitiatedShutdownBehavior()
//                    .instanceMarketOptions()
                    .instanceType(param.getFlavorId())
//                    .ipv6Addresses()
//                    .ipv6AddressCount()
                    .kernelId(param.getKernelId())
                    .keyName(param.getKeypairName())
//                    .launchTemplate()
//                    .licenseSpecifications()
                    .maxCount(param.getMaxCount())
//                    .metadataOptions()
                    .minCount(1)
//                    .monitoring()
//                    .networkInterfaces()
                    .placement(Placement.builder().availabilityZone(param.getZoneId()).build())
                    .privateIpAddress(param.getPrivateIpAddress())
                    .ramdiskId(param.getRamDiskId())
                    .securityGroupIds(param.getSecurityGroups())
                    .subnetId(param.getSubnetId())
                    .tagSpecifications(TagSpecification.builder().resourceType(ResourceType.INSTANCE).tags(Tag.builder().key("Name").value(param.getName()).build()).build())
//                    .userData()
                    .build();
            RunInstancesResponse response = ec2.runInstances(request);
            GetInstancesResult instancesResult = queryInstanceStatus(ec2, response.instances().get(0).instanceId());
            instancesResult.setStatusCode(response.sdkHttpResponse().statusCode());
            ec2.close();
            return instancesResult;
        } catch (Ec2Exception e) {
            log.error("创建AWS云主机出错", e);
            throw new RuntimeException("创建AWS云主机出错");
        }
    }

    private GetInstancesResult queryInstanceStatus(Ec2Client ec2, String id){
        long now = System.currentTimeMillis();
        long waitInterval = 3000;
        long maxWait = 120000;
        do {
            GetInstancesResult result = getInstanceDetail(ec2, id);
            if (result.getStatusCode()!= 200 || Integer.valueOf(1).equals(result.getInstances().get(0).getStatus())){
                return result;
            }
            log.info("AWS云主机正在创建,instanceId:"+id);
            try {
                Thread.sleep(waitInterval);
            } catch (InterruptedException e) {
                log.error("重试查询AWS主机状态等待出错"+id);
            }
        }while (System.currentTimeMillis() < now + maxWait);
        ec2.close();
        throw new RuntimeException("AWS轮询主机状态超时,instanceId:"+id);
    }
}
