package tencent.parser;

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
    private CloudTemplate getCloudTemplate(){
        CloudTemplate cloudTemplate = CloudTemplateFactory
                .getTemplate(Platform.TENCENT.getCode(),"1.0", "eip");
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
    public void list(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        CloudAction list = cloudTemplate.getCloudAction("list");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void query(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("addressId","eip-g6xcrdbj");
        CloudAction query = cloudTemplate.getCloudAction("query");
        ActionParser.parse(query);
    }

    @Test
    @SneakyThrows
    public void modifyEip(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("addressId","eip-i5jcl10l");
        cloudTemplate.getVariables().put("name","xjhtest");
        CloudAction query = cloudTemplate.getCloudAction("modify");
        ActionParser.parse(query);
    }

    @Test
    @SneakyThrows
    public void install(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("internetChargeType","TRAFFIC_POSTPAID_BY_HOUR");
        cloudTemplate.getVariables().put("internetMaxBandwidthOut","1");
        cloudTemplate.getVariables().put("anycastZone","ANYCAST_ZONE_GLOBAL");
        cloudTemplate.getVariables().put("name","vegaopsTest");
        CloudAction query = cloudTemplate.getCloudAction("install");
        ActionParser.parse(query);
    }


    @Test
    @SneakyThrows
    public void task(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("taskId","97273289");
        CloudAction query = cloudTemplate.getCloudAction("task");
        ActionParser.parse(query);
    }

    @Test
    @SneakyThrows
    public void uninstall(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("eipId","eip-ahixbo93");
        CloudAction query = cloudTemplate.getCloudAction("uninstall");
        ActionParser.parse(query);
    }

    @Test
    @SneakyThrows
    public void bind(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("eipId","eip-gbpgeebt");
        cloudTemplate.getVariables().put("instanceId","ins-ntubkkdn");
        CloudAction bind = cloudTemplate.getCloudAction("bind");
        ActionParser.parse(bind);
    }

    @Test
    @SneakyThrows
    public void unbind(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("eipId","eip-gbpgeebt");
        cloudTemplate.getVariables().put("instanceId","ins-ntubkkdn");
        CloudAction unbind = cloudTemplate.getCloudAction("unbind");
        ActionParser.parse(unbind);
    }

}
