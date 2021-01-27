package aws.parser;

import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.model.Platform;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.parser.ActionParser;

import java.util.HashMap;
import java.util.Map;

public class TestEipParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate() {
        CloudTemplate cloudTemplate = CloudTemplateFactory
                .getTemplate(Platform.AWS.getCode(),"1.0", "eip");
        cloudTemplate.setComponentId("0123456789");
        Map input = new HashMap();
        input.put("accessKey", "xxxxx");
        input.put("secret", "xxxxx");
        input.put("regionId", "cn-north-1");
        cloudTemplate.inputVars(input);
        return cloudTemplate;
    }

    @Test
    @SneakyThrows
    public void parseCreateEip() {
        CloudTemplate cloudTemplate = getCloudTemplate();
        Map input = new HashMap();
        input.put("domainType", "vpc");
        input.put("publicIpv4Pool", "amazon");
        input.put("publicIpv4Pool", "cn-north-1");
        cloudTemplate.getVariables().putAll(input);
        CloudAction list = cloudTemplate.getCloudAction("install");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseGetEipList() {
        CloudTemplate cloudTemplate = getCloudTemplate();
        Map input = new HashMap();
        cloudTemplate.getVariables().putAll(input);
        CloudAction list = cloudTemplate.getCloudAction("list");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseGetEipt() {
        CloudTemplate cloudTemplate = getCloudTemplate();
        Map input = new HashMap();
        input.put("allocationId", "eipalloc-0f1fc562abba1831e");
        cloudTemplate.getVariables().putAll(input);
        CloudAction list = cloudTemplate.getCloudAction("query");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseDeleteEip() {
        CloudTemplate cloudTemplate = getCloudTemplate();
        Map input = new HashMap();
        input.put("allocationId", "eipalloc-0c2295655ab104259");
        cloudTemplate.getVariables().putAll(input);
        CloudAction list = cloudTemplate.getCloudAction("uninstall");
        ActionParser.parse(list);
    }




}
