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

public class TestCloudEipContainer {
    @SneakyThrows
    @Test
    public void testInstall() {
        InputStream inputStream = FileUtils.getResourceAsStream("parser/vegaopsEipContainer.json");
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
        String json="{\"vendor\":\"aws\",\"containerOutput\":{\"eip-1\":{\"success\":true,\"allocationId\":\"eipalloc-0e073b00153db9a16\",\"retry\":false}},\"nodeMap\":{\"eip-1\":{\"action\":\"install\",\"componentId\":\"eip-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"eip\",\"output\":{\"success\":true,\"allocationId\":\"eipalloc-0e073b00153db9a16\",\"retry\":false},\"vars\":{\"domainType\":\"vpc\"}}},\"nodes\":[{\"action\":\"install\",\"componentId\":\"eip-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"eip\",\"output\":{\"success\":true,\"allocationId\":\"eipalloc-0e073b00153db9a16\",\"retry\":false},\"vars\":{\"domainType\":\"vpc\"}}],\"resolveArrangement\":[\"eip-1\"],\"resolvedNodes\":[\"eip-1\"],\"success\":true,\"unresolvedNodes\":[],\"variables\":{\"accessKey\":\"xxxxx\",\"secret\":\"xxxxx\",\"regionId\":\"cn-north-1\",\"zoneId\":\"cnn1-az1\"}}";
        CloudContainer container =JSON.parseObject(json,CloudContainer.class);
        ContainerParser.uninstall(container);
        System.out.println(container.getContainerOutput());
    }
}
