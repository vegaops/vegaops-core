package tencent.parser;

import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.parser.ActionParser;

import java.util.HashMap;
import java.util.Map;

public class TestVolumeParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate(){
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("tencent","1.0", "volume");
        cloudTemplate.setComponentId("1234567890");
        Map input=new HashMap();
        input.put("accessKey","xxxxx");
        input.put("secret","xxxxx");
        input.put("regionId", "ap-beijing");
        cloudTemplate.inputVars(input);
        return cloudTemplate;
    }



    @Test
    @SneakyThrows
    public void parseGetVolumeList(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        CloudAction list = cloudTemplate.getCloudAction("list");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseGetVolumeDetail(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("volumeId","disk-a630l5e5");
        CloudAction list = cloudTemplate.getCloudAction("query");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseCreateVolume(){
        CloudTemplate cloudTemplate=getCloudTemplate();
//        cloudTemplate.getVariables().put("period","1");
        cloudTemplate.getVariables().put("chargeType","PREPAID");
        cloudTemplate.getVariables().put("count","1");
        cloudTemplate.getVariables().put("name","disk-vegaops");
        cloudTemplate.getVariables().put("size", "100");
//        cloudTemplate.getVariables().put("encrypt","disk-a630l5e5");
//        cloudTemplate.getVariables().put("shareable","disk-a630l5e5");
        cloudTemplate.getVariables().put("zoneId","ap-beijing-1");
//        cloudTemplate.getVariables().put("snapshotId","disk-a630l5e5");
        cloudTemplate.getVariables().put("category","CLOUD_SSD");
        cloudTemplate.getVariables().put("period","1");
//        cloudTemplate.getVariables().put("clientToken","disk-a630l5e5");
        CloudAction list = cloudTemplate.getCloudAction("install");
        ActionParser.parse(list);
    }
}
