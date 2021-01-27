package tencent.parser;

import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.parser.ActionParser;

import java.util.HashMap;
import java.util.Map;

public class TestNetworkInterfaceParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate(){
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("tencent","1.0", "networkInterface");
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
    public void parseCreateNetworkInterface(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("vpcId","vpc-1f0ziyua");
        cloudTemplate.getVariables().put("vswitchId","subnet-8ni0c15d");
        cloudTemplate.getVariables().put("name","xjh-test2");
        CloudAction install = cloudTemplate.getCloudAction("install");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parseGetNetworkInterfaceList(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        CloudAction list = cloudTemplate.getCloudAction("list");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseGetNetworkInterface(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("networkInterfaceId","eni-9c1fdg26");
        CloudAction query = cloudTemplate.getCloudAction("query");
        ActionParser.parse(query);
    }

    @Test
    @SneakyThrows
    public void parseDeleteNetworkInterface(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("networkInterfaceId","eni-qongekyo");
        CloudAction uninstall = cloudTemplate.getCloudAction("uninstall");
        ActionParser.parse(uninstall);
    }

    @Test
    @SneakyThrows
    public void bind(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("networkInterfaceId","eni-cqajdz2c");
        cloudTemplate.getVariables().put("instanceId","ins-f9ii5npj");
        CloudAction uninstall = cloudTemplate.getCloudAction("bind");
        ActionParser.parse(uninstall);
    }

    @Test
    @SneakyThrows
    public void unbind(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("networkInterfaceId","eni-cqajdz2c");
        cloudTemplate.getVariables().put("instanceId","ins-f9ii5npj");
        CloudAction unbind = cloudTemplate.getCloudAction("unbind");
        ActionParser.parse(unbind);
    }
}
