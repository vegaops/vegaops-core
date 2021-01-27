package tencent.parser;

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
        String json="{\"vendor\":\"tencent\",\"containerOutput\":{\"vpc-1\":{\"success\":true,\"VpcId\":\"vpc-rdiickn4\",\"vpcName\":\"vegaopsTest\",\"cidrBlock\":\"10.0.0.0/16\",\"createTime\":\"0000-00-00 00:00:00\",\"retry\":false}},\"nodeMap\":{\"vpc-1\":{\"action\":\"install\",\"componentId\":\"vpc-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"vpc\",\"output\":{\"success\":true,\"VpcId\":\"vpc-rdiickn4\",\"vpcName\":\"vegaopsTest\",\"cidrBlock\":\"10.0.0.0/16\",\"createTime\":\"0000-00-00 00:00:00\",\"retry\":false},\"vars\":{\"name\":\"vegaopsTest\",\"cidr\":\"10.0.0.0/16\"}}},\"nodes\":[{\"action\":\"install\",\"componentId\":\"vpc-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"vpc\",\"output\":{\"success\":true,\"VpcId\":\"vpc-rdiickn4\",\"vpcName\":\"vegaopsTest\",\"cidrBlock\":\"10.0.0.0/16\",\"createTime\":\"0000-00-00 00:00:00\",\"retry\":false},\"vars\":{\"name\":\"vegaopsTest\",\"cidr\":\"10.0.0.0/16\"}}],\"resolveArrangement\":[\"vpc-1\"],\"resolvedNodes\":[\"vpc-1\"],\"success\":true,\"unresolvedNodes\":[],\"variables\":{\"accessKey\":\"xxxxx\",\"secret\":\"xxxxx\",\"regionId\":\"ap-beijing\"}}";
        CloudContainer container =JSON.parseObject(json,CloudContainer.class);
        ContainerParser.uninstall(container);
        System.out.println(container.getContainerOutput());
    }
}
