package org.prophetech.hyperone.vegaops.ctyun.client;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.client.CtyunJsoupClient;
import org.prophetech.hyperone.vegaops.ctyun.exception.ClientException;
import org.prophetech.hyperone.vegaops.ctyun.exception.ServerException;
import org.prophetech.hyperone.vegaops.ctyun.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Setter
@Getter
public class CtyunImageClient {
    private CtyunAccount ctyunAccount;

    public List<GetImagesResponse> getAllImage(String regionId, String imageType) throws ServerException, ClientException {
        List<GetImagesResponse> responses = new ArrayList<>();
        if(imageType.equals("all")){
            responses.addAll(getImages(regionId, "gold"));
            responses.addAll(getImages(regionId, "private"));
        }else {
            responses.addAll(getImages(regionId, imageType));
        }
        return responses;
    }

    private List<GetImagesResponse> getImages(String regionId, String imageType) throws ServerException, ClientException {
        GetImagesRequest request = new GetImagesRequest();
        CtyunJsoupClient client = new CtyunJsoupClient();
        request.setRegionId(regionId);
        request.setImageType(imageType);
        client.setCtyunAccount(ctyunAccount);
        CtyunApiResponse response = client.getCtyunResponse(request);
        if (response.getStatusCode() == 800) {
            CtyunApiListResponse<GetImagesResponse> ctyunResponse = (CtyunApiListResponse<GetImagesResponse>) response;
            List<GetImagesResponse> responses = ctyunResponse.getReturnObj();
            return responses;
        }
        return Collections.EMPTY_LIST;

    }
}
