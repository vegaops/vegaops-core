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

public class TestCloudNetworkInterfaceContainer {
    @SneakyThrows
    @Test
    public void testInstall() {
        InputStream inputStream = FileUtils.getResourceAsStream("parser/vegaopsNetworkInterfaceContainer.json");
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
        String json="{\"vendor\":\"tencent\",\"containerOutput\":{\"networkInterface-1\":{\"success\":true,\"networkInterfaceId\":\"eni-6uh6dimi\",\"vpcId\":\"vpc-1f0ziyua\",\"vswitchId\":\"subnet-8ni0c15d\",\"name\":\"vegaopsTest\",\"description\":\"\",\"macAddress\":\"20:90:6F:71:48:72\",\"zone\":\"\",\"createdTime\":\"\",\"privateIpAddressSet\":[{\"addressId\":\"\",\"description\":\"\",\"isWanIpBlocked\":false,\"primary\":true,\"privateIpAddress\":\"10.0.0.5\",\"publicIpAddress\":\"\",\"state\":\"PENDING\"}],\"retry\":false}},\"nodeMap\":{\"networkInterface-1\":{\"action\":\"install\",\"componentId\":\"networkInterface-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"networkInterface\",\"output\":{\"success\":true,\"networkInterfaceId\":\"eni-6uh6dimi\",\"vpcId\":\"vpc-1f0ziyua\",\"vswitchId\":\"subnet-8ni0c15d\",\"name\":\"vegaopsTest\",\"description\":\"\",\"macAddress\":\"20:90:6F:71:48:72\",\"zone\":\"\",\"createdTime\":\"\",\"privateIpAddressSet\":[{\"addressId\":\"\",\"description\":\"\",\"isWanIpBlocked\":false,\"primary\":true,\"privateIpAddress\":\"10.0.0.5\",\"publicIpAddress\":\"\",\"state\":\"PENDING\"}],\"retry\":false},\"vars\":{\"vswitchId\":\"subnet-8ni0c15d\",\"name\":\"vegaopsTest\",\"vpcId\":\"vpc-1f0ziyua\"}}},\"nodes\":[{\"action\":\"install\",\"componentId\":\"networkInterface-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"networkInterface\",\"output\":{\"success\":true,\"networkInterfaceId\":\"eni-6uh6dimi\",\"vpcId\":\"vpc-1f0ziyua\",\"vswitchId\":\"subnet-8ni0c15d\",\"name\":\"vegaopsTest\",\"description\":\"\",\"macAddress\":\"20:90:6F:71:48:72\",\"zone\":\"\",\"createdTime\":\"\",\"privateIpAddressSet\":[{\"addressId\":\"\",\"description\":\"\",\"isWanIpBlocked\":false,\"primary\":true,\"privateIpAddress\":\"10.0.0.5\",\"publicIpAddress\":\"\",\"state\":\"PENDING\"}],\"retry\":false},\"vars\":{\"vswitchId\":\"subnet-8ni0c15d\",\"name\":\"vegaopsTest\",\"vpcId\":\"vpc-1f0ziyua\"}}],\"resolveArrangement\":[\"networkInterface-1\"],\"resolvedNodes\":[\"networkInterface-1\"],\"success\":true,\"unresolvedNodes\":[],\"variables\":{\"accessKey\":\"xxxxx\",\"secret\":\"xxxxx\",\"regionId\":\"ap-beijing\"}}";
        CloudContainer container =JSON.parseObject(json,CloudContainer.class);
        ContainerParser.uninstall(container);
        System.out.println(container.getContainerOutput());
    }
}
