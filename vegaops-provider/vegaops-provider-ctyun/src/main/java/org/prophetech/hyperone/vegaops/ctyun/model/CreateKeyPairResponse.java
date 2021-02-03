package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class CreateKeyPairResponse extends CtyunResponse {

    private CtyunKeyPair keypair;

    @Setter
    @Getter
    @NoArgsConstructor
    public static class CtyunKeyPair{

        private String name;

        private String public_key;

        private String user_id;

        private String type;

        private String private_key;

        private String fingerprint;
    }

}
