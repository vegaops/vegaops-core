package org.prophetech.hyperone.vegaops.aws.client;

import lombok.extern.slf4j.Slf4j;
import org.prophetech.hyperone.vegaops.aws.model.CreateEipParam;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.AllocateAddressRequest;
import software.amazon.awssdk.services.ec2.model.AllocateAddressResponse;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;

@Slf4j(topic = "vegaops")
public class AwsEipClient {

    public AllocateAddressResponse createEip(Ec2Client ec2, CreateEipParam createEipParam ){
        try {
            AllocateAddressRequest request = AllocateAddressRequest.builder().domain(createEipParam.getDomainType()).publicIpv4Pool(createEipParam.getPublicIpv4Pool()).networkBorderGroup(createEipParam.getNetworkBorderGroup()).build();
            AllocateAddressResponse createAddressResponse = ec2.allocateAddress(request);
            ec2.close();
            return createAddressResponse;
        } catch (Ec2Exception e) {
            e.getStackTrace();
        }
        return null;
    }
}
