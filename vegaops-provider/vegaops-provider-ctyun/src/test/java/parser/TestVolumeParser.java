package parser;

import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.parser.ActionParser;

import java.util.HashMap;
import java.util.Map;

public class TestVolumeParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate() {
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("ctyun","1.0", "volume");
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
    public void parseCreateVolume() {
        CloudTemplate cloudTemplate = getCloudTemplate();
        Map input = new HashMap();
        input.put("payType","PostPaid");
        input.put("regionId", "cn-gzT");
        input.put("zoneId", "cn-gzTa");
        input.put("name", "volume10000");
        input.put("type", "SATA");
        input.put("size", "10");
        input.put("count", "1");
        input.put("backupId", "");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("install");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parseGetVolumeList() {
        CloudTemplate cloudTemplate = getCloudTemplate();
        Map input = new HashMap();
        input.put("regionId", "cn-gzT");
        cloudTemplate.getVariables().putAll(input);
        CloudAction list = cloudTemplate.getCloudAction("list");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void query() {
        CloudTemplate cloudTemplate = getCloudTemplate();
        Map input = new HashMap();
        input.put("regionId", "cn-gzT");
        input.put("volumeId","22fd639f-9efb-40f3-90f5-5fe7763d9c09");
        cloudTemplate.getVariables().putAll(input);
        CloudAction list = cloudTemplate.getCloudAction("query");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseUnBindVolume() {
        CloudTemplate cloudTemplate = getCloudTemplate();
        Map input = new HashMap();
        input.put("regionId", "cn-gzT");
        input.put("volumeId", "9cbf24dd-2882-4de5-bc3b-fc3b7623b652");
        input.put("vmId", "5ea33e59-f5a7-4ebd-b409-b21aff3e6705");
        input.put("device", "/dev/sdb");
        input.put("payType","PostPaid");
        cloudTemplate.getVariables().putAll(input);
        CloudAction unBind = cloudTemplate.getCloudAction("unbind");
        ActionParser.parse(unBind);
    }

    @Test
    @SneakyThrows
    public void parseBindVolume() {
        CloudTemplate cloudTemplate = getCloudTemplate();
        Map input = new HashMap();
        input.put("regionId", "cn-gzT");
        input.put("volumeId", "9cbf24dd-2882-4de5-bc3b-fc3b7623b652");
        input.put("vmId", "66142af6-0bec-48e2-a7d4-c77ede2c5505");
        input.put("device", "/dev/sdb");
        input.put("payType","PostPaid");
        cloudTemplate.getVariables().putAll(input);
        CloudAction bind = cloudTemplate.getCloudAction("bind");
        ActionParser.parse(bind);
    }

    @Test
    @SneakyThrows
    public void parseDeleteVolume() {
        CloudTemplate cloudTemplate = getCloudTemplate();
        Map input = new HashMap();
        input.put("regionId", "cn-gzT");
        input.put("volumeId", "fc6b3886-6e5e-4bcc-800d-9b55394b9947");
        cloudTemplate.getVariables().putAll(input);
        CloudAction uninstall = cloudTemplate.getCloudAction("uninstall");
        ActionParser.parse(uninstall);
    }
}
