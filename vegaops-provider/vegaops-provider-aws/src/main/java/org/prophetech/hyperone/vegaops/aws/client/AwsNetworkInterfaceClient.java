package org.prophetech.hyperone.vegaops.aws.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

@Slf4j(topic = "vegaops")
public class AwsNetworkInterfaceClient {

    public NetworkInterface getNetworkInterface(Ec2Client ec2, String networkInterfaceId) {
        if (StringUtils.isEmpty(networkInterfaceId)) {
            throw new RuntimeException("networkInterfaceId is empty");
        }
        DescribeNetworkInterfacesRequest describeNetworkInterfacesRequest = DescribeNetworkInterfacesRequest.builder()
                .networkInterfaceIds(networkInterfaceId).build();
        DescribeNetworkInterfacesResponse netResponse = ec2.describeNetworkInterfaces(describeNetworkInterfacesRequest);
        if (!netResponse.sdkHttpResponse().isSuccessful()
                || CollectionUtils.isEmpty(netResponse.networkInterfaces())) {
            throw new RuntimeException("networkInterface does not exist");
        }
        return netResponse.networkInterfaces().get(0);
    }

    public DetachNetworkInterfaceResponse detachNetworkInterface(Ec2Client ec2, String networkInterfaceId) {
        String attachmentId = getNetworkInterface(ec2, networkInterfaceId).attachment().attachmentId();
        if (StringUtils.isEmpty(attachmentId)) {
            throw new RuntimeException("attachmentId is empty");
        }
        DetachNetworkInterfaceRequest describeNetworkInterfacesRequest = DetachNetworkInterfaceRequest.builder()
                .attachmentId(attachmentId).build();
        DetachNetworkInterfaceResponse netResponse = ec2.detachNetworkInterface(describeNetworkInterfacesRequest);
        if (!netResponse.sdkHttpResponse().isSuccessful()
               ) {
            throw new RuntimeException("networkInterface is detached");
        }
        return netResponse;
    }

}
