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

public class TestCloudVolumeContainer {
    @SneakyThrows
    @Test
    public void testInstall() {
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
        String json="{\"vendor\":\"aws\",\"containerOutput\":{\"volume-1\":{\"success\":true,\"size\":4,\"state\":\"CREATING\",\"volumeId\":\"vol-032f39571c163e4b6\",\"volumeType\":\"GP2\",\"attachments\":[],\"availabilityZone\":\"cn-north-1b\",\"iops\":100,\"snapshotId\":\"\",\"encrypted\":false,\"retry\":false}},\"nodeMap\":{\"volume-1\":{\"action\":\"install\",\"componentId\":\"volume-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"volume\",\"output\":{\"success\":true,\"size\":4,\"state\":\"CREATING\",\"volumeId\":\"vol-032f39571c163e4b6\",\"volumeType\":\"GP2\",\"attachments\":[],\"availabilityZone\":\"cn-north-1b\",\"iops\":100,\"snapshotId\":\"\",\"encrypted\":false,\"retry\":false},\"vars\":{\"volumeType\":\"gp2\",\"size\":\"4\",\"availabilityZone\":\"cn-north-1b\"}}},\"nodes\":[{\"action\":\"install\",\"componentId\":\"volume-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"volume\",\"output\":{\"success\":true,\"size\":4,\"state\":\"CREATING\",\"volumeId\":\"vol-032f39571c163e4b6\",\"volumeType\":\"GP2\",\"attachments\":[],\"availabilityZone\":\"cn-north-1b\",\"iops\":100,\"snapshotId\":\"\",\"encrypted\":false,\"retry\":false},\"vars\":{\"volumeType\":\"gp2\",\"size\":\"4\",\"availabilityZone\":\"cn-north-1b\"}}],\"resolveArrangement\":[\"volume-1\"],\"resolvedNodes\":[\"volume-1\"],\"success\":true,\"unresolvedNodes\":[],\"variables\":{\"accessKey\":\"xxxxx\",\"secret\":\"xxxxx\",\"regionId\":\"cn-north-1\",\"zoneId\":\"cnn1-az1\"}}";
        CloudContainer container =JSON.parseObject(json,CloudContainer.class);
        ContainerParser.uninstall(container);
        System.out.println(container.getContainerOutput());
    }
}
