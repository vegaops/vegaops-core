package org.prophetech.hyperone.vegaops.ctyun.client;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.prophetech.hyperone.vegaops.ctyun.client.CtyunJsoupClient;
import org.prophetech.hyperone.vegaops.ctyun.exception.ClientException;
import org.prophetech.hyperone.vegaops.ctyun.exception.ServerException;
import org.prophetech.hyperone.vegaops.ctyun.model.*;

import java.util.List;

@Setter
@Getter
@Slf4j
public class CtyunFlavorClient {
    private CtyunAccount ctyunAccount;

    public List<GetFlavorsResponse> getAllFlavors(GetFlavorsRequest request) throws ServerException, ClientException {
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        CtyunResponse response =client.getCtyunResponse(request);
        if(response instanceof CtyunApiListResponse){
            CtyunApiListResponse<GetFlavorsResponse> ctyunResponse = (CtyunApiListResponse)response;
            if (ctyunResponse.getStatusCode() == 800) {
                List<GetFlavorsResponse> flavors = ctyunResponse.getReturnObj();
                flavors.forEach(flavor -> {
                    String[] gpu = flavor.getFlavorType().split("GPU");
                    if (gpu.length > 1) {
                        flavor.setGpuNum(flavor.getCpuNum());
                        flavor.setCpuNum("0");
                    }
                });
                return flavors;
            }
        }
        log.error("天翼云同步{}地域规格出错:{}", request.getRegionId(), JSON.toJSONString(response));
        throw new ClientException("天翼云同步地域规格出错");
    }

}
