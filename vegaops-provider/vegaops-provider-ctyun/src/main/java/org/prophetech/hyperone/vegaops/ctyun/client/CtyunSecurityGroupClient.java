package org.prophetech.hyperone.vegaops.ctyun.client;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hswebframework.web.bean.FastBeanCopier;
import org.prophetech.hyperone.vegaops.ctyun.client.CtyunJsoupClient;
import org.prophetech.hyperone.vegaops.ctyun.exception.ClientException;
import org.prophetech.hyperone.vegaops.ctyun.exception.ServerException;
import org.prophetech.hyperone.vegaops.ctyun.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Setter
@Getter
@Slf4j(topic = "vegaops")
public class CtyunSecurityGroupClient {
    private CtyunAccount ctyunAccount;

    public List<GetSecurityGroupsResponse> getAllSecurityGroups(String regionId) throws ServerException, ClientException {
        List<GetSecurityGroupsResponse> responses = new ArrayList<>();
        responses.addAll(getSecurityGroups(regionId, ""));
        return responses;
    }

    public List<GetSecurityGroupsResponse> getSecurityGroups(String regionId, String vpcId ) throws ServerException, ClientException {
        GetSecurityGroupsRequest request = new GetSecurityGroupsRequest();
        CtyunJsoupClient client = new CtyunJsoupClient();
        request.setRegionId(regionId);
        request.setVpcId(vpcId);
        client.setCtyunAccount(ctyunAccount);
        CtyunApiResponse response = client.getCtyunResponse(request);
        if (response.getStatusCode() == 800) {
            CtyunApiListResponse<GetSecurityGroupsResponse> ctyunResponse = (CtyunApiListResponse<GetSecurityGroupsResponse>) response;
            List<GetSecurityGroupsResponse> responses = ctyunResponse.getReturnObj();
            return responses;
        }
        return Collections.EMPTY_LIST;

    }

    public GetSecurityGroupsResponse getSecurityGroupDetail(String securityGroupId, String regionId) throws ServerException, ClientException {
        GetSecurityGroupsResponse groupResponse  = new GetSecurityGroupsResponse();
        List<GetSecurityGroupsResponse> responses = new ArrayList<>();
        responses.addAll(getSecurityGroups(regionId, ""));
        for(GetSecurityGroupsResponse response :responses){
            if(securityGroupId.equals(response.getResSecurityGroupId())){
                FastBeanCopier.copy(response,groupResponse);
            }
        }
        return groupResponse;
    }


    public CreateSgResponse createSg(CreateSgRequest createSgRequest) throws ServerException, ClientException {
        CreateSgResponse response = new CreateSgResponse();
        CtyunJsoupClient client = new CtyunJsoupClient();
        CreateSecurityGroupRequest createSecurityGroupRequest = new CreateSecurityGroupRequest();
        createSecurityGroupRequest.setName(createSgRequest.getName());
        createSecurityGroupRequest.setRegionId(createSgRequest.getRegionId());
        createSecurityGroupRequest.setVpcId(createSgRequest.getVpcId());
        client.setCtyunAccount(ctyunAccount);
        CtyunApiResponse responses = client.getCtyunResponse(createSecurityGroupRequest);
        if (responses.getStatusCode() == 800){
            CtyunApiObjectResponse<CreateSecurityGroupResponse> ctyunApiObjectResponse = (CtyunApiObjectResponse<CreateSecurityGroupResponse>) responses;
            response.setDescription(ctyunApiObjectResponse.getReturnObj().getDescription());
            response.setId(ctyunApiObjectResponse.getReturnObj().getId());
            response.setName(ctyunApiObjectResponse.getReturnObj().getName());
            response.setVpcId(createSgRequest.getVpcId());
        }
        return response;
    }


}
