package org.prophetech.hyperone.vegaops.tencent.util;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tencentcloudapi.common.AbstractModel;
import com.tencentcloudapi.vpc.v20170312.models.CreateSecurityGroupRequest;

public class RequestBuilder {

    public static void main(String[] args) {
        CreateSecurityGroupRequest request = RequestBuilder.build()
                .put("GroupName", "test")
                .put("GroupDescription", "test123")
                .build(CreateSecurityGroupRequest.class);
    }


    public static Request build() {
        return new Request();
    }

    public static final class Request {

        private JSONObject jsonObject;

        public Request() {
            this.jsonObject = new JSONObject();
        }

        public Request put(String key, Object value) {
            this.jsonObject.put(key, value);
            return this;
        }

        public Request json(String json) {
            this.jsonObject = JSONObject.parseObject(json);
            return this;
        }

        public <T extends AbstractModel> T build(Class<T> cls) {
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            return gson.fromJson(this.jsonObject.toJSONString(), cls);
        }
    }
}
