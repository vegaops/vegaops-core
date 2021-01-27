package aws.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import lombok.SneakyThrows;
import org.hswebframework.utils.file.FileUtils;
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
        String json="{\"vendor\":\"aws\",\"containerOutput\":{\"securityGroup-1\":{\"success\":true,\"groupId\":\"sg-0e0abd432b570bec8\",\"retry\":false}},\"nodeMap\":{\"securityGroup-1\":{\"action\":\"install\",\"componentId\":\"securityGroup-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"securityGroup\",\"output\":{\"success\":true,\"groupId\":\"sg-0e0abd432b570bec8\",\"retry\":false},\"vars\":{\"groupName\":\"vegaops-Test\",\"groupDesc\":\"xjh-test\",\"vpcId\":\"vpc-0fa36d6f17a396b13\"}}},\"nodes\":[{\"action\":\"install\",\"componentId\":\"securityGroup-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"securityGroup\",\"output\":{\"success\":true,\"groupId\":\"sg-0e0abd432b570bec8\",\"retry\":false},\"vars\":{\"groupName\":\"vegaops-Test\",\"groupDesc\":\"xjh-test\",\"vpcId\":\"vpc-0fa36d6f17a396b13\"}}],\"resolveArrangement\":[\"securityGroup-1\"],\"resolvedNodes\":[\"securityGroup-1\"],\"success\":true,\"unresolvedNodes\":[],\"variables\":{\"accessKey\":\"xxxxx\",\"secret\":\"oH/PCT/XT+Vh1++xxxxx+zuSQ\",\"regionId\":\"cn-north-1\",\"zoneId\":\"cnn1-az1\"}}";
        CloudContainer container =JSON.parseObject(json,CloudContainer.class);
        ContainerParser.uninstall(container);
        System.out.println(container.getContainerOutput());
    }
}
