package parser;

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

public class TestCloudContainer {
    @SneakyThrows
    @Test
    public  void testInstall() {
        InputStream inputStream = FileUtils.getResourceAsStream("parser/vegaopsContainer.json");
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
        String json="{\"vendor\":\"ctyun\",\"containerOutput\":{\"vswitch-1\":{\"resVlanId\":\"0a059846-6cd0-4681-9eff-714d1ecf01dd\",\"primaryDns\":\"100.125.128.17\",\"success\":\"true\",\"vpcId\":\"bb8009c4-75a8-4296-a6be-592a8f8c7854\",\"name\":\"hyberbin-test\",\"cidr\":\"192.168.0.0/24\",\"secondaryDns\":\"114.114.114.114\",\"availabilityZone\":\"cn-gzTa\",\"gateway\":\"192.168.0.1\",\"status\":\"SUCCESS\"},\"vpc-1\":{\"name\":\"vegaopsTest\",\"cidr\":\"192.168.0.0/24\",\"success\":\"true\",\"vpcId\":\"bb8009c4-75a8-4296-a6be-592a8f8c7854\",\"status\":\"SUCCESS\"}},\"nodeMap\":{\"vswitch-1\":{\"action\":\"install\",\"componentId\":\"vswitch-1\",\"dependency\":[\"vpc-1\"],\"dependencyNodes\":[\"vpc-1\"],\"nodeType\":\"vswitch\",\"output\":{\"resVlanId\":\"0a059846-6cd0-4681-9eff-714d1ecf01dd\",\"primaryDns\":\"100.125.128.17\",\"success\":\"true\",\"vpcId\":\"bb8009c4-75a8-4296-a6be-592a8f8c7854\",\"name\":\"hyberbin-test\",\"cidr\":\"192.168.0.0/24\",\"secondaryDns\":\"114.114.114.114\",\"availabilityZone\":\"cn-gzTa\",\"gateway\":\"192.168.0.1\",\"status\":\"SUCCESS\"},\"vars\":{\"dhcpEnable\":\"true\",\"regionId\":\"cn-gzT\",\"vpcId\":\"bb8009c4-75a8-4296-a6be-592a8f8c7854\",\"name\":\"hyberbin-test\",\"zoneId\":\"cn-gzTa\",\"cidr\":\"192.168.0.0/24\",\"gatewayIp\":\"192.168.0.1\"}},\"vpc-1\":{\"action\":\"install\",\"componentId\":\"vpc-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"vpc\",\"output\":{\"name\":\"vegaopsTest\",\"cidr\":\"192.168.0.0/24\",\"success\":\"true\",\"vpcId\":\"bb8009c4-75a8-4296-a6be-592a8f8c7854\",\"status\":\"SUCCESS\"},\"vars\":{\"name\":\"vegaopsTest\",\"cidr\":\"192.168.0.0/24\",\"regionId\":\"cn-gzT\"}}},\"nodes\":[{\"action\":\"install\",\"componentId\":\"vpc-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"vpc\",\"output\":{\"name\":\"vegaopsTest\",\"cidr\":\"192.168.0.0/24\",\"success\":\"true\",\"vpcId\":\"bb8009c4-75a8-4296-a6be-592a8f8c7854\",\"status\":\"SUCCESS\"},\"vars\":{\"name\":\"vegaopsTest\",\"cidr\":\"192.168.0.0/24\",\"regionId\":\"cn-gzT\"}},{\"action\":\"install\",\"componentId\":\"vswitch-1\",\"dependency\":[\"vpc-1\"],\"dependencyNodes\":[\"vpc-1\"],\"nodeType\":\"vswitch\",\"output\":{\"resVlanId\":\"0a059846-6cd0-4681-9eff-714d1ecf01dd\",\"primaryDns\":\"100.125.128.17\",\"success\":\"true\",\"vpcId\":\"bb8009c4-75a8-4296-a6be-592a8f8c7854\",\"name\":\"hyberbin-test\",\"cidr\":\"192.168.0.0/24\",\"secondaryDns\":\"114.114.114.114\",\"availabilityZone\":\"cn-gzTa\",\"gateway\":\"192.168.0.1\",\"status\":\"SUCCESS\"},\"vars\":{\"dhcpEnable\":\"true\",\"regionId\":\"cn-gzT\",\"vpcId\":\"bb8009c4-75a8-4296-a6be-592a8f8c7854\",\"name\":\"hyberbin-test\",\"zoneId\":\"cn-gzTa\",\"cidr\":\"192.168.0.0/24\",\"gatewayIp\":\"192.168.0.1\"}}],\"resolveArrangement\":[\"vpc-1\",\"vswitch-1\"],\"resolvedNodes\":[\"vswitch-1\",\"vpc-1\"],\"success\":true,\"unresolvedNodes\":[],\"variables\":{\"zoneId\":\"cn-gzTa\",\"ctyunAccount\":{\"accessKey\":\"xxxxx\",\"secret\":\"xxxxx\"},\"regionId\":\"cn-gzT\"}}\n";
        CloudContainer container =JSON.parseObject(json,CloudContainer.class);
        ContainerParser.uninstall(container);
        System.out.println(container.getContainerOutput());
    }
}
