package parser;

import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.parser.ActionParser;

import java.util.HashMap;
import java.util.Map;

public class TestSlbListenerParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate(){
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("ctyun","1.0", "slbListener");
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
    public void list(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("load_balancer_id", "26036991-1f36-4a6b-8286-aa4c5a426b2a");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("list");
        ActionParser.parse(install);
    }

}
