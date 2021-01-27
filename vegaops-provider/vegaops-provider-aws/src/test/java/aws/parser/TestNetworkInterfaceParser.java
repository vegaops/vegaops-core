package aws.parser;

import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.parser.ActionParser;
import software.amazon.awssdk.regions.Region;

import java.util.HashMap;
import java.util.Map;

public class TestNetworkInterfaceParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate(){
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("aws","1.0",  "networkInterface");
        cloudTemplate.setComponentId("1234567890");
        Map input=new HashMap();
        input.put("accessKey","xxxxx");
        input.put("secret","xxxxx");
        input.put("regionId", Region.CN_NORTH_1.toString());
        cloudTemplate.inputVars(input);
        return cloudTemplate;
    }
    @Test
    @SneakyThrows
    public void parseCreateNetworkInterface(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("vswitchId","subnet-03b2601b9232e86f7");
        input.put("description","xjhtest");
        cloudTemplate.getVariables().putAll(input);
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
        Map input=new HashMap();
        input.put("networkInterfaceId","eni-04cff1b2afdc987d3");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("query");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parseDeleteNetworkInterface(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("networkInterfaceId","eni-005498c2c21f53fd6");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("uninstall");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parseBindNetworkInterface(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("networkInterfaceId","eni-0c47602e680322a45");
        input.put("instanceId","i-0222dd9973b1a27e6");
        input.put("deviceIndex","1");
        cloudTemplate.getVariables().putAll(input);
        CloudAction bind = cloudTemplate.getCloudAction("bind");
        ActionParser.parse(bind);
    }

    @Test
    @SneakyThrows
    public void parseUnBindNetworkInterface(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("networkInterfaceId","eni-04cff1b2afdc987d3");
        cloudTemplate.getVariables().putAll(input);
        CloudAction unbind = cloudTemplate.getCloudAction("unbind");
        ActionParser.parse(unbind);
    }
}
