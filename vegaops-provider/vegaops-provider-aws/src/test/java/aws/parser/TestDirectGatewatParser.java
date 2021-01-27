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

public class TestDirectGatewatParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate(){
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("aws","1.0",  "directConnectGateway");
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
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("install");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parseGetDirectGatewayList(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        CloudAction list = cloudTemplate.getCloudAction("list");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseDirectGatewayVpc(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("vpcId","vpc-e5a78181");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("query");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parseDeleteDirectGateway(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("directConnectGatewayId","2484954e-4a7f-42c1-b006-9d833e06267c");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("uninstall");
        ActionParser.parse(install);
    }
}
