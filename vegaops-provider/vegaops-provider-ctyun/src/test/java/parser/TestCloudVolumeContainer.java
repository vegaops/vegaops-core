package parser;

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

public class TestCloudVolumeContainer {
    @SneakyThrows
    @Test
    public  void testInstall() {
        InputStream inputStream = FileUtils.getResourceAsStream("parser/vegaopsVolumeContainer.json");
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
        String json="{\"vendor\":\"ctyun\",\"containerOutput\":{\"volume-1\":{\"payType\":\"PostPaid\",\"size\":\"20\",\"regionId\":\"cn-gzT\",\"cycleCnt\":\"\",\"success\":\"true\",\"name\":\"vegaopsTets-2\",\"volumeId\":\"4f281720-ebbd-4b7a-93e0-13cf90a8c6c1\",\"zoneId\":\"cn-gzTa\",\"type\":\"SATA\"}},\"nodeMap\":{\"volume-1\":{\"action\":\"install\",\"componentId\":\"volume-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"volume\",\"output\":{\"payType\":\"PostPaid\",\"size\":\"20\",\"regionId\":\"cn-gzT\",\"cycleCnt\":\"\",\"success\":\"true\",\"name\":\"vegaopsTets-2\",\"volumeId\":\"4f281720-ebbd-4b7a-93e0-13cf90a8c6c1\",\"zoneId\":\"cn-gzTa\",\"type\":\"SATA\"},\"vars\":{\"payType\":\"PostPaid\",\"size\":\"20\",\"regionId\":\"cn-gzT\",\"name\":\"vegaopsTets-2\",\"count\":\"1\",\"zoneId\":\"cn-gzTa\",\"type\":\"SATA\"}}},\"nodes\":[{\"action\":\"install\",\"componentId\":\"volume-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"volume\",\"output\":{\"payType\":\"PostPaid\",\"size\":\"20\",\"regionId\":\"cn-gzT\",\"cycleCnt\":\"\",\"success\":\"true\",\"name\":\"vegaopsTets-2\",\"volumeId\":\"4f281720-ebbd-4b7a-93e0-13cf90a8c6c1\",\"zoneId\":\"cn-gzTa\",\"type\":\"SATA\"},\"vars\":{\"payType\":\"PostPaid\",\"size\":\"20\",\"regionId\":\"cn-gzT\",\"name\":\"vegaopsTets-2\",\"count\":\"1\",\"zoneId\":\"cn-gzTa\",\"type\":\"SATA\"}}],\"resolveArrangement\":[\"volume-1\"],\"resolvedNodes\":[\"volume-1\"],\"success\":true,\"unresolvedNodes\":[],\"variables\":{\"zoneId\":\"cn-gzTa\",\"ctyunAccount\":{\"accessKey\":\"xxxxx\",\"secret\":\"xxxxx\"},\"regionId\":\"cn-gzT\"}}";
        CloudContainer container =JSON.parseObject(json,CloudContainer.class);
        ContainerParser.uninstall(container);
        System.out.println(container.getContainerOutput());
    }
}
