package parser;

import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.parser.ActionParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestInstanceParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate(){
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("ctyun","1.0", "instance");
        cloudTemplate.setComponentId("1234567890");
        Map input=new HashMap();
        input.put("accessKey","xxxxx");
        input.put("secret","xxxxx");
        input.put("regionId","cn-gzT");
        cloudTemplate.inputVars(input);
        return cloudTemplate;
    }
    @Test
    @SneakyThrows
    public void parseStopInstance(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("instanceId","fc6b3886-6e5e-4bcc-800d-9b55394b9947");
        input.put("instanceChargeType","PostPaid");
        input.put("type","SOFT");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("stop");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parseStartInstance(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("instanceId","5ea33e59-f5a7-4ebd-b409-b21aff3e6705");
        input.put("instanceChargeType","PrePaid");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("start");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parseGetInstance(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
//        input.put("instanceId","5ea33e59-f5a7-4ebd-b409-b21aff3e6705");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("list");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parseQueryInstance(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("instanceId","fd7d1d37-c0ba-4763-9cab-8ae61e156da8");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("query");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void create(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("payType","PostPaid");
        input.put("regionId","cn-gzT");
        input.put("name","fty_test11");
        input.put("zoneId","cn-gzTa");
        input.put("imageRef","3af8a1ec-94a8-4e39-adef-7a9cedd93a44");
        input.put("osType","linux");
        input.put("volumeSize","50");
        input.put("volumeType","SATA");
        input.put("flavorRef","c2.medium");
        input.put("vpcId","3819d846-20bc-4159-bb7b-b434334707bd");
        input.put("securityGroups","1ae92871-e385-4a29-9db6-05413cdeffa2");
        input.put("VSwitchId","b94ecea6-2eae-4f93-9aa1-4dcda9ea9ac4");
        input.put("shareType","PER");
        input.put("adminPass","Fu123456");
        input.put("count","1");
        input.put("publicIpId","784992f5-441c-4c34-a499-99a951a98e49");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("install");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void delete(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("regionId", "cn-gzT");
        input.put("instanceId", "231e0eb5-d5f4-4e54-8d4f-882f879b2c88");
        input.put("instanceChargeType", "PostPaid");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("uninstall");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void array(){
        List<String> aaa = new ArrayList<>();
        aaa.add("a");
        aaa.add("b");
        aaa.size();
    }
}
