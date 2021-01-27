package aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.bssopenapi.model.v20171214.CreateAgAccountRequest;
import com.aliyuncs.bssopenapi.model.v20171214.CreateAgAccountResponse;
import com.aliyuncs.bssopenapi.model.v20171214.CreateResellerUserQuotaRequest;
import com.aliyuncs.bssopenapi.model.v20171214.CreateResellerUserQuotaResponse;
import com.aliyuncs.crm.model.v20150408.AddIdentityCertifiedForBidUserRequest;
import com.aliyuncs.crm.model.v20150408.AddIdentityCertifiedForBidUserResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.ram.model.v20150501.*;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.junit.Test;

public class CloudAccountTest {

    @Test
    @SneakyThrows
    public void createAgAccount() {
//        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);
        CreateAgAccountRequest request = new CreateAgAccountRequest();
        // 登录邮箱
        // 说明 该登录邮箱的后缀必须与申请虚商时填写的账号组邮箱后缀保持一致。
        request.setLoginEmail("chenwei@edatahome.com");
        // 账号属性，取值：
        // 1：企业
        // 0：个人
        request.setAccountAttr("0");
        // 企业名称
        request.setEnterpriseName("天梯互联信息科技有限公司");
        // 名
        request.setFirstName("Wei");
        // 姓
        request.setLastName("Chen");
        // 国籍代码，取值
        // CN：中国
        // HK：中国香港
        // US：美国
        // JP：日本
        request.setNationCode("CN");
        // 省份名称
        request.setProvinceName("湖北");
        // 城市名称
        request.setCityName("武汉");
        // 邮政编码
        request.setPostcode("431000");

        try {
            CreateAgAccountResponse response = client.getAcsResponse(request);
            // 错误码
            response.getCode();
            // 发起调用者的账号 ID
            response.getAgRelationDto().getMpk();
            // 业务场景类型
            response.getAgRelationDto().getType();
            // 创建出来的账号 ID
            response.getAgRelationDto().getPk();
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }


    @Test
    @SneakyThrows
    public void assumeRole() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
//        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        AssumeRoleRequest request = new AssumeRoleRequest();
        //指定角色的ARN。格式：acs:ram::$accountID:role/$roleName 。
        //说明
        //$accountID：阿里云账号ID。您可以通过登录阿里云控制台，将鼠标悬停在右上角头像的位置，单击安全设置进行查看。
        //$roleName：RAM角色名称。您可以通过登录RAM控制台，单击左侧导航栏的RAM角色管理，在RAM角色名称列表下进行查看。
        request.setRoleArn("acs:ram::" + "1395897310205686" + ":role/" + "aliyunid-ag-ram-role-admin");
        //用户自定义参数。此参数用来区分不同的令牌，可用于用户级别的访问审计。
        //长度为2~32个字符，可包含英文字母、数字、英文句点（.）、at（@）、短划线（-）和下划线（_）。
        request.setRoleSessionName("onezero");
        //权限策略。
        //生成STS Token时可以指定一个额外的权限策略，以进一步限制STS Token的权限。若不指定则返回的Token拥有指定角色的所有权限。
        //长度为1~1024个字符。
//        request.setPolicy("");
        //过期时间，单位为秒。
        //过期时间最小值为900秒，最大值为MaxSessionDuration设置的时间。默认值为3600秒。
        //说明 您可以通过CreateRole或UpdateRole接口设置角色最大会话时间MaxSessionDuration。详情请参见CreateRole或UpdateRole。
        request.setDurationSeconds(3600L);
        AssumeRoleResponse response = client.getAcsResponse(request);
//        String result = getSignToken(response.getCredentials());
        response.getAssumedRoleUser();
        response.getCredentials();
    }


