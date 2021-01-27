package aliyun.parser;

import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.parser.ActionParser;

import java.util.HashMap;
import java.util.Map;

public class TestCenInstanceParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate(){
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("aliyun","1.0", "networkCenInstance");
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
    public void parseAttachCen(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("cenId","cen-g8excp4rt31u5inh5g");
        cloudTemplate.getVariables().put("instanceId","vbr-2zepp6fxe0v7c5jdkutbf");
        cloudTemplate.getVariables().put("resourceType","VBR");
        cloudTemplate.getVariables().put("instanceRegionId","cn-beijing");
        CloudAction install = cloudTemplate.getCloudAction("attach");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parseGetCenInstanceList(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("cenId","cen-g8excp4rt31u5inh5g");
        CloudAction list = cloudTemplate.getCloudAction("list");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseGetCen(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("cenId","cen-g8excp4rt31u5inh5g");
        cloudTemplate.getVariables().put("instanceId","vbr-2zepp6fxe0v7c5jdkutbf");
        cloudTemplate.getVariables().put("resourceType","VBR");
        cloudTemplate.getVariables().put("instanceRegionId","cn-beijing");
        CloudAction query = cloudTemplate.getCloudAction("query");
        ActionParser.parse(query);
    }

    @Test
    @SneakyThrows
    public void parseDetachCen(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("cenId","cen-g8excp4rt31u5inh5g");
        cloudTemplate.getVariables().put("instanceId","vbr-2zepp6fxe0v7c5jdkutbf");
        cloudTemplate.getVariables().put("resourceType","VBR");
        cloudTemplate.getVariables().put("instanceRegionId","cn-beijing");
        CloudAction uninstall = cloudTemplate.getCloudAction("detach");
        ActionParser.parse(uninstall);
    }

}
