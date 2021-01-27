package parser;

import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.parser.ActionParser;

import java.util.HashMap;
import java.util.Map;

public class TestEipParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate(){
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("ctyun","1.0", "eip");
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
    public void parseGetEipList(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("regionId","cn-gzT");
        cloudTemplate.getVariables().putAll(input);
        CloudAction list = cloudTemplate.getCloudAction("list");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void query(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("regionId","cn-gzT");
        input.put("publicIpId","b426300d-3e93-4418-a843-6602bb6658df");
        cloudTemplate.getVariables().putAll(input);
        CloudAction list = cloudTemplate.getCloudAction("query");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void unbind(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("regionId","cn-gzT");
        input.put("publicIpId","e5528855-457f-448f-aea6-05c5c91a1548");
        input.put("privateIp","192.168.1.18");
        cloudTemplate.getVariables().putAll(input);
        CloudAction list = cloudTemplate.getCloudAction("unbind");
        ActionParser.parse(list);
    }

}
