package tencent.parser;

import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.parser.ActionParser;

import java.util.HashMap;
import java.util.Map;

public class TestSlbParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate(){
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("tencent","1.0", "slb");
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
    public void parseGetSlbList(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        CloudAction list = cloudTemplate.getCloudAction("list");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseGetSlb(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("slbId", "lb-lbaslv1d");
        CloudAction list = cloudTemplate.getCloudAction("query");
        ActionParser.parse(list);
    }


    @Test
    @SneakyThrows
    public void parseCreateInstance() {
        CloudTemplate cloudTemplate = getCloudTemplate();
//        cloudTemplate.getVariables().put("bandwidthpkgSubType", "1");
        cloudTemplate.getVariables().put("internetChargeType", "TRAFFIC_POSTPAID_BY_HOUR ");
//        cloudTemplate.getVariables().put("internetMaxBandwidthOut", "10");
        cloudTemplate.getVariables().put("ipVersion", "IPV4");
        cloudTemplate.getVariables().put("name", "fty_test");
        cloudTemplate.getVariables().put("type", "INTERNAL");
        cloudTemplate.getVariables().put("goodsNum", "1");
//        cloudTemplate.getVariables().put("projectId", "1");
        cloudTemplate.getVariables().put("vswitchId", "subnet-rp6fc62d");
//        cloudTemplate.getVariables().put("vipIsp", "CMCC");
        cloudTemplate.getVariables().put("vpcId", "vpc-hdlbw0iq");
//        cloudTemplate.getVariables().put("zoneId", "ap-beijing-1");
        CloudAction list = cloudTemplate.getCloudAction("install");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void uninstall(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("slbId", "lb-hk4rhpkf");
        CloudAction list = cloudTemplate.getCloudAction("uninstall");
        ActionParser.parse(list);
    }
}
