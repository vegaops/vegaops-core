package aws.parser;

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
        String json="{\"vendor\":\"aws\",\"containerOutput\":{\"networkInterface-1\":{\"success\":true,\"availabilityZone\":\"cn-north-1b\",\"description\":\"vegaopsTest\",\"macAddr\":\"06:69:66:fd:0e:5e\",\"networkInterfaceId\":\"eni-01ef22b5efefcce95\",\"vswitchId\":\"subnet-0b93b4c853b63c1db\",\"vpcId\":\"vpc-0fa36d6f17a396b13\",\"ipAddr\":\"172.167.0.10\",\"interfaceType\":\"INTERFACE\",\"retry\":false}},\"nodeMap\":{\"networkInterface-1\":{\"action\":\"install\",\"componentId\":\"networkInterface-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"networkInterface\",\"output\":{\"success\":true,\"availabilityZone\":\"cn-north-1b\",\"description\":\"vegaopsTest\",\"macAddr\":\"06:69:66:fd:0e:5e\",\"networkInterfaceId\":\"eni-01ef22b5efefcce95\",\"vswitchId\":\"subnet-0b93b4c853b63c1db\",\"vpcId\":\"vpc-0fa36d6f17a396b13\",\"ipAddr\":\"172.167.0.10\",\"interfaceType\":\"INTERFACE\",\"retry\":false},\"vars\":{\"subnetId\":\"subnet-0b93b4c853b63c1db\",\"description\":\"vegaopsTest\"}}},\"nodes\":[{\"action\":\"install\",\"componentId\":\"networkInterface-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"networkInterface\",\"output\":{\"success\":true,\"availabilityZone\":\"cn-north-1b\",\"description\":\"vegaopsTest\",\"macAddr\":\"06:69:66:fd:0e:5e\",\"networkInterfaceId\":\"eni-01ef22b5efefcce95\",\"vswitchId\":\"subnet-0b93b4c853b63c1db\",\"vpcId\":\"vpc-0fa36d6f17a396b13\",\"ipAddr\":\"172.167.0.10\",\"interfaceType\":\"INTERFACE\",\"retry\":false},\"vars\":{\"subnetId\":\"subnet-0b93b4c853b63c1db\",\"description\":\"vegaopsTest\"}}],\"resolveArrangement\":[\"networkInterface-1\"],\"resolvedNodes\":[\"networkInterface-1\"],\"success\":true,\"unresolvedNodes\":[],\"variables\":{\"accessKey\":\"xxxxx\",\"secret\":\"oH/PCT/XT+Vh1++xxxxx+zuSQ\",\"regionId\":\"cn-north-1\",\"zoneId\":\"cnn1-az1\"}}";
        CloudContainer container =JSON.parseObject(json,CloudContainer.class);
        ContainerParser.uninstall(container);
        System.out.println(container.getContainerOutput());
    }
}
