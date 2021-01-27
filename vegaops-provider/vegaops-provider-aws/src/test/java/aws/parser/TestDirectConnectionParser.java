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

public class TestDirectConnectionParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate(){
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("aws","1.0", "directConnectConnection");
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
    public void parseCreateDirectGateway(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("name","vegaopsTest");
        input.put("bandwidth","1Gbps");
        input.put("location","MoC2-CM");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("install");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parseConfirmDirectConnection(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("connectionId","dxcon-fh45k7c2");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("confirm");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parseGetDirectConnectionList(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        CloudAction list = cloudTemplate.getCloudAction("list");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseGetDirectGatewayLocation(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        CloudAction list = cloudTemplate.getCloudAction("location");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseDeleteDirectConnection(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("connectionId","dxcon-fh45k7c2");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("uninstall");
        ActionParser.parse(install);
    }
}
