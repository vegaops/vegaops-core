package org.prophetech.hyperone.vegaops.ctyun.client;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.prophetech.hyperone.vegaops.ctyun.exception.ClientException;
import org.prophetech.hyperone.vegaops.ctyun.exception.ServerException;
import org.prophetech.hyperone.vegaops.ctyun.model.*;

import java.util.List;


@Setter
@Getter
@Slf4j(topic = "vegaops")
public class CtyunKeyPairClient {
    private CtyunAccount ctyunAccount;



    public List<GetKeyPairsResponse.Keypair> getKeyPairs(String regionId) throws ServerException, ClientException {
        GetKeyPairsRequest request = new GetKeyPairsRequest();
        CtyunJsoupClient client = new CtyunJsoupClient();
        request.setRegionId(regionId);
        client.setCtyunAccount(ctyunAccount);
        CtyunApiResponse response = client.getCtyunResponse(request);
        if (response.getStatusCode() == 800) {
            CtyunApiObjectResponse<GetKeyPairsResponse> ctyunResponse = (CtyunApiObjectResponse<GetKeyPairsResponse>) response;
            List<GetKeyPairsResponse.Keypair> responses = ctyunResponse.getReturnObj().getKeypairs();
            return responses;
        }
        throw new ClientException("天翼云查询KeyPair出错");
    }


    public GetKeyPairsResponse.Keypair getKeyPair(CreateKeytPairsParam param) throws ServerException, ClientException {
        List<GetKeyPairsResponse.Keypair> keypairs = getKeyPairs(param.getRegionId());
        for(GetKeyPairsResponse.Keypair keypair:keypairs){
            if(param.getName().equals(keypair.getKeypair().getName())){
                return keypair;
            }
        }
        throw new ClientException("天翼云创建NetWorkInterFace出错");
    }

}
