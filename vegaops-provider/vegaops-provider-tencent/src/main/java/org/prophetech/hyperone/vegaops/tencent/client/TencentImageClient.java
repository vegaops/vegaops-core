package org.prophetech.hyperone.vegaops.tencent.client;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.cvm.v20170312.CvmClient;
import com.tencentcloudapi.cvm.v20170312.models.DescribeImagesRequest;
import com.tencentcloudapi.cvm.v20170312.models.DescribeImagesResponse;
import com.tencentcloudapi.cvm.v20170312.models.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.prophetech.hyperone.vegaops.tencent.model.DescribeImagesParam;

@Getter
@Setter
@Slf4j(topic = "vegaops")
public class TencentImageClient {
    private String key;
    private String secret;

    public DescribeImagesResponse DescribeImage(DescribeImagesParam describeImagesParam) {
        try {
            Credential cred = new Credential(key, secret);
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cvm.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            CvmClient client = new CvmClient(cred, describeImagesParam.getRegionId(), clientProfile);
            DescribeImagesRequest req = new DescribeImagesRequest();
            if (StringUtils.isNotEmpty(describeImagesParam.getImageType())) {
                Filter[] filters1 = new Filter[1];
                Filter filter1 = new Filter();
                filter1.setName("image-type");
                String[] values1 = {describeImagesParam.getImageType()};
                filter1.setValues(values1);
                filters1[0] = filter1;
                req.setFilters(filters1);
            }
            req.setLimit(describeImagesParam.getLimit());
            req.setOffset(describeImagesParam.getOffset());
            DescribeImagesResponse resp = client.DescribeImages(req);
            return resp;
        } catch (TencentCloudSDKException e) {
            throw new RuntimeException("查询腾讯云镜像失败" + e);
        }
    }
}
