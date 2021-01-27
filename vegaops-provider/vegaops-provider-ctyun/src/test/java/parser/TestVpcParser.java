package parser;

import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.parser.ActionParser;

import java.util.HashMap;
import java.util.Map;

public class TestVpcParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate(){
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("ctyun","1.0", "vpc");
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
    public void parseCreateVpc(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("name","vegaopsTest");
        input.put("cidr","192.168.0.0/24");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("install");
        ActionParser.parse(install);
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
    public void parseGetVpc(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("vpcId","3819d846-20bc-4159-bb7b-b434334707bd");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("query");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parseDeleteVpc(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("vpcId","3819d846-20bc-4159-bb7b-b434334707bd");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("uninstall");
        ActionParser.parse(install);
    }


    @Test
    @SneakyThrows
    public void parseFlow(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("vpcId","3819d846-20bc-4159-bb7b-b434334707bd");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("testFlow");
        ActionParser.parse(install);
    }
}
