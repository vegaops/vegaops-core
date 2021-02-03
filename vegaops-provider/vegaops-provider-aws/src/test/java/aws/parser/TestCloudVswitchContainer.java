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

public class TestCloudVswitchContainer {
    @SneakyThrows
    @Test
    public void testInstall() {
        InputStream inputStream = FileUtils.getResourceAsStream("parser/vegaopsVswitchContainer.json");
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
        String json="{\"vendor\":\"aws\",\"containerOutput\":{\"vswitch-1\":{\"success\":true,\"subnetId\":\"subnet-0e81dc09a60c254a1\",\"retry\":false,\"availabilityZone\":\"cn-north-1b\",\"availabilityZoneId\":\"cnn1-az2\",\"availableIpAddressCount\":59,\"defaultForAz\":false,\"mapPublicIpOnLaunch\":false,\"state\":\"AVAILABLE\",\"tags\":[],\"vpcId\":\"vpc-0fa36d6f17a396b13\",\"ownerId\":\"306750078988\",\"assignIpv6AddressOnCreation\":false,\"ipv6CidrBlockAssociationSet\":[],\"subnetArn\":\"arn:aws-cn:ec2:cn-north-1:306750078988:subnet/subnet-0e81dc09a60c254a1\"}},\"nodeMap\":{\"vswitch-1\":{\"action\":\"install\",\"componentId\":\"vswitch-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"vswitch\",\"output\":{\"success\":true,\"subnetId\":\"subnet-0e81dc09a60c254a1\",\"retry\":false,\"availabilityZone\":\"cn-north-1b\",\"availabilityZoneId\":\"cnn1-az2\",\"availableIpAddressCount\":59,\"defaultForAz\":false,\"mapPublicIpOnLaunch\":false,\"state\":\"AVAILABLE\",\"tags\":[],\"vpcId\":\"vpc-0fa36d6f17a396b13\",\"ownerId\":\"306750078988\",\"assignIpv6AddressOnCreation\":false,\"ipv6CidrBlockAssociationSet\":[],\"subnetArn\":\"arn:aws-cn:ec2:cn-north-1:306750078988:subnet/subnet-0e81dc09a60c254a1\"},\"vars\":{\"cidrBlock\":\"172.167.0.0/26\",\"vpcId\":\"vpc-0fa36d6f17a396b13\"}}},\"nodes\":[{\"action\":\"install\",\"componentId\":\"vswitch-1\",\"dependency\":[],\"dependencyNodes\":[],\"nodeType\":\"vswitch\",\"output\":{\"success\":true,\"subnetId\":\"subnet-0e81dc09a60c254a1\",\"retry\":false,\"availabilityZone\":\"cn-north-1b\",\"availabilityZoneId\":\"cnn1-az2\",\"availableIpAddressCount\":59,\"defaultForAz\":false,\"mapPublicIpOnLaunch\":false,\"state\":\"AVAILABLE\",\"tags\":[],\"vpcId\":\"vpc-0fa36d6f17a396b13\",\"ownerId\":\"306750078988\",\"assignIpv6AddressOnCreation\":false,\"ipv6CidrBlockAssociationSet\":[],\"subnetArn\":\"arn:aws-cn:ec2:cn-north-1:306750078988:subnet/subnet-0e81dc09a60c254a1\"},\"vars\":{\"cidrBlock\":\"172.167.0.0/26\",\"vpcId\":\"vpc-0fa36d6f17a396b13\"}}],\"resolveArrangement\":[\"vswitch-1\"],\"resolvedNodes\":[\"vswitch-1\"],\"success\":true,\"unresolvedNodes\":[],\"variables\":{\"accessKey\":\"xxxxx\",\"secret\":\"oH/PCT/XT+Vh1++xxxxx+zuSQ\",\"regionId\":\"cn-north-1\",\"zoneId\":\"cnn1-az1\"}}";
        CloudContainer container =JSON.parseObject(json,CloudContainer.class);
        ContainerParser.uninstall(container);
        System.out.println(container.getContainerOutput());
    }
}
