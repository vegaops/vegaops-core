package aliyun.parser;

import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.parser.ActionParser;

import java.util.HashMap;
import java.util.Map;

public class TestKeyPairParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate(){
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("aliyun","1.0",  "keyPair");
        cloudTemplate.setComponentId("1234567890");
        Map input=new HashMap();
        input.put("accessKey","xxxxx");
        input.put("secret","xxxxx");
        input.put("regionId", "cn-hangzhou");
        cloudTemplate.inputVars(input);
        return cloudTemplate;
    }

    @Test
    @SneakyThrows
    public void parseCreateKeyPair(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("name","vegaopsTest");
        CloudAction install = cloudTemplate.getCloudAction("install");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parseGetKeyPairList(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        CloudAction list = cloudTemplate.getCloudAction("list");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseGetKeyPair(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("name","vegaopsTest");
        CloudAction query = cloudTemplate.getCloudAction("query");
        ActionParser.parse(query);
    }

    @Test
    @SneakyThrows
    public void parseDeleteKeyPair(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("name","vegaopsTest");
        CloudAction uninstall = cloudTemplate.getCloudAction("uninstall");
        ActionParser.parse(uninstall);
    }

    @Test
    @SneakyThrows
    public void parseBindKeyPair(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("instanceId","i-bp187wk4nytbrlufpkep");
        cloudTemplate.getVariables().put("name","vegaopsTest");
        CloudAction bind = cloudTemplate.getCloudAction("bind");
        ActionParser.parse(bind);
    }

    @Test
    @SneakyThrows
    public void parseunBindKeyPair(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("name","vegaopsTest");
        cloudTemplate.getVariables().put("instanceId","vegaopsTest");
        CloudAction unbind = cloudTemplate.getCloudAction("unbind");
        ActionParser.parse(unbind);
    }

}
