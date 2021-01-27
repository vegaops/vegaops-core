package parser;

import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.parser.ActionParser;

import java.util.HashMap;
import java.util.Map;

public class TestNetworkInterfaceParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate() {
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("ctyun","1.0", "networkInterface");
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
    public void parseGetNetworkInterfaceList() {
        CloudTemplate cloudTemplate = getCloudTemplate();
        Map input = new HashMap();
        input.put("regionId", "cn-gzT");
        input.put("instanceId", "59fc8180-c016-4eaf-96f0-3eed7d306fcf");
        cloudTemplate.getVariables().putAll(input);
        CloudAction list = cloudTemplate.getCloudAction("list");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void install() {
        CloudTemplate cloudTemplate = getCloudTemplate();
        Map input = new HashMap();
        input.put("regionId", "cn-gzT");
        input.put("instanceId", "5ea33e59-f5a7-4ebd-b409-b21aff3e6705");
        input.put("securityGroups", "1ae92871-e385-4a29-9db6-05413cdeffa2");
        input.put("vswitchId", "b94ecea6-2eae-4f93-9aa1-4dcda9ea9ac4");
        cloudTemplate.getVariables().putAll(input);
        CloudAction list = cloudTemplate.getCloudAction("install");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void uninstall() {
        CloudTemplate cloudTemplate = getCloudTemplate();
        Map input = new HashMap();
        input.put("regionId", "cn-gzT");
        input.put("instanceId", "ea5c357e-cf24-4464-ba50-2aac3fae8cc1");
        input.put("networkCardId", "4aa59f30-d557-4a02-9112-fd7770dee286");
        cloudTemplate.getVariables().putAll(input);
        CloudAction list = cloudTemplate.getCloudAction("uninstall");
        ActionParser.parse(list);
    }

}