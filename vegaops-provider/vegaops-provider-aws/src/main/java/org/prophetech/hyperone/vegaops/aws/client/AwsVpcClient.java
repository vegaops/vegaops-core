package org.prophetech.hyperone.vegaops.aws.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.prophetech.hyperone.vegaops.aws.model.CreateVpcParam;
import org.prophetech.hyperone.vegaops.aws.model.GetVpcResult;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

@Slf4j(topic = "vegaops")
public class AwsVpcClient {

    public GetVpcResult createVpc(Ec2Client ec2, CreateVpcParam param) {
        GetVpcResult result = new GetVpcResult();
        try {
            CreateVpcRequest request = CreateVpcRequest.builder().cidrBlock(param.getCidrBlock()).instanceTenancy(param.getInstanceTenancy()).build();
            CreateVpcResponse response = ec2.createVpc(request);
            if(response.sdkHttpResponse().statusCode() == 200){
                result.setStatusCode(response.sdkHttpResponse().statusCode());
                result.setVpcId(response.vpc().vpcId());
                result.setCidrBlcok(response.vpc().cidrBlock());
                result.setVpcState(response.vpc().stateAsString());
            }
            return result;
        } catch (Ec2Exception e) {
            log.error("创建Aws Vpc出错", e);
            throw new RuntimeException("创建Aws Vpc出错");
        }
    }

    public GetVpcResult DescribeVpc(Ec2Client ec2, CreateVpcParam param) {
        GetVpcResult result = new GetVpcResult();
        try {
            DescribeVpcsRequest request = DescribeVpcsRequest.builder().build();
            DescribeVpcsResponse response = ec2.describeVpcs(request);
            if(response.sdkHttpResponse().statusCode() == 200){
                result.setStatusCode(response.sdkHttpResponse().statusCode());
                response.vpcs().forEach(e->{
                    result.setVpcId(e.vpcId());
                    result.setVpcState(e.stateAsString());

                    e.tags().forEach(m->{
                        if("Name".equalsIgnoreCase(m.key())){
                            result.setName(m.value());
                        }
                    });
                });
            }
            return result;
        } catch (Ec2Exception e) {
            log.error("查询Aws Vpc出错", e);
            throw new RuntimeException("查询Aws Vpc出错");
        }
    }

    public GetVpcResult createVswitch(Ec2Client ec2, CreateVpcParam param) {
        GetVpcResult result = new GetVpcResult();
        try {
            CreateSubnetRequest request = CreateSubnetRequest.builder().cidrBlock(param.getCidrBlock()).availabilityZone(param.getAvailabilityZone()).vpcId(param.getVpcId()).build();
            CreateSubnetResponse response = ec2.createSubnet(request);
            if(response.sdkHttpResponse().statusCode() == 200){
                result.setStatusCode(response.sdkHttpResponse().statusCode());
                result.setVpcId(response.subnet().vpcId());
                result.setSubnetId(response.subnet().subnetId());
                result.setCidrBlcok(response.subnet().cidrBlock());
                result.setVswitchState(response.subnet().stateAsString());;
            }
            return result;
        } catch (Ec2Exception e) {
            log.error("创建Aws Vpc出错", e);
            throw new RuntimeException("创建Aws Vpc出错");
        }
    }

    private GetVpcResult createInternetGateway(Ec2Client ec2){
        GetVpcResult result = new GetVpcResult();
        try {
            CreateInternetGatewayRequest request = CreateInternetGatewayRequest.builder().build();
            CreateInternetGatewayResponse response = ec2.createInternetGateway(request);
            if(response.sdkHttpResponse().statusCode() == 200){
                result.setStatusCode(response.sdkHttpResponse().statusCode());
                result.setInternetGatewayId(response.internetGateway().internetGatewayId());
            }
            return result;
        }catch (Ec2Exception e) {
            log.error("创建AWs InternetGateway出错", e);
            throw new RuntimeException("创建AWS InternetGateway出错");
        }
    }

