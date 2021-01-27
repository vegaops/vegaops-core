package tencent.parser;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudContainer;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.parser.ActionParser;
import org.prophetech.hyperone.vegaops.engine.parser.ContainerParser;

import java.util.HashMap;
import java.util.Map;

public class TestCloudScGroupRuleContainer {
    @SneakyThrows
    private CloudTemplate getCloudTemplate(){
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("tencent","1.0", "securityGroupRule");
        cloudTemplate.setComponentId("1234567890");
        Map input=new HashMap();
        input.put("accessKey","xxxxx");
        input.put("secret","xxxxx");
        input.put("regionId", "ap-beijing");
        cloudTemplate.inputVars(input);
        return cloudTemplate;
    }


    @SneakyThrows
    @Test
    public void testUninstall(){
        String json="{\"vendor\":\"tencent\",\"containerOutput\":{\"securityGroup-1\":{\"success\":true,\"securityGroupId\":\"sg-4aj2w0zf\",\"providerId\":\"sg-4aj2w0zf\",\"securityGroupName\":\"10.0.0.0/16\",\"description\":\"vegaopsTest\",\"projectId\":\"0\",\"createTime\":\"2020-05-07 14:26:16\",\"retry\":false}},\"nodeMap\":{\"securityGroup-1\":{\"action\":\"install\",\"componentId\":\"securityGroup-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"securityGroup\",\"output\":{\"success\":true,\"securityGroupId\":\"sg-4aj2w0zf\",\"providerId\":\"sg-4aj2w0zf\",\"securityGroupName\":\"10.0.0.0/16\",\"description\":\"vegaopsTest\",\"projectId\":\"0\",\"createTime\":\"2020-05-07 14:26:16\",\"retry\":false},\"vars\":{\"description\":\"vegaopsTest\",\"groupName\":\"10.0.0.0/16\"}}},\"nodes\":[{\"action\":\"install\",\"componentId\":\"securityGroup-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"securityGroup\",\"output\":{\"success\":true,\"securityGroupId\":\"sg-4aj2w0zf\",\"providerId\":\"sg-4aj2w0zf\",\"securityGroupName\":\"10.0.0.0/16\",\"description\":\"vegaopsTest\",\"projectId\":\"0\",\"createTime\":\"2020-05-07 14:26:16\",\"retry\":false},\"vars\":{\"description\":\"vegaopsTest\",\"groupName\":\"10.0.0.0/16\"}}],\"resolveArrangement\":[\"securityGroup-1\"],\"resolvedNodes\":[\"securityGroup-1\"],\"success\":true,\"unresolvedNodes\":[],\"variables\":{\"accessKey\":\"xxxxx\",\"secret\":\"xxxxx\",\"regionId\":\"ap-beijing\"}}\n";
        CloudContainer container =JSON.parseObject(json,CloudContainer.class);
        ContainerParser.uninstall(container);
        System.out.println(container.getContainerOutput());
    }

    @Test
    @SneakyThrows
    public void parseGetSgRuleList(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("group_id","sg-eqgq838z");
        CloudAction list = cloudTemplate.getCloudAction("list");
        ActionParser.parse(list);
    }
}
