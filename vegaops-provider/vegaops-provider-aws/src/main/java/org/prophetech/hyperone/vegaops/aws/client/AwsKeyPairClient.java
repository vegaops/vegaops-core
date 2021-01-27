package org.prophetech.hyperone.vegaops.aws.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DeleteKeyPairRequest;
import software.amazon.awssdk.services.ec2.model.DeleteKeyPairResponse;

@Slf4j(topic = "vegaops")
public class AwsKeyPairClient {

    public DeleteKeyPairResponse deleteKeyPair(Ec2Client ec2, String keyName) {
        if (StringUtils.isEmpty(keyName)) {
            throw new RuntimeException("keyName is empty");
        }
        DeleteKeyPairRequest deleteKeyPairRequest = DeleteKeyPairRequest.builder()
                .keyName(keyName).build();
        DeleteKeyPairResponse keyPairResponse = ec2.deleteKeyPair(deleteKeyPairRequest);
        if (!keyPairResponse.sdkHttpResponse().isSuccessful()
        ) {
            throw new RuntimeException("keyPair does not exist");
        }
        ec2.close();
        return keyPairResponse;
    }
}
