package tencent.parser;

import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.parser.ActionParser;

import java.util.HashMap;
import java.util.Map;

public class TestCenParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate(){
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("tencent","1.0", "networkCen");
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
    public void parseGetVpcList(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        CloudAction list = cloudTemplate.getCloudAction("list");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void detail(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input  = new HashMap();
        input.put("cenId", "ccn-ec4t2g3z");
        cloudTemplate.getVariables().putAll(input);
        CloudAction list = cloudTemplate.getCloudAction("query");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void create(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input  = new HashMap();
        input.put("name", "vegaops_test");
        input.put("description", "vegaops_test_des");
        input.put("qosLevel", "AG");
        input.put("instanceChargeType", "PREPAID");
        input.put("bandwidthLimitType", "INTER_REGION_LIMIT");
        cloudTemplate.getVariables().putAll(input);
        CloudAction list = cloudTemplate.getCloudAction("install");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void uninstall(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input  = new HashMap();
        input.put("networkCenId", "ccn-ec4t2g3z");
        cloudTemplate.getVariables().putAll(input);
        CloudAction list = cloudTemplate.getCloudAction("uninstall");
        ActionParser.parse(list);
    }
}
