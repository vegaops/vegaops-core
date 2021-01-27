package parser;

import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.parser.ActionParser;

import java.util.HashMap;
import java.util.Map;

public class TestSecurityGroupRuleParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate() {
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("ctyun","1.0", "securityGroupRule");
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
    public void parseCreateSecurityGroupRule() {
        CloudTemplate cloudTemplate = getCloudTemplate();
        Map input = new HashMap();
        input.put("regionId", "cn-gzT");
        input.put("securityGroupId", "1ae92871-e385-4a29-9db6-05413cdeffa2");
        input.put("direction", "egress");
        input.put("ethertype", "IPv4");
        input.put("protocol", "tcp");
        input.put("portRangeMin", "");
        input.put("portRangeMax", "");
        input.put("remoteIpPrefix", "");
        input.put("remoteGroupId", "");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("install");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parseGetSecurityGroupRule() {
        CloudTemplate cloudTemplate = getCloudTemplate();
        Map input = new HashMap();
        input.put("regionId", "cn-gzT");
        input.put("securityGroupId", "4a23fb2a-0f21-4225-aa9f-1ac4e8c023fe");
        cloudTemplate.getVariables().putAll(input);
        CloudAction list = cloudTemplate.getCloudAction("list");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseGetSecurityGroupRules() {
        CloudTemplate cloudTemplate = getCloudTemplate();
        Map input = new HashMap();
        input.put("regionId", "cn-gzT");
//        input.put("securityGroupId", "cb9cef1e-384a-49af-a8ac-ff16fdacd3a6");
        cloudTemplate.getVariables().putAll(input);
        CloudAction list = cloudTemplate.getCloudAction("grouplist");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseDeleteSecurityGroupRule() {
        CloudTemplate cloudTemplate = getCloudTemplate();
        Map input = new HashMap();
        input.put("regionId", "cn-gzT");
        input.put("securityGroupRuleId", "14622f92-108f-49dd-98e0-f51ac0202f23");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("uninstall");
        ActionParser.parse(install);
    }
}
