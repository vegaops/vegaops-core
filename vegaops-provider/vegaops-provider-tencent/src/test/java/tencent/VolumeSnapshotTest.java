package tencent;

import com.tencentcloudapi.cbs.v20170312.CbsClient;
import com.tencentcloudapi.cbs.v20170312.models.DescribeSnapshotsRequest;
import com.tencentcloudapi.cbs.v20170312.models.DescribeSnapshotsResponse;
import com.tencentcloudapi.cbs.v20170312.models.Snapshot;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VolumeSnapshotTest {

    private static String key = "xxxxx";
    private static String secret = "xxxxx";


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
        DescribeSnapshotsRequest req = new DescribeSnapshotsRequest();
        req.setLimit(pageSize);
        req.setOffset(0L);
        List<Snapshot> disks = new ArrayList<>();
        DescribeSnapshotsResponse resp = new DescribeSnapshotsResponse();
        do {
            try {
                resp = client.DescribeSnapshots(req);
                if (resp.getSnapshotSet() != null) {
                    disks.addAll(Arrays.asList(resp.getSnapshotSet()));
                }
                req.setOffset(pageSize);
            } catch (TencentCloudSDKException e) {
                e.printStackTrace();
            }
        } while (resp.getSnapshotSet().length != 0);
        System.out.println(disks);
    }
}
