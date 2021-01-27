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

public class TestVpcParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate(){
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("aws","1.0",  "vpc");
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
    public void parseCreateVpc(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("cidrBlock","172.167.0.0/24");
        //input.put("amazonProvidedIpv6CidrBlock",false);
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
        input.put("vpcId","vpc-e5a78181");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("query");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parseVpcToPublic(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("cidrBlock","vpc-e5a78181");
        input.put("routeTableId","vpc-e5a78181");
        input.put("availabilityZone","vpc-e5a78181");
        input.put("internetGatewayId","vpc-e5a78181");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("query");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parseDeleteVpc(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("vpcId","vpc-0fa36d6f17a396b13");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("uninstall");
        ActionParser.parse(install);
    }
}
