package tencent.parser;

import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.parser.ActionParser;

import java.util.HashMap;
import java.util.Map;

public class TestCenInstanceParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate(){
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("tencent","1.0", "networkCenInstance");
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
    public void parseGetList(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input = new HashMap();
        input.put("cenId", "ccn-7nngem1z");
        cloudTemplate.getVariables().putAll(input);
        CloudAction list = cloudTemplate.getCloudAction("query");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void attach(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input = new HashMap();
        input.put("instanceType", "instanceType");
        input.put("instanceId", "instanceId");
        input.put("instanceRegionId", "instanceRegionId");
        input.put("cenId", "cenId");
        input.put("regionId", "regionId");
        cloudTemplate.getVariables().putAll(input);
        CloudAction list = cloudTemplate.getCloudAction("attach");
        ActionParser.parse(list);
    }
}
