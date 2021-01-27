package aws;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.*;
import software.amazon.awssdk.services.organizations.OrganizationsClient;
import software.amazon.awssdk.services.organizations.model.*;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest;
import software.amazon.awssdk.services.sts.model.AssumeRoleResponse;

public class CloudAccountTest {

    @Test
    @SneakyThrows
    public void InviteAccount() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");

        OrganizationsClient client = OrganizationsClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTHWEST_1)
                .build();

        InviteAccountToOrganizationRequest request = InviteAccountToOrganizationRequest.builder()
                .target(HandshakeParty.builder().id("futognyong@qq.com").type("EMAIL").build())
                .notes("This is a test.")
                .build();
        InviteAccountToOrganizationResponse response = client.inviteAccountToOrganization(request);
        JSON.toJSONString(response);
    }

    @Test
    @SneakyThrows
    public void createAccount() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");

        OrganizationsClient client = OrganizationsClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTHWEST_1)
                .build();

        CreateAccountRequest request = CreateAccountRequest.builder()
                .accountName("hyperone_test")
                .email("futongyong@qq.com")
                .iamUserAccessToBilling("ALLOW")
                .roleName("hyperone_fty_test")
                .build();
        CreateAccountResponse response = client.createAccount(request);
        JSON.toJSONString(response);
    }

    @Test
    @SneakyThrows
    public void assumeRole() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");

        StsClient client = StsClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTHWEST_1)
                .build();

        AssumeRoleRequest request = AssumeRoleRequest.builder()
                // 角色会话的持续时间（以秒为单位）。 该值的范围从900秒（15分钟）到角色的最大会话持续时间设置。 此设置的值可以在1小时到12小时之间。 如果指定的值大于此设置，则操作将失败。默认情况下，该值设置为3600秒。有效范围:最小值900。最大值为43200。
//                .durationSeconds(3600)
                // 在另一个帐户中担任角色时可能需要的唯一标识符。 如果角色所属帐户的管理员为您提供了外部ID，请在ExternalId参数中提供该值。 该值可以是任何字符串，例如密码或帐号。 通常会设置跨帐户角色，以信任帐户中的每个人。 因此，信任帐户的管理员可能会向信任帐户的管理员发送外部ID。 这样，只有具有ID的人才能担任该角色，而不是帐户中的每个人。长度约束:最小长度为2。最大长度1224。
//                .externalId("712942869988")
                // 想要用作内联会话策略的JSON格式的IAM策略。此参数是可选的。将策略传递给此操作将返回新的临时凭据。生成的会话权限是角色的基于身份的策略和会话策略的交集。您可以在后续的AWS API调用中使用角色的临时凭证来访问拥有该角色的帐户中的资源。您不能使用会话策略授予比所假定角色的基于身份的策略所允许的权限更多的权限。
//                .policy("")
                // 您希望用作托管会话策略的IAM管理策略的Amazon资源名。策略必须存在于与角色相同的帐户中。此参数是可选的。您可以提供多达10个托管策略arn。但是，内联和托管会话策略使用的纯文本不能超过2048个字符。
//                .policyArns(PolicyDescriptorType.builder().arn("arn:aws-cn:organizations::932563355466:account/o-50jwxo55pl/671096652236").build())
                // Required: Yes 要承担的角色的Amazon资源名(ARN)。arn:aws:iam::932563355466:role/hyperone_test
                .roleArn("arn:aws-cn:iam::910353015999:role/OrganizationAccountAccessRole")
                // Required: Yes 假设角色会话的标识符。当不同主体或出于不同原因假定相同的角色时，使用角色会话名称来惟一地标识会话。在跨帐户场景中，角色会话名对拥有该角色的帐户可见，并且可以由该帐户记录。角色会话名也在假定的角色主体的ARN中使用。这意味着后续使用临时安全凭据的跨帐户API请求将在其AWS CloudTrail日志中向外部帐户公开角色会话名称。
                .roleSessionName("aws_onepro")
                // 与进行AssumeRole呼叫的用户相关联的MFA设备的标识号。 如果假定角色的信任策略包括要求MFA身份验证的条件，请指定此值。 该值可以是硬件设备（例如GAHT12345678）的序列号，也可以是虚拟设备（例如arn：aws：iam :: 123456789012：mfa / user）的Amazon资源名称（ARN）。
//                .serialNumber("")
                // 如果所假定的角色的信任策略需要MFA(即，如果策略包含测试MFA的条件)，则MFA设备提供的值。如果所假定的角色需要MFA，并且TokenCode值丢失或过期，则假设调用返回一个“拒绝访问”错误。此参数的格式（如其正则表达式模式所描述）是六个数字的序列。
//                .tokenCode("")
                // 要设置为可传递的会话标签的键列表。 如果将标记键设置为可传递键，则相应的键和值将传递到角色链中的后续会话。
