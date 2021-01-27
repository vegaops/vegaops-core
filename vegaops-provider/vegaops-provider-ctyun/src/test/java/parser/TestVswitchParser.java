package parser;

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
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("ctyun","1.0", "vswitch");
        Map input=new HashMap();
        input.put("accessKey","xxxxx");
        input.put("secret","xxxxx");
        input.put("regionId","cn-gzT");
        cloudTemplate.inputVars(input);
        cloudTemplate.setComponentId("1234567890");
        return cloudTemplate;
    }
    @Test
    @SneakyThrows
    public void parseCreateVswitch(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("regionId","cn-gzT");
        input.put("zoneId","cn-gzTa");
        input.put("name","fty-test");
        input.put("cidr","192.168.0.0/24");
        input.put("gatewayIp","192.168.0.1");
        input.put("dhcpEnable","true");
        input.put("vpcId","9c4a8288-43bd-4ebd-a3f0-1ec16ce5eb2d");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("install");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parseDeleteVswitch(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("regionId","cn-gzT");
        input.put("vpcId","3819d846-20bc-4159-bb7b-b434334707bd");
        input.put("vswitchId","b94ecea6-2eae-4f93-9aa1-4dcda9ea9ac4");
        cloudTemplate.getVariables().putAll(input);
        CloudAction uninstall = cloudTemplate.getCloudAction("uninstall");
        ActionParser.parse(uninstall);
    }

    @Test
    @SneakyThrows
    public void parseGetVswitchList(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("regionId","cn-gzT");
        cloudTemplate.getVariables().putAll(input);
        CloudAction list = cloudTemplate.getCloudAction("list");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseGetVswitch(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("vswitchId","65465a3d-54a6-4a94-a64a-aad6dea2b71d");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("query");
        ActionParser.parse(install);
    }

}
