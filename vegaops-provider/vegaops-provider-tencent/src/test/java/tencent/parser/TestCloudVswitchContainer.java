package tencent.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.model.CloudContainer;
import org.prophetech.hyperone.vegaops.engine.parser.ContainerParser;
import org.prophetech.hyperone.vegaops.engine.utils.FileUtils;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.util.LinkedHashMap;

public class TestCloudVswitchContainer {
    @SneakyThrows
    @Test
    public void testInstall() {
        InputStream inputStream = FileUtils.getResourceAsStream("parser/vegaopsVswitchContainer.json");
        String json = new String(FileCopyUtils.copyToByteArray(inputStream));
        LinkedHashMap source = JSON.parseObject(json, LinkedHashMap.class, Feature.OrderedField);
        CloudContainer container = new CloudContainer();
        container.readFormMap(source);
        ContainerParser.install(container);
        System.out.println(container.toJson());
    }

    @SneakyThrows
    @Test
    public void testUninstall(){
        String json="{\"vendor\":\"tencent\",\"containerOutput\":{\"vswitch-1\":{\"success\":true,\"vpcId\":\"vpc-83p4kdj8\",\"vswitchId\":\"subnet-jx139fo3\",\"name\":\"vegaopsTest\",\"cidrBlock\":\"10.0.0.0/18\",\"zone\":\"ap-beijing-1\",\"createdTime\":\"0000-00-00 00:00:00\",\"retry\":false}},\"nodeMap\":{\"vswitch-1\":{\"action\":\"install\",\"componentId\":\"vswitch-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"vswitch\",\"output\":{\"success\":true,\"vpcId\":\"vpc-83p4kdj8\",\"vswitchId\":\"subnet-jx139fo3\",\"name\":\"vegaopsTest\",\"cidrBlock\":\"10.0.0.0/18\",\"zone\":\"ap-beijing-1\",\"createdTime\":\"0000-00-00 00:00:00\",\"retry\":false},\"vars\":{\"name\":\"vegaopsTest\",\"zone\":\"ap-beijing-1\",\"vpcId\":\"vpc-83p4kdj8\",\"cidrBlock\":\"10.0.0.0/18\"}}},\"nodes\":[{\"action\":\"install\",\"componentId\":\"vswitch-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"vswitch\",\"output\":{\"success\":true,\"vpcId\":\"vpc-83p4kdj8\",\"vswitchId\":\"subnet-jx139fo3\",\"name\":\"vegaopsTest\",\"cidrBlock\":\"10.0.0.0/18\",\"zone\":\"ap-beijing-1\",\"createdTime\":\"0000-00-00 00:00:00\",\"retry\":false},\"vars\":{\"name\":\"vegaopsTest\",\"zone\":\"ap-beijing-1\",\"vpcId\":\"vpc-83p4kdj8\",\"cidrBlock\":\"10.0.0.0/18\"}}],\"resolveArrangement\":[\"vswitch-1\"],\"resolvedNodes\":[\"vswitch-1\"],\"success\":true,\"unresolvedNodes\":[],\"variables\":{\"accessKey\":\"xxxxx\",\"secret\":\"xxxxx\",\"regionId\":\"ap-beijing\"}}";
        CloudContainer container =JSON.parseObject(json,CloudContainer.class);
        ContainerParser.uninstall(container);
        System.out.println(container.getContainerOutput());
    }
}