//                .transitiveTagKeys("")
                .build();
        AssumeRoleResponse response = client.assumeRole(request);
        response.credentials();
    }

    @Test
    @SneakyThrows
    public void createUser() {
        AwsSessionCredentials credentials = AwsSessionCredentials.create("xxxxx",
                "xxxxx",
                "IQoJb3JpZ2luX2VjEO3//////////wEaDmNuLW5vcnRod2VzdC0xIkYwRAIgfYMRhtGWf9216KvC4cmw1CJg1i4QC/iB6UtDm6w9OKQCIDVt+/IGcNFTRz7u/E/KUX2Oi5gS+HxjvhumWHjVfI0gKtQBCBsQABoMNjcxMDk2NjUyMjM2IgwLGEgc0PuadnUmpKYqsQGAur7QZaUPEyfqzSeNMtb4Ec8L0DMKknxE/Mh0pSJvzRcg8M03sWe4/vVTfnloEvIF1s2O9TYyQeAD7cqXL4nQW9lFYWxiEH3EDucKDj9suD4H+aJasFSt2cLUwTAzqAsym1Uwc91Y779FqRKGCiPWjWpcxISKrwdYYeV2kcQ4JnlkE2Y6RJqNml+Pg/xcQz9/q4/KeFOQZPcl8lr9DBiy9uh3LR6gwKOqYBk4Aw8AMocw+66o+QU65AHQrKpbtPJZ5PLzx+D+5ZfBai5YGsmYCkfVselv0nDF/toAq5CEUUqkn7MMx0Z5jeftfXGo3Rdsukn+X7QZXkyIBkGDtkNcAh+X0tC8+AgM5+jqD64nCpJpVjLuTk/QLSCDptcOCBzKb7ZgBghvcFiuUWQpe9jlo3ZwN4aBEftgephRm0DdBHZPkZ7ODtcVxAGzMzOznW8xgMdqIlMsZifq7yJziNGPalA3icTkS0K21CyW3ini5kU7942Dhc6DubFwukFAyUgFTTWF0LhrpHwCShB8p/YIcEe26KbsidBjwKr0FpY="
        );

        IamClient client = IamClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.AWS_CN_GLOBAL)
                .build();

        CreateUserRequest request = CreateUserRequest.builder()
                // 用户名的路径。
//                .path("")
                // 用于设置用户权限边界的策略的ARN。
