package tencent;

import com.tencentcloudapi.cam.v20190116.CamClient;
import com.tencentcloudapi.cam.v20190116.models.CreateRoleRequest;
import com.tencentcloudapi.cam.v20190116.models.CreateRoleResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.cvm.v20170312.CvmClient;
import com.tencentcloudapi.cvm.v20170312.models.*;
import com.tencentcloudapi.sts.v20180813.StsClient;
import com.tencentcloudapi.sts.v20180813.models.AssumeRoleRequest;
import com.tencentcloudapi.sts.v20180813.models.AssumeRoleResponse;
import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.jsoup.Connection;
import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;

;

public class InstanceTest {

    private static String key = "xxxxx";
    private static String secret = "xxxxx";

    @Test
    @SneakyThrows
    public void list() {
        try {

            Credential cred = new Credential(key, secret);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cvm.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CvmClient client = new CvmClient(cred, "ap-beijing", clientProfile);

            DescribeInstancesRequest req = new DescribeInstancesRequest();
            req.setInstanceIds(new String[]{"ins-qvme6hah"});
            req.setLimit(100L);
            req.setOffset(0L);

            DescribeInstancesResponse resp = client.DescribeInstances(req);
            System.out.println(DescribeInstancesRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    @SneakyThrows
    public void create() {
        try{

            Credential cred = new Credential(key, secret);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cvm.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CvmClient client = new CvmClient(cred, "ap-beijing", clientProfile);

            RunInstancesRequest req = new RunInstancesRequest();
//            req.setDryRun(true);
//            req.setHostName("主机名");
            req.setImageId("img-9qabwvbn");
//            InstanceChargePrepaid prepaid = new InstanceChargePrepaid();
//            prepaid.setPeriod(1L);
//            req.setInstanceChargePrepaid(prepaid);
            req.setInstanceChargeType("POSTPAID_BY_HOUR");
            req.setInstanceCount(1L);
//            req.setInstanceMarketOptions();
            req.setInstanceName("fty_test");
//            req.setInstanceType("实力类型");
            InternetAccessible internetAccessible = new InternetAccessible();
            internetAccessible.setInternetChargeType("TRAFFIC_POSTPAID_BY_HOUR");
//            internetAccessible.setInternetMaxBandwidthOut(1L);
//            internetAccessible.setBandwidthPackageId("带宽包ID");
            // 是否分配公网IP。取值范围：
//            internetAccessible.setPublicIpAssigned(true);
            req.setInternetAccessible(internetAccessible);
            LoginSettings loginSettings = new LoginSettings();
//            String[] keyName = new String[1];
//            keyName[0] = "密钥对";
//            loginSettings.setKeyIds(keyName);
//            loginSettings.setKeepImageLogin();
            loginSettings.setPassword("Fu1234567");
            req.setLoginSettings(loginSettings);
            Placement placement = new Placement();
            placement.setZone("ap-beijing-1");
            req.setPlacement(placement);

            String[] securityGroupId = new String[1];
            securityGroupId[0] = "sg-kz69njcl";
            req.setSecurityGroupIds(securityGroupId);
            SystemDisk systemDisk = new SystemDisk();
            systemDisk.setDiskSize(100L);
            systemDisk.setDiskType("CLOUD_SSD");
//            systemDisk.setDiskId();
            req.setSystemDisk(systemDisk);
//            req.setTagSpecification();
//            req.setUserData();
            VirtualPrivateCloud cloud = new VirtualPrivateCloud();
            cloud.setVpcId("vpc-1f0ziyua");
            cloud.setSubnetId("subnet-8ni0c15d");
            // 是否用作公网网关。
//            cloud.setAsVpcGateway(false);
            // 为弹性网卡指定随机生成的 IPv6 地址数量。
//            cloud.setIpv6AddressCount(1L);
//            cloud.setPrivateIpAddresses();
//            req.setVirtualPrivateCloud(cloud);
            RunInstancesResponse resp = client.RunInstances(req);

            System.out.println(RunInstancesRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    @SneakyThrows
    public void uninstall() {
        try {

            Credential cred = new Credential(key, secret);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cvm.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CvmClient client = new CvmClient(cred, "ap-shanghai", clientProfile);

            TerminateInstancesRequest req = new TerminateInstancesRequest ();
            String[] a = new String[1];
            a[0] = "ins-6ahxfyq3";
            req.setInstanceIds(a);

            TerminateInstancesResponse resp = client.TerminateInstances(req);
            System.out.println(DescribeInstancesRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    @SneakyThrows
    public void queryStatus(){
        try{

            Credential cred = new Credential(key, secret);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cvm.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CvmClient client = new CvmClient(cred, "ap-beijing", clientProfile);

            DescribeInstancesStatusRequest req = new DescribeInstancesStatusRequest();
            req.setInstanceIds(new String[]{"ins-ntubkkdn"});

            DescribeInstancesStatusResponse resp = client.DescribeInstancesStatus(req);
            System.out.println(DescribeInstancesStatusRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    @SneakyThrows
    public void stop(){
        try{

            Credential cred = new Credential("", "");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cvm.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CvmClient client = new CvmClient(cred, "", clientProfile);

            StopInstancesRequest req = new StopInstancesRequest();

            StopInstancesResponse resp = client.StopInstances(req);

            System.out.println(StopInstancesRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    @SneakyThrows
    public void update(){
        try{

            Credential cred = new Credential("", "");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cvm.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CvmClient client = new CvmClient(cred, "", clientProfile);

            String params = "{}";
            ResetInstancesTypeRequest req = ResetInstancesTypeRequest.fromJsonString(params, ResetInstancesTypeRequest.class);

            ResetInstancesTypeResponse resp = client.ResetInstancesType(req);

            System.out.println(ResetInstancesTypeResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }


    /**
     * 创建角色
     */
    @Test
    @SneakyThrows
    public void createRole(){
        try{
            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cam.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CamClient client = new CamClient(cred, "ap-beijing", clientProfile);

            CreateRoleRequest req = new CreateRoleRequest();
            req.setRoleName("zhangdeshuai");
            req.setPolicyDocument("{\"version\":\"2.0\",\"statement\":[{\"action\":\"name/sts:AssumeRole\",\"effect\":\"allow\",\"principal\":{\"service\":[\"cloudaudit.cloud.tencent.com\",\"cls.cloud.tencent.com\"]}}]}");
            req.setConsoleLogin(1L);
            req.setSessionDuration(3600L);
            CreateRoleResponse resp = client.CreateRole(req);

            System.out.println(CreateRoleResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * 角色扮演
     */
    public Map<String, String> assumRole(){
        try {
            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sts.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            StsClient client = new StsClient(cred, "ap-beijing", clientProfile);


            AssumeRoleRequest req = new AssumeRoleRequest();
            req.setRoleArn("qcs::cam::uin/100010124987:roleName/zhangdeshuai");
            req.setRoleSessionName("oneProTest");
            AssumeRoleResponse resp = client.AssumeRole(req);
            Map<String, String> resultMap = new LinkedHashMap<>();
            resultMap.put("Token",resp.getCredentials().getToken());
            resultMap.put("TmpSecretId",resp.getCredentials().getTmpSecretId());
            resultMap.put("TmpSecretKey",resp.getCredentials().getTmpSecretKey());
            return resultMap;
    } catch (TencentCloudSDKException e) {
            throw new RuntimeException("error"+e);
    } }




    // 加密
    private String hmacSha1 (String src, String key){
        byte[] result = null;
        try {
            //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
            SecretKeySpec signinKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
            //生成一个指定 Mac 算法 的 Mac 对象
            Mac mac = Mac.getInstance("HmacSHA1");
            //用给定密钥初始化 Mac 对象
            mac.init(signinKey);
            //完成 Mac 操作
            byte[] rawHmac = mac.doFinal(src.getBytes());
            result = Base64.encodeBase64(rawHmac);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e.getMessage());
        }
        if (null != result) {
            return new String(result);
        } else {
            return null;
        }
    }

    @Test
    @SneakyThrows
    public void testZoneFlavorConfigs(){
        try{

            Credential cred = new Credential("xxxxx", "xxxxx");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cvm.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CvmClient client = new CvmClient(cred, "ap-beijing", clientProfile);

            DescribeZoneInstanceConfigInfosRequest req = new DescribeZoneInstanceConfigInfosRequest();
            Filter[] filters1 = new Filter[1];
            Filter filter1 = new Filter();

            String[] values1 = {"ap-beijing-1"};
            filter1.setValues(values1);

            filter1.setName("zone");
            filters1[0] = filter1;

            req.setFilters(filters1);


            DescribeZoneInstanceConfigInfosResponse resp = client.DescribeZoneInstanceConfigInfos(req);

            System.out.println(DescribeZoneInstanceConfigInfosResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

}


