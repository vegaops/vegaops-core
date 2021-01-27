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

public class TestCloudImageContainer {
    @SneakyThrows
    @Test
    public void testInstall() {
        InputStream inputStream = FileUtils.getResourceAsStream("parser/vegaopsImageContainer.json");
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
        String json="{\"vendor\":\"aws\",\"containerOutput\":{\"image-1\":{\"success\":true,\"fpgaImageId\":\"ami-0b660dbc319bdd984\",\"retry\":false}},\"nodeMap\":{\"image-1\":{\"action\":\"install\",\"componentId\":\"image-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"image\",\"output\":{\"success\":true,\"fpgaImageId\":\"ami-0b660dbc319bdd984\",\"retry\":false},\"vars\":{\"name\":\"xjh-test\",\"description\":\"vegaopsTets\",\"instanceId\":\"i-03fda4760fcd07b8b\"}}},\"nodes\":[{\"action\":\"install\",\"componentId\":\"image-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"image\",\"output\":{\"success\":true,\"fpgaImageId\":\"ami-0b660dbc319bdd984\",\"retry\":false},\"vars\":{\"name\":\"xjh-test\",\"description\":\"vegaopsTets\",\"instanceId\":\"i-03fda4760fcd07b8b\"}}],\"resolveArrangement\":[\"image-1\"],\"resolvedNodes\":[\"image-1\"],\"success\":true,\"unresolvedNodes\":[],\"variables\":{\"accessKey\":\"xxxxx\",\"secret\":\"oH/PCT/XT+Vh1++xxxxx+zuSQ\",\"regionId\":\"cn-north-1\",\"zoneId\":\"cnn1-az1\"}}";
        CloudContainer container =JSON.parseObject(json,CloudContainer.class);
        ContainerParser.uninstall(container);
        System.out.println(container.getContainerOutput());
    }
}
