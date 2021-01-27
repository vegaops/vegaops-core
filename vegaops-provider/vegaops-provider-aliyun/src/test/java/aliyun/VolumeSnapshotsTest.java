package aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.ecs.model.v20140526.DescribeSnapshotsRequest;
import com.aliyuncs.ecs.model.v20140526.DescribeSnapshotsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.junit.Test;

public class VolumeSnapshotsTest {

    @Test
    @SneakyThrows
    public  void getRegionList() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-beijing", "xxxxx", "xxxxx");
        IAcsClient client = new DefaultAcsClient(profile);

        DescribeSnapshotsRequest request = new DescribeSnapshotsRequest();

        try {
            DescribeSnapshotsResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }



    }

}

