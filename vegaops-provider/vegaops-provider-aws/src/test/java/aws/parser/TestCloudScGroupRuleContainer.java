package aws.parser;

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

public class TestCloudScGroupRuleContainer {
    @SneakyThrows
    @Test
    public void testInstall() {
        InputStream inputStream = FileUtils.getResourceAsStream("parser/vegaopsScGroupRuleContainer.json");
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
        String json="{\"vendor\":\"aws\",\"containerOutput\":{\"securityGroupRule-1\":{\"success\":true,\"protocol\":\"tcp\",\"portStart\":\"2\",\"portEnd\":\"65535\",\"direction\":\"ingress\",\"securityGroupId\":\"sg-0a46ea5d0001db50a\",\"retry\":false}},\"nodeMap\":{\"securityGroupRule-1\":{\"action\":\"install\",\"componentId\":\"securityGroupRule-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"securityGroupRule\",\"output\":{\"success\":true,\"protocol\":\"tcp\",\"portStart\":\"2\",\"portEnd\":\"65535\",\"direction\":\"ingress\",\"securityGroupId\":\"sg-0a46ea5d0001db50a\",\"retry\":false},\"vars\":{\"securityGroupId\":\"sg-0a46ea5d0001db50a\",\"protocol\":\"tcp\",\"portStart\":\"2\",\"ethertype\":\"IPv4\",\"portEnd\":\"65535\",\"direction\":\"ingress\"}}},\"nodes\":[{\"action\":\"install\",\"componentId\":\"securityGroupRule-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"securityGroupRule\",\"output\":{\"success\":true,\"protocol\":\"tcp\",\"portStart\":\"2\",\"portEnd\":\"65535\",\"direction\":\"ingress\",\"securityGroupId\":\"sg-0a46ea5d0001db50a\",\"retry\":false},\"vars\":{\"securityGroupId\":\"sg-0a46ea5d0001db50a\",\"protocol\":\"tcp\",\"portStart\":\"2\",\"ethertype\":\"IPv4\",\"portEnd\":\"65535\",\"direction\":\"ingress\"}}],\"resolveArrangement\":[\"securityGroupRule-1\"],\"resolvedNodes\":[\"securityGroupRule-1\"],\"success\":true,\"unresolvedNodes\":[],\"variables\":{\"accessKey\":\"xxxxx\",\"secret\":\"oH/PCT/XT+Vh1++xxxxx+zuSQ\",\"regionId\":\"cn-north-1\",\"zoneId\":\"cnn1-az1\"}}";
        CloudContainer container =JSON.parseObject(json,CloudContainer.class);
        ContainerParser.uninstall(container);
        System.out.println(container.getContainerOutput());
    }
}
