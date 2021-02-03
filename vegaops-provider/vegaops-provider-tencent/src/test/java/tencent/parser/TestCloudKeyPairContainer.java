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

public class TestCloudKeyPairContainer {
    @SneakyThrows
    @Test
    public void testInstall() {
        InputStream inputStream = FileUtils.getResourceAsStream("parser/vegaopsKeyPairContainer.json");
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
        String json="{\"vendor\":\"tencent\",\"containerOutput\":{\"keyPair-1\":{\"success\":true,\"keyId\":\"skey-86unpfdr\",\"name\":\"vegaopsTest\",\"retry\":false}},\"nodeMap\":{\"keyPair-1\":{\"action\":\"install\",\"componentId\":\"keyPair-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"keyPair\",\"output\":{\"success\":true,\"keyId\":\"skey-86unpfdr\",\"name\":\"vegaopsTest\",\"retry\":false},\"vars\":{\"name\":\"vegaopsTest\"}}},\"nodes\":[{\"action\":\"install\",\"componentId\":\"keyPair-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"keyPair\",\"output\":{\"success\":true,\"keyId\":\"skey-86unpfdr\",\"name\":\"vegaopsTest\",\"retry\":false},\"vars\":{\"name\":\"vegaopsTest\"}}],\"resolveArrangement\":[\"keyPair-1\"],\"resolvedNodes\":[\"keyPair-1\"],\"success\":true,\"unresolvedNodes\":[],\"variables\":{\"accessKey\":\"xxxxx\",\"secret\":\"xxxxx\",\"regionId\":\"ap-beijing\"}}";
        CloudContainer container =JSON.parseObject(json,CloudContainer.class);
        ContainerParser.uninstall(container);
        System.out.println(container.getContainerOutput());
    }
}
