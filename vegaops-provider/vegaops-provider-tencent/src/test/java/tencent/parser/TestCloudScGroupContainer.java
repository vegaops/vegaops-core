package tencent.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import lombok.SneakyThrows;
import org.prophetech.hyperone.vegaops.engine.utils.FileUtils;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.model.CloudContainer;
import org.prophetech.hyperone.vegaops.engine.parser.ContainerParser;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.util.LinkedHashMap;

public class TestCloudScGroupContainer {
    @SneakyThrows
    @Test
    public void testInstall() {
        InputStream inputStream = FileUtils.getResourceAsStream("parser/vegaopsScGroupContainer.json");
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
        String json="{\"vendor\":\"tencent\",\"containerOutput\":{\"securityGroup-1\":{\"success\":true,\"securityGroupId\":\"sg-4aj2w0zf\",\"providerId\":\"sg-4aj2w0zf\",\"securityGroupName\":\"10.0.0.0/16\",\"description\":\"vegaopsTest\",\"projectId\":\"0\",\"createTime\":\"2020-05-07 14:26:16\",\"retry\":false}},\"nodeMap\":{\"securityGroup-1\":{\"action\":\"install\",\"componentId\":\"securityGroup-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"securityGroup\",\"output\":{\"success\":true,\"securityGroupId\":\"sg-4aj2w0zf\",\"providerId\":\"sg-4aj2w0zf\",\"securityGroupName\":\"10.0.0.0/16\",\"description\":\"vegaopsTest\",\"projectId\":\"0\",\"createTime\":\"2020-05-07 14:26:16\",\"retry\":false},\"vars\":{\"description\":\"vegaopsTest\",\"groupName\":\"10.0.0.0/16\"}}},\"nodes\":[{\"action\":\"install\",\"componentId\":\"securityGroup-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"securityGroup\",\"output\":{\"success\":true,\"securityGroupId\":\"sg-4aj2w0zf\",\"providerId\":\"sg-4aj2w0zf\",\"securityGroupName\":\"10.0.0.0/16\",\"description\":\"vegaopsTest\",\"projectId\":\"0\",\"createTime\":\"2020-05-07 14:26:16\",\"retry\":false},\"vars\":{\"description\":\"vegaopsTest\",\"groupName\":\"10.0.0.0/16\"}}],\"resolveArrangement\":[\"securityGroup-1\"],\"resolvedNodes\":[\"securityGroup-1\"],\"success\":true,\"unresolvedNodes\":[],\"variables\":{\"accessKey\":\"xxxxx\",\"secret\":\"xxxxx\",\"regionId\":\"ap-beijing\"}}\n";
        CloudContainer container =JSON.parseObject(json,CloudContainer.class);
        ContainerParser.uninstall(container);
        System.out.println(container.getContainerOutput());
    }
}
