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

public class TestDirectConnectParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate() {
        CloudTemplate cloudTemplate = CloudTemplateFactory
                .getTemplate(Platform.AWS.getCode(),"1.0","directConnect");
        cloudTemplate.setComponentId("0123456789");
        Map input = new HashMap();
        input.put("accessKey", "xxxxx");
        input.put("secret", "oH/PCT/XT+Vh1++xxxxx+zuSQ");
        input.put("regionId", "cn-northwest-1");
        cloudTemplate.inputVars(input);
        return cloudTemplate;
    }


    @Test
    @SneakyThrows
    public void parseGetSecurityGroupList() {
        CloudTemplate cloudTemplate = getCloudTemplate();
        Map input = new HashMap();
        cloudTemplate.getVariables().putAll(input);
        CloudAction list = cloudTemplate.getCloudAction("list");
        ActionParser.parse(list);
    }


}
