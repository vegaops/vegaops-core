package parser;

import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.parser.ActionParser;

import java.util.HashMap;
import java.util.Map;

public class TestSecurityGroupParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate() {
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("ctyun","1.0", "securityGroup");
        cloudTemplate.setComponentId("1234567890");
        Map input = new HashMap();
        input.put("accessKey", "xxxxx");
        input.put("secret", "xxxxx");
        input.put("regionId", "cn-gzT");
        cloudTemplate.inputVars(input);
        return cloudTemplate;
    }

    @Test
    @SneakyThrows
    public void parseCreateSecurityGroup() {
        CloudTemplate cloudTemplate = getCloudTemplate();
        Map input = new HashMap();
        input.put("name", "vegaopsTest9");
        input.put("regionId", "cn-gzT");
        input.put("vpcId", "3819d846-20bc-4159-bb7b-b434334707bd");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("install");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parseGetSecurityGroupList() {
        CloudTemplate cloudTemplate = getCloudTemplate();
        Map input = new HashMap();
        input.put("regionId", "cn-gzT");
//        input.put("vpcId", "3819d846-20bc-4159-bb7b-b434334707bd");
        cloudTemplate.getVariables().putAll(input);
        CloudAction list = cloudTemplate.getCloudAction("list");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseGetSecurityGroup() {
        CloudTemplate cloudTemplate = getCloudTemplate();
        Map input = new HashMap();
        input.put("regionId", "cn-gzT");
        input.put("securityGroupId", "5124e7e2-0bae-4956-b577-e062c4853d70");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("query");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parseDeleteSecurityGroup() {
        CloudTemplate cloudTemplate = getCloudTemplate();
        Map input = new HashMap();
        input.put("regionId", "cn-gzT");
        input.put("securityGroupId", "e2904ad9-0d5a-41d0-aaeb-1160d34f7c71");
        input.put("projectId", "");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("uninstall");
        ActionParser.parse(install);
    }
}