    @Test
    @SneakyThrows
    public void addIdentityCertifiedForBidUser() {
//        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
//        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);
        AddIdentityCertifiedForBidUserRequest request = new AddIdentityCertifiedForBidUserRequest();
        request.setSysEndpoint("crm-cn-hangzhou.aliyuncs.com");
        // 虚商类型
        //  NORMAL：普通虚商（默认值）
        //  LIGHTWEIGHT：轻量级虚商
        request.setBidType("LIGHTWEIGHT");
        // 被实名认证的账号的阿里云数字 ID
        request.setPK("1442098325661561");
        // 实名认证时的手机号
        request.setPhone("123456789");
        // 实名认证时的真实姓名或企业名称
        request.setName("enduser");
        // 实名认证时的证件类型 ，取值：
        // ID：身份证
        // BUSINESS：营业执照
        // ORGANIZATION_CODE：组织机构代码证书
        // INSTITUTION_LEGAL_PERSON：事业法人证书
        request.setLicenseType("ID");
        // 实名认证时的证件号。
        request.setLicenseNumber("enduser");
        // 实名认证类型。取值：
        // true 表示企业实名认证
        // false 表示个人实名认证
        request.setIsEnterprise(true);

        try {
            AddIdentityCertifiedForBidUserResponse response = client.getAcsResponse(request);
            response.getRequestId();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }

    @Test
    @SneakyThrows
    public void createUser() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "STS.NT7a244uCmrjY5byVDAwtpBU9", "xxxxx",
                "CAISgAJ1q6Ft5B2yfSjIr5eCKoiA2api2rCBWxPTnVYRTfhYn4f+2zz2IHBJenJqCOEdsv8wlGhW7f0flq50TZJXSFbDNWDISh+Bt1HPWZHInuDox55m4cTXNAr+Ihr/29CoEIedZdjBe/CrRknZnytou9XTfimjWFrXWv/gy+QQDLItUxK/cCBNCfpPOwJms7V6D3bKMuu3OROY6Qi5TmgQ41Et1DMutv/mmpfEt0SO0meXkLFF+97DRbG/dNRpMZtFVNO44fd7bKKp0lQLu0UUr/0n3fcYomiX5o/GWARLkRyCMvvJ9NxjaQZ2I7IzHuteq/zx0PR0v+3Vh14gFcbGiW4/GoABk65dZyZNkpQU3OyqcsAvXx7z7q1iNTkktQNDnaCQyHXos03L3vUz7+WcSqmkakf98oar2cq3MfxnbzPZB0vWCb0l9qEVbZZV7LXuCFvdMU/tSYk0aeCOhoNJXCZIc5F8F8LGF/YNqvz5IB27FLaLynegHt3puGzqQ2OR6f3ppEU=");
        IAcsClient client = new DefaultAcsClient(profile);
        CreateUserRequest request = new CreateUserRequest();
        request.setUserName("WeiChen");
        CreateUserResponse response = client.getAcsResponse(request);
        response.getUser();
    }

    @Test
    @SneakyThrows
    public void createAccessKey() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "STS.NT7a244uCmrjY5byVDAwtpBU9", "xxxxx",
                "CAISgAJ1q6Ft5B2yfSjIr5eCKoiA2api2rCBWxPTnVYRTfhYn4f+2zz2IHBJenJqCOEdsv8wlGhW7f0flq50TZJXSFbDNWDISh+Bt1HPWZHInuDox55m4cTXNAr+Ihr/29CoEIedZdjBe/CrRknZnytou9XTfimjWFrXWv/gy+QQDLItUxK/cCBNCfpPOwJms7V6D3bKMuu3OROY6Qi5TmgQ41Et1DMutv/mmpfEt0SO0meXkLFF+97DRbG/dNRpMZtFVNO44fd7bKKp0lQLu0UUr/0n3fcYomiX5o/GWARLkRyCMvvJ9NxjaQZ2I7IzHuteq/zx0PR0v+3Vh14gFcbGiW4/GoABk65dZyZNkpQU3OyqcsAvXx7z7q1iNTkktQNDnaCQyHXos03L3vUz7+WcSqmkakf98oar2cq3MfxnbzPZB0vWCb0l9qEVbZZV7LXuCFvdMU/tSYk0aeCOhoNJXCZIc5F8F8LGF/YNqvz5IB27FLaLynegHt3puGzqQ2OR6f3ppEU=");
        IAcsClient client = new DefaultAcsClient(profile);

        CreateAccessKeyRequest request = new CreateAccessKeyRequest();
        request.setUserName("WeiChen");
        CreateAccessKeyResponse response = client.getAcsResponse(request);
        response.getAccessKey().getAccessKeyId();
        response.getAccessKey().getAccessKeySecret();
    }


    @Test
    @SneakyThrows
    public void attachRoleToUser() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "STS.NT7a244uCmrjY5byVDAwtpBU9", "xxxxx",
                "CAISgAJ1q6Ft5B2yfSjIr5eCKoiA2api2rCBWxPTnVYRTfhYn4f+2zz2IHBJenJqCOEdsv8wlGhW7f0flq50TZJXSFbDNWDISh+Bt1HPWZHInuDox55m4cTXNAr+Ihr/29CoEIedZdjBe/CrRknZnytou9XTfimjWFrXWv/gy+QQDLItUxK/cCBNCfpPOwJms7V6D3bKMuu3OROY6Qi5TmgQ41Et1DMutv/mmpfEt0SO0meXkLFF+97DRbG/dNRpMZtFVNO44fd7bKKp0lQLu0UUr/0n3fcYomiX5o/GWARLkRyCMvvJ9NxjaQZ2I7IzHuteq/zx0PR0v+3Vh14gFcbGiW4/GoABk65dZyZNkpQU3OyqcsAvXx7z7q1iNTkktQNDnaCQyHXos03L3vUz7+WcSqmkakf98oar2cq3MfxnbzPZB0vWCb0l9qEVbZZV7LXuCFvdMU/tSYk0aeCOhoNJXCZIc5F8F8LGF/YNqvz5IB27FLaLynegHt3puGzqQ2OR6f3ppEU=");
        IAcsClient client = new DefaultAcsClient(profile);

        AttachPolicyToUserRequest request = new AttachPolicyToUserRequest();
        request.setPolicyType("System");
        request.setPolicyName("AdministratorAccess");
        request.setUserName("WeiChen");
        AttachPolicyToUserResponse response = client.getAcsResponse(request);
        response.getRequestId();
    }

    @Test
    @SneakyThrows
    public void createResellerUserQuota() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);
        CreateResellerUserQuotaRequest request = new CreateResellerUserQuotaRequest();
        request.setAmount("20000");
        request.setOwnerId(1442098325661561L);
        request.setCurrency("CNY");
        CreateResellerUserQuotaResponse response = client.getAcsResponse(request);
        response.getRequestId();
    }
}
