package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;

import java.util.List;

@Setter
@Getter
public class GetKeyPairsResponse extends CtyunResponse {


    private List<Keypair> keypairs;

    @Setter
    @Getter
    @NoArgsConstructor
    public static class Keypair{
        /**
         * 秘钥信息详情
         */
        private KeypairDetail keypair;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class KeypairDetail{
        /**
         * 秘钥对应指纹信息
         */
        private String fingerprint;
        /**
         * 秘钥名称
         */
        private String name;
        /**
         * 密钥对应publicKey信息
         */
        private String public_key;
        /**
         * 类型
         */
        private String type;
    }

}
