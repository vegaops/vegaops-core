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
        String json="{\"vendor\":\"aws\",\"containerOutput\":{\"keyPair-1\":{\"success\":true,\"name\":\"xjh-test\",\"keyName\":\"xjh-test\",\"providerId\":\"key-0b5ecfd51a57eeea6\",\"keyPairId\":\"key-0b5ecfd51a57eeea6\",\"keyFingerprint\":\"09:32:dc:ca:d8:32:bb:35:d2:c9:6c:0e:60:e8:d5:e3:f7:36:76:6b\",\"regionId\":\"\",\"zoneId\":\"\",\"retry\":false}},\"nodeMap\":{\"keyPair-1\":{\"action\":\"install\",\"componentId\":\"keyPair-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"keyPair\",\"output\":{\"success\":true,\"name\":\"xjh-test\",\"keyName\":\"xjh-test\",\"providerId\":\"key-0b5ecfd51a57eeea6\",\"keyPairId\":\"key-0b5ecfd51a57eeea6\",\"keyFingerprint\":\"09:32:dc:ca:d8:32:bb:35:d2:c9:6c:0e:60:e8:d5:e3:f7:36:76:6b\",\"regionId\":\"\",\"zoneId\":\"\",\"retry\":false},\"vars\":{\"keyName\":\"xjh-test\"}}},\"nodes\":[{\"action\":\"install\",\"componentId\":\"keyPair-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"keyPair\",\"output\":{\"success\":true,\"name\":\"xjh-test\",\"keyName\":\"xjh-test\",\"providerId\":\"key-0b5ecfd51a57eeea6\",\"keyPairId\":\"key-0b5ecfd51a57eeea6\",\"keyFingerprint\":\"09:32:dc:ca:d8:32:bb:35:d2:c9:6c:0e:60:e8:d5:e3:f7:36:76:6b\",\"regionId\":\"\",\"zoneId\":\"\",\"retry\":false},\"vars\":{\"keyName\":\"xjh-test\"}}],\"resolveArrangement\":[\"keyPair-1\"],\"resolvedNodes\":[\"keyPair-1\"],\"success\":true,\"unresolvedNodes\":[],\"variables\":{\"accessKey\":\"xxxxx\",\"secret\":\"oH/PCT/XT+Vh1++xxxxx+zuSQ\",\"regionId\":\"cn-north-1\",\"zoneId\":\"cnn1-az1\"}}";
        CloudContainer container =JSON.parseObject(json,CloudContainer.class);
        ContainerParser.uninstall(container);
        System.out.println(container.getContainerOutput());
    }
}
