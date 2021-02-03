package org.prophetech.hyperone.vegaops.ctyun.client;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.prophetech.hyperone.vegaops.ctyun.exception.ClientException;
import org.prophetech.hyperone.vegaops.ctyun.exception.ServerException;
import org.prophetech.hyperone.vegaops.ctyun.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Setter
@Getter
@Slf4j
public class CtyunRegionZoneClient {
    private CtyunAccount ctyunAccount;

    public List<CtyunRegion> getAllRegions() throws ServerException, ClientException {
        GetZoneConfigRequest request = new GetZoneConfigRequest();
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        CtyunApiResponse response = client.getCtyunResponse(request);
        if (response!=null&&response.getStatusCode() == 800) {
            CtyunApiListResponse<GetZoneConfigResponse> ctyunResponse = (CtyunApiListResponse<GetZoneConfigResponse>) response;
            List<GetZoneConfigResponse> responses = ctyunResponse.getReturnObj();
            Map<String, GetZoneConfigResponse> responseMap = responses.stream().collect(Collectors.toMap(r -> r.getRegionId(), v -> v, (o, o2) -> o));
            List<CtyunRegion> regions = new ArrayList<>();
            responseMap.forEach((k, v) -> {
                CtyunRegion region = new CtyunRegion();
                region.setVendor("ctyun");
                region.setDisplayName(v.getZoneName());
                region.setName(v.getZoneName());
                region.setProviderId(v.getRegionId());
                regions.add(region);
            });
            return regions;
        } else {
            throw new ClientException("天翼云查询AllRegions出错");
        }
    }

    public List<CtyunZone> getAllZones() throws ServerException, ClientException {
        GetZoneConfigRequest request = new GetZoneConfigRequest();
        CtyunJsoupClient client = new CtyunJsoupClient();
        client.setCtyunAccount(ctyunAccount);
        CtyunApiResponse response = client.getCtyunResponse(request);
        if (response!=null&&response.getStatusCode() == 800) {
            CtyunApiListResponse<GetZoneConfigResponse> ctyunResponse = (CtyunApiListResponse<GetZoneConfigResponse>) response;
            List<GetZoneConfigResponse> responses = ctyunResponse.getReturnObj();
            Map<String, GetZoneConfigResponse> responseMap = responses.stream().collect(Collectors.toMap(r -> r.getZoneId(), v -> v, (o, o2) -> o));
            List<CtyunZone> zones = new ArrayList<>();
            responseMap.forEach((k, v) -> {
                CtyunZone zone = new CtyunZone();
                zone.setVendor("ctyun");
                zone.setZoneRegionId(v.getRegionId());
                zone.setName(v.getZoneName());
                zone.setProviderId(v.getZoneId());
                zones.add(zone);
            });
            return zones;
        } else {
            throw new ClientException("天翼云查询AllZones出错");
        }
    }
}
