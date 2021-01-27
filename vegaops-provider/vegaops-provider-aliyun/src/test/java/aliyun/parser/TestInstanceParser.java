package aliyun.parser;

import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.parser.ActionParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestInstanceParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate(){
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("aliyun","1.0", "instance");
        cloudTemplate.setComponentId("1234567890");
        Map input=new HashMap();
        //onePro云账号
        input.put("accessKey","xxx");
        input.put("secret","xxxx");
        input.put("regionId", "cn-qingdao");
        cloudTemplate.inputVars(input);
        return cloudTemplate;
    }

    @Test
    @SneakyThrows
    public void list(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        CloudAction install = cloudTemplate.getCloudAction("list");
        ActionParser.parse(install);
    }


    @Test
    @SneakyThrows
    public void query(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        List<String> aa = new ArrayList<>();
        input.put("instanceId","i-m5e65iwt6l83sv2xz2d2");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("query");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void create(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("instanceChargeType","PostPaid");
        input.put("imageId","centos_7_7_x64_20G_alibase_20200426.vhd");
        input.put("internetMaxBandwidthOut","1");
        input.put("VSwitchId","vsw-m5eq6ndluee3lldgaf5ir");
        input.put("flavorId","ecs.n1.tiny");
        input.put("systemDiskCategory","cloud_efficiency");
        input.put("systemDiskSize","40");
        input.put("internetChargeType","PayByTraffic");
        input.put("securityGroups","sg-m5e4k5dvd9o1bcd58hbt");
        input.put("password","199784Xjh.");
        input.put("name","xjhtests3");
        input.put("tagKey","vegaops");
        input.put("tagValue","centos_6_09_64_20G_alibase_20180326.vhd");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("install");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void uninstall(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("instanceId","i-m5e0lghgjbelk9xifr3b");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("uninstall");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void start(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("instanceId","i-bp1amfrl14214qp4odmb");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("start");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void stop(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("instanceId","i-m5e3o9dxs3cqz8zpeoey");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("stop");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void reboot(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        Map input=new HashMap();
        input.put("instanceId","i-bp1amfrl14214qp4odmb");
        cloudTemplate.getVariables().putAll(input);
        CloudAction install = cloudTemplate.getCloudAction("reboot");
        ActionParser.parse(install);
    }
}