    private GetVpcResult attachVpcToInternetGateway(Ec2Client ec2, CreateVpcParam param){
        try {
            GetVpcResult result = new GetVpcResult();
            AttachInternetGatewayRequest request = AttachInternetGatewayRequest.builder().vpcId(param.getVpcId()).internetGatewayId(param.getInternetGatewayId()).build();
            AttachInternetGatewayResponse response = ec2.attachInternetGateway(request);
            if(response.sdkHttpResponse().statusCode()!= 200){
                log.error("关联AWs Vpc和InternetGateway出错");
            }
            else {
                result.setStatusCode(response.sdkHttpResponse().statusCode());
            }
            return result;
        }catch (Ec2Exception e) {
            log.error("关联AWs Vpc和InternetGateway出错", e);
            throw new RuntimeException("关联AWs Vpc和InternetGateway出错");
        }
    }

    private GetVpcResult createRouteTable(Ec2Client ec2, CreateVpcParam param){
        GetVpcResult result = new GetVpcResult();
        try{
            CreateRouteTableRequest request = CreateRouteTableRequest.builder().vpcId(param.getVpcId()).build();
            CreateRouteTableResponse response = ec2.createRouteTable(request);
            if(response.sdkHttpResponse().statusCode()== 200){
                result.setRouteTableId(response.routeTable().routeTableId());
                result.setVpcId(response.routeTable().vpcId());
            }
         return result;
        }catch (Ec2Exception e){
            log.error("创建AWs路由表出错", e);
            throw new RuntimeException("创建AWs路由表出错");
        }
    }

    private GetVpcResult associateRouteTable(Ec2Client ec2, CreateVpcParam param){
        GetVpcResult result = new GetVpcResult();
        try{
            AssociateRouteTableRequest request = AssociateRouteTableRequest.builder().gatewayId(param.getInternetGatewayId()).subnetId(param.getVswitchId()).routeTableId(param.getRouteTableId()).build();
            AssociateRouteTableResponse response = ec2.associateRouteTable(request);
            if(response.sdkHttpResponse().statusCode()== 200){
                result.setAssociationId(response.associationId());
            }
            return result;
        }catch (Ec2Exception e){
            log.error("关联AWs路由表和子网出错", e);
            throw new RuntimeException("关联AWs路由表和子网出错");
        }
    }



   private GetVpcResult vpcToPublic (Ec2Client ec2, CreateVpcParam param){
        GetVpcResult result = new GetVpcResult();
        //创建vpc
       GetVpcResult vpc =  createVpc(ec2,param);
       //创建互联网网关
       GetVpcResult internetGateway = createInternetGateway(ec2);
       //创建子网
       if(StringUtils.isEmpty(vpc.getVpcId())||StringUtils.isEmpty(vpc.getVpcState())){
           do{
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           while (!"available".equalsIgnoreCase(vpc.getVpcState()));
               result.setVpcId(vpc.getVpcId());
               CreateVpcParam createVswitchParam = new CreateVpcParam();
               createVswitchParam.setVpcId(vpc.getVpcId());
               createVswitchParam.setCidrBlock("10.0.0.0/16");
               GetVpcResult vswitch = createVswitch(ec2,createVswitchParam);

           //关联vpc和互联网网关
           CreateVpcParam attachVpcToGatewayParam = new CreateVpcParam();
           attachVpcToGatewayParam.setVpcId(vpc.getVpcId());
           if(StringUtils.isNotEmpty(internetGateway.getInternetGatewayId())){
               attachVpcToGatewayParam.setInternetGatewayId(internetGateway.getInternetGatewayId());
               GetVpcResult attachVpcToInternetGatewayRes =  attachVpcToInternetGateway(ec2, attachVpcToGatewayParam);
           }
           //关联子网至路由表
           if(StringUtils.isNotEmpty(vswitch.getSubnetId())){
               result.setSubnetId(vswitch.getSubnetId());
               CreateVpcParam associateSubnetToRouteParam = new CreateVpcParam();
               associateSubnetToRouteParam.setVswitchId(vswitch.getSubnetId());
               associateSubnetToRouteParam.setInternetGatewayId(internetGateway.getInternetGatewayId());
               associateSubnetToRouteParam.setRouteTableId(param.getRouteTableId());
               GetVpcResult routeTbaleRes = associateRouteTable(ec2,associateSubnetToRouteParam);
           }
       }
       else {
           throw new RuntimeException("创建vpc失败");
       }
        return result;
   }
}
