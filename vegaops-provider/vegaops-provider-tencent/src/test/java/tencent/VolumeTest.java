package tencent;

import com.tencentcloudapi.cbs.v20170312.CbsClient;
import com.tencentcloudapi.cbs.v20170312.models.*;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VolumeTest {

    private static String key = "xxxxx";
    private static String secret = "xxxxx";


    @Test
    @SneakyThrows
    public void volumeBindTest(){
        Credential cred = new Credential(key, secret);

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("cbs.tencentcloudapi.com");

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        CbsClient client = new CbsClient(cred, "ap-shanghai", clientProfile);

        AttachDisksRequest req = new AttachDisksRequest();
        req.setDeleteWithInstance(true);
        String[] a = new String[1];
        a[0] = "123";
        req.setDiskIds(a);
        req.setInstanceId("124");
        try {
            AttachDisksResponse resp = client.AttachDisks(req);
        }catch (TencentCloudSDKException e){
            System.out.println(e);
        }
    }

    @Test
    @SneakyThrows
    public void volumeUnbind(){
        try{

            Credential cred = new Credential(key, secret);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cbs.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CbsClient client = new CbsClient(cred, "ap-shanghai", clientProfile);

            DetachDisksRequest req = new DetachDisksRequest();
            String[] a = new String[1];
            a[0] = "123";
            req.setDiskIds(a);

            DetachDisksResponse resp = client.DetachDisks(req);
            System.out.println(DetachDisksRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    @SneakyThrows
    public void list(){
        Credential cred = new Credential(key, secret);
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("cbs.tencentcloudapi.com");
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        CbsClient client = new CbsClient(cred, "ap-shanghai", clientProfile);
        Long pageSize = 100L;
        DescribeDisksRequest req = new DescribeDisksRequest();
        req.setLimit(pageSize);
        req.setOffset(0L);
        List<Disk> disks = new ArrayList<>();
        DescribeDisksResponse resp = new DescribeDisksResponse();
        do {
            try {
                resp = client.DescribeDisks(req);
                if (resp.getDiskSet() != null) {
                    disks.addAll(Arrays.asList(resp.getDiskSet()));
                }
                req.setOffset(pageSize);
            } catch (TencentCloudSDKException e) {
                e.printStackTrace();
            }
        } while (resp.getDiskSet().length != 0);
        System.out.println(disks);
    }

    @Test
    @SneakyThrows
    public void uninstall(){
        try{

            Credential cred = new Credential(key, secret);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cbs.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CbsClient client = new CbsClient(cred, "ap-shanghai", clientProfile);

            TerminateDisksRequest req = new TerminateDisksRequest();
            String[] a = new String[1];
            a[0] = "123";
            req.setDiskIds(a);

            TerminateDisksResponse resp = client.TerminateDisks(req);
            System.out.println(DetachDisksRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }
}
