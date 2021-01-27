package parser;


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
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("ctyun","1.0", "keyPair");
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
    public void parseCreateSshs(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("name","xjhCloudTest211271wz");
        input.put("regionId","cn-gzT");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("install");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parseGetSshsList(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input = new HashMap();
        input.put("regionId", "cn-gzT");
        cloudTemplate.getVariables().putAll(input);
        CloudAction list = cloudTemplate.getCloudAction("list");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseDeleteSshs(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("regionId","cn-gzT");
        input.put("name","xjhCloudTest1");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("uninstall");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parsegetSshs(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("regionId","cn-gzT");
        input.put("name","xjhtest");
        cloudTemplate.getVariables().putAll(input);
        CloudAction query = cloudTemplate.getCloudAction("query");
        ActionParser.parse(query);
    }

}
