package tencent.parser;

import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.parser.ActionParser;

import java.util.HashMap;
import java.util.Map;

public class TestInstanceParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate(){
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("tencent","1.0", "instance");
        cloudTemplate.setComponentId("1234567890");
        Map input=new HashMap();
        input.put("accessKey","xxxxx");
        input.put("secret","xxxxx");
        input.put("regionId", "ap-beijing");
        cloudTemplate.inputVars(input);
        return cloudTemplate;
    }



    @Test
    @SneakyThrows
    public void parseGetInstanceList(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        CloudAction list = cloudTemplate.getCloudAction("list");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseGetInstanceDetail(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("instanceId","ins-0998vmeb");
        CloudAction list = cloudTemplate.getCloudAction("query");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseCreateInstance(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("zoneId","ap-beijing-1");
        cloudTemplate.getVariables().put("imageId","img-9qabwvbn");
        cloudTemplate.getVariables().put("instanceChargeType","POSTPAID_BY_HOUR");
        cloudTemplate.getVariables().put("password", "Fu12345678");
        cloudTemplate.getVariables().put("securityGroups", "sg-luqy3q2d");
        cloudTemplate.getVariables().put("flavorId", "SA1.SMALL1");
        cloudTemplate.getVariables().put("systemDiskSize", "60");
        cloudTemplate.getVariables().put("systemDiskCategory", "CLOUD_SSD");
        cloudTemplate.getVariables().put("vpcId", "vpc-6olfg4qu");
        cloudTemplate.getVariables().put("VSwitchId", "subnet-lwal3wwr");
        cloudTemplate.getVariables().put("tagValue", "xjhtest");
        CloudAction list = cloudTemplate.getCloudAction("install");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseUninstallInstance(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("instanceId", "ins-qvme6hah");
        CloudAction list = cloudTemplate.getCloudAction("uninstall");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void startInstance(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("instanceId", "ins-ntubkkdn");
        CloudAction list = cloudTemplate.getCloudAction("start");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void stopInstance(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("instanceId", "ins-ntubkkdn");
        CloudAction list = cloudTemplate.getCloudAction("stop");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void queryInstanceStatus(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("instanceId", "ins-fdpgqyw3");
        CloudAction list = cloudTemplate.getCloudAction("query");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void upgradeInstance(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("instanceId", "ins-6aim1p5z");
        cloudTemplate.getVariables().put("instanceType", "S1.LARGE4");
        CloudAction list = cloudTemplate.getCloudAction("update");
        ActionParser.parse(list);
    }
}
