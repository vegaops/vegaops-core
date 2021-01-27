package ctyun;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.ctyun.client.CtyunJsoupClient;
import org.prophetech.hyperone.vegaops.ctyun.client.CtyunVolumeClient;
import org.prophetech.hyperone.vegaops.ctyun.model.*;

import java.util.List;

public class VolumeTest {
    private static CtyunAccount ctyunAccount = new CtyunAccount("xxxxx", "xxxxx");

    @Test
    @SneakyThrows
    public void queryDataDiskDetail() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        QueryDataDiskDetailRequest request = new QueryDataDiskDetailRequest();
        request.setRegionId("cn-gzT");
        request.setVolumeId("93833647-d0d8-4c56-8863-dc94c62bd1a8");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void createVolume() {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        CreateVolumeRequest request = new CreateVolumeRequest();
//        request.setProjectId("0");
        request.setRegionId("cn-gzT");
        request.setZoneId("cn-gzTa");
        request.setName("ftyTest");
        request.setType("SATA");
        request.setSize("50");
        request.setCount("1");
//        request.setBackupId("");
        CtyunApiResponse ctyunResponse = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void getVolumes() {
        CtyunVolumeClient client = new CtyunVolumeClient();
        client.setCtyunAccount(ctyunAccount);
        GetVolumesRequest request = new GetVolumesRequest();
        request.setRegionId("cn-gzT");
        request.setVolumeId("f0afb6cd-080e-4a66-a697-a4de6de38b3d");
        List<VolumeResult> ctyunResponse =  client.getVolumes(request);
//        ctyunResponse.getReturnObj().getVolumes().forEach(o->{
//            deleteDisk(o.getId());
//        });
        System.out.println(JSON.toJSONString(ctyunResponse));
    }

    @Test
    @SneakyThrows
    public void createDatadisk() {
        CtyunVolumeClient client = new CtyunVolumeClient();
        client.setCtyunAccount(ctyunAccount);
        CreateDatadiskRequest request = new CreateDatadiskRequest();
        request.setPayType("PostPaid");
        request.setRegionId("cn-gzT");
        request.setZoneId("cn-gzTa");
        request.setName("ftyTestMain3");
        request.setType("SATA");
        request.setSize(50);
        request.setCount("1");
        CreateDatadiskRequest result = client.createVolume(request);
        System.out.println(JSON.toJSONString(result));
    }

    @SneakyThrows
    public void deleteDisk(String id) {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        DeleteVolumeRequest request = new DeleteVolumeRequest();
        request.setRegionId("cn-gzT");
        request.setVolumeId(id);
        CtyunApiResponse response = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    @SneakyThrows
    public void unbind(){
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        UninstallVolumeRequest request = new UninstallVolumeRequest();
        request.setRegionId("cn-gzT");
        request.setVmId("5ea33e59-f5a7-4ebd-b409-b21aff3e6705");
        request.setVolumeId("22fd639f-9efb-40f3-90f5-5fe7763d9c09");
        request.setDevice("/dev/sdb");
        request.setPayType("PostPaid");
        CtyunApiResponse response = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    @SneakyThrows
    public void bind(){
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        AttachVolumeRequest request = new AttachVolumeRequest();
        request.setRegionId("cn-gzT");
        request.setVmId("f0afb6cd-080e-4a66-a697-a4de6de38b3d");
        request.setVolumeId("22fd639f-9efb-40f3-90f5-5fe7763d9c09");
        request.setDevice("/dev/sdb");
        request.setPayType("PostPaid");
        CtyunApiResponse response = client.getCtyunResponse(request);
        System.out.println(JSON.toJSONString(response));
    }
}