package tencent.parser;

import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.parser.ActionParser;

import java.util.HashMap;
import java.util.Map;

public class TestVswitchParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate(){
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("tencent","1.0", "vswitch");
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
    public void parseCreateVswitch(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("vpcId","vpc-gfmx67be");
        cloudTemplate.getVariables().put("name","xjh-test2");
        cloudTemplate.getVariables().put("cidrBlock","10.0.0.0/18");
        cloudTemplate.getVariables().put("zone","ap-beijing-1");
        CloudAction install = cloudTemplate.getCloudAction("install");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parseDeleteVswitch(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("vswitchId","subnet-4h3llcc1");
        CloudAction uninstall = cloudTemplate.getCloudAction("uninstall");
        ActionParser.parse(uninstall);
    }

    @Test
    @SneakyThrows
    public void parseGetVswitchList(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        CloudAction list = cloudTemplate.getCloudAction("list");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseGetVswitch(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("vswitchId","subnet-8ni0c15d");
        CloudAction query = cloudTemplate.getCloudAction("query");
        ActionParser.parse(query);
    }
}