//                .permissionsBoundary("arn:aws-cn:iam::aws:policy/AdministratorAccess")
                // Required: Yes 要创建的用户名。
                .userName("flowTest2")
                .build();
            CreateUserResponse response = client.createUser(request);
        // 带有关于新的IAM用户的详细信息的结构。
        response.user();
        // /
        // flowTest
        // AIDAZYQD2IHGOFDWTGSX5
        // arn:aws-cn:iam::671096652236:user/flowTest

    }

    @Test
    @SneakyThrows
    public void attachRolePolicy() {
        AwsSessionCredentials credentials = AwsSessionCredentials.create("xxxxx",
                "HPE4PT/xxxxx",
                "IQoJb3JpZ2luX2VjEPT//////////wEaDmNuLW5vcnRod2VzdC0xIkcwRQIhALFY0s3YZEQja0T9KRBCW2UCGVlC2P5F4fiG+C3cT6TnAiASeyQ2z1GZHnrU3ssgXOIewqvpYAPkcjrLf0E2OZWQEyrRAQgiEAAaDDkxMDM1MzAxNTk5OSIM8eHnelrlx6klgpr0Kq4BunWzuhp5W7Z3a7PIOumqK5Pf9aAzCPfoAyhfqQfc8HpBVScn1iCztCtu8uZVuDVEnopDoe9o+LbmehcWe8LTmcvyIEgupTvKqUyB8HjiGdZNRL0PvYRYV/bkhHAge29Lapr9Vky/ce30r5fdglvQ7ZUebPEDGY0iQG/sy8n9XRVpFGZC9NelSjR/68e2MMWCBe9MBhFj6T/Y490+9Tx7yJE6wG4QmoJ/4sSO7yJ+MPDwqfkFOuMB6o2e7kxeAbU7DwXazQ2R43rACGOGrG/mH+97r/xxgJw4sCrue/vN4V6QmJ6km+x3Qt9aueOwiijIbr8D2zgYbH+RLYsZm/mfuCmg0m1WmcFeo5zh3a0QRtCVCtjmWfoGufvLvW22qKEdGTA014ZDClosbJmtrO6fv+R76YVShK4aT/XGlFY3Psa0GbcsPD4G+y0eLt/3YvBArEPOJzxdeFAIrJm/XPpqoNHsuo2EqrvRdqLRv21+5QtcZfJI+pRGtCr+REHHbzwmkibtCg1MVraNXmGDtfjoGNdGoqRs/fGhtu4=");


        IamClient client = IamClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.AWS_CN_GLOBAL)
                .build();

        AttachRolePolicyRequest request = AttachRolePolicyRequest.builder()
                .policyArn("arn:aws-cn:iam::aws:policy/AdministratorAccess")
                .roleName("OrganizationAccountAccessRole")
                .build();
        try {
            AttachRolePolicyResponse response = client.attachRolePolicy(request);
            response.hashCode();
        } catch (IamException e) {
            throw new RuntimeException("AWS授权角色过程失败");
        }
    }

    @Test
    @SneakyThrows
    public void attachUserPolicy() {
        AwsSessionCredentials credentials = AwsSessionCredentials.create("xxxxx",
                "HPE4PT/xxxxx",
                "IQoJb3JpZ2luX2VjEPT//////////wEaDmNuLW5vcnRod2VzdC0xIkcwRQIhALFY0s3YZEQja0T9KRBCW2UCGVlC2P5F4fiG+C3cT6TnAiASeyQ2z1GZHnrU3ssgXOIewqvpYAPkcjrLf0E2OZWQEyrRAQgiEAAaDDkxMDM1MzAxNTk5OSIM8eHnelrlx6klgpr0Kq4BunWzuhp5W7Z3a7PIOumqK5Pf9aAzCPfoAyhfqQfc8HpBVScn1iCztCtu8uZVuDVEnopDoe9o+LbmehcWe8LTmcvyIEgupTvKqUyB8HjiGdZNRL0PvYRYV/bkhHAge29Lapr9Vky/ce30r5fdglvQ7ZUebPEDGY0iQG/sy8n9XRVpFGZC9NelSjR/68e2MMWCBe9MBhFj6T/Y490+9Tx7yJE6wG4QmoJ/4sSO7yJ+MPDwqfkFOuMB6o2e7kxeAbU7DwXazQ2R43rACGOGrG/mH+97r/xxgJw4sCrue/vN4V6QmJ6km+x3Qt9aueOwiijIbr8D2zgYbH+RLYsZm/mfuCmg0m1WmcFeo5zh3a0QRtCVCtjmWfoGufvLvW22qKEdGTA014ZDClosbJmtrO6fv+R76YVShK4aT/XGlFY3Psa0GbcsPD4G+y0eLt/3YvBArEPOJzxdeFAIrJm/XPpqoNHsuo2EqrvRdqLRv21+5QtcZfJI+pRGtCr+REHHbzwmkibtCg1MVraNXmGDtfjoGNdGoqRs/fGhtu4=");

        IamClient client = IamClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.AWS_CN_GLOBAL)
                .build();

        AttachUserPolicyRequest request = AttachUserPolicyRequest.builder()
                .policyArn("arn:aws-cn:iam::aws:policy/AdministratorAccess")
                .userName("flowTest").build();
        AttachUserPolicyResponse response = client.attachUserPolicy(request);
        JSON.toJSONString(response);
    }

    @Test
    @SneakyThrows
    public void createAccessKey() {
        AwsSessionCredentials credentials = AwsSessionCredentials.create("xxxxx",
                "xxxxx",
                "IQoJb3JpZ2luX2VjEO3//////////wEaDmNuLW5vcnRod2VzdC0xIkYwRAIgfYMRhtGWf9216KvC4cmw1CJg1i4QC/iB6UtDm6w9OKQCIDVt+/IGcNFTRz7u/E/KUX2Oi5gS+HxjvhumWHjVfI0gKtQBCBsQABoMNjcxMDk2NjUyMjM2IgwLGEgc0PuadnUmpKYqsQGAur7QZaUPEyfqzSeNMtb4Ec8L0DMKknxE/Mh0pSJvzRcg8M03sWe4/vVTfnloEvIF1s2O9TYyQeAD7cqXL4nQW9lFYWxiEH3EDucKDj9suD4H+aJasFSt2cLUwTAzqAsym1Uwc91Y779FqRKGCiPWjWpcxISKrwdYYeV2kcQ4JnlkE2Y6RJqNml+Pg/xcQz9/q4/KeFOQZPcl8lr9DBiy9uh3LR6gwKOqYBk4Aw8AMocw+66o+QU65AHQrKpbtPJZ5PLzx+D+5ZfBai5YGsmYCkfVselv0nDF/toAq5CEUUqkn7MMx0Z5jeftfXGo3Rdsukn+X7QZXkyIBkGDtkNcAh+X0tC8+AgM5+jqD64nCpJpVjLuTk/QLSCDptcOCBzKb7ZgBghvcFiuUWQpe9jlo3ZwN4aBEftgephRm0DdBHZPkZ7ODtcVxAGzMzOznW8xgMdqIlMsZifq7yJziNGPalA3icTkS0K21CyW3ini5kU7942Dhc6DubFwukFAyUgFTTWF0LhrpHwCShB8p/YIcEe26KbsidBjwKr0FpY="
        );
        IamClient client = IamClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.AWS_CN_GLOBAL)
                .build();

        CreateAccessKeyRequest request = CreateAccessKeyRequest.builder().userName("flowTest2").build();
        CreateAccessKeyResponse response = client.createAccessKey(request);
        JSON.toJSONString(response);

    }

    @Test
    @SneakyThrows
    public void listUsers(){
        AwsBasicCredentials credentials = AwsBasicCredentials.create("xxxxx",
                "xxxxx");

        OrganizationsClient client = OrganizationsClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.CN_NORTHWEST_1)
                .build();

//        ListUsersRequest request = ListUsersRequest.builder().build();
        ListAccountsResponse response = client.listAccounts();
        response.accounts();
    }

}