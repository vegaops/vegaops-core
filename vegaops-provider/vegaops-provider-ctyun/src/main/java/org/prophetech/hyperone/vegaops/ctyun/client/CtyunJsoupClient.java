package org.prophetech.hyperone.vegaops.ctyun.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.TypeReference;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.prophetech.hyperone.vegaops.ctyun.annotation.IgnoreParam;
import org.prophetech.hyperone.vegaops.ctyun.exception.ClientException;
import org.prophetech.hyperone.vegaops.ctyun.exception.ServerException;
import org.prophetech.hyperone.vegaops.ctyun.model.*;
import org.springframework.util.ReflectionUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

@Setter
@Getter
@Slf4j(topic = "vegaops")
public class CtyunJsoupClient {
    protected static String hosts = "http://api.ctyun.cn";
    protected static String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    private CtyunAccount ctyunAccount;
    private int timeout=30;


    public <T extends CtyunResponse> CtyunApiResponse<T> getCtyunResponse(CtyunRequest<T> request) throws ServerException, ClientException {
        String resultJSON=null;
        try {
            if (ctyunAccount == null) {
                throw new ClientException("ctyunAccount参数为空");
            }
            request.init();
            resultJSON = doAction(request);
            CtyunApiResponse<T> ctyunApiResponse;
            if (request.getBodyType() == BodyType.OBJECT) {
                ctyunApiResponse = JSON.parseObject(resultJSON, new TypeReference<CtyunApiObjectResponse<T>>(request.getResponseClass()) {
                });
            } else if (request.getBodyType() == BodyType.ARRAY) {
                ctyunApiResponse = JSON.parseObject(resultJSON, new TypeReference<CtyunApiListResponse<T>>(request.getResponseClass()) {
                });
            } else if (request.getBodyType() == BodyType.String) {
                ctyunApiResponse = JSON.parseObject(resultJSON, new TypeReference<CtyunApiStringResponse<T>>(request.getResponseClass()) {
                });
            } else throw new UnsupportedOperationException();

            if (!Objects.equals(ctyunApiResponse.getStatusCode(), 800)) {
                ctyunApiResponse = JSON.parseObject(resultJSON, CtyunApiErrorResponse.class);
            }
            ctyunApiResponse.setResultJSON(resultJSON);
            return ctyunApiResponse;
        } catch (Exception e) {
            if (resultJSON != null) {
                log.error("请求天翼云出错:request:{},response:{}" ,request.getBody(),resultJSON);
            }
            if (e instanceof IOException) {
                throw new ServerException(e);
            } else if (e instanceof JSONException) {
                throw new ClientException(e);
            } else {
                throw new ClientException(e);
            }
        }
    }

    private TreeMap<String, String> getParamMap(CtyunRequest request) {
        TreeMap<String, String> paramMap = new TreeMap<>();
        ReflectionUtils.doWithLocalFields(request.getClass(), field -> {
            field.setAccessible(true);
            if (!Modifier.isFinal(field.getModifiers())
                    && !Modifier.isStatic(field.getModifiers())
                    && !field.isAnnotationPresent(IgnoreParam.class)
            ) {
                Object value = field.get(request);
                if (value != null) {
                    paramMap.put(field.getName(), value.toString());
                }
            }
        });

        return paramMap;
    }

    private String doAction(CtyunRequest request) throws IOException {
        Method method = request.getMethod();
        String hostquery = request.getUrl();
        Map<String, String> param = getParamMap(request);
        String sDate = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.UK).format(new Date());
        String host = (hosts + hostquery);
        Connection connect = Jsoup.connect(host);
        connect.timeout(timeout * 1000);
        connect.ignoreContentType(true);
        connect.ignoreHttpErrors(true);
        connect.validateTLSCertificates(false);
        StringBuilder plan = new StringBuilder();
        StringBuilder body = new StringBuilder();
        param.forEach((k, v) -> {
            if (v != null) {
                if (method == Method.GET) {
                    connect.header(k, v);
                } else {
                    body.append(k).append("=").append(v).append("&");
                }
                plan.append(v).append("\n");
            }
        });
        if (body.length() > 0) {
            body.deleteCharAt(body.length() - 1);
        }
        String contentMD5 = toMD5Base64(plan.toString().trim());
        String string = getHMACString(contentMD5, sDate, hostquery);
        connect.header("accessKey", ctyunAccount.getAccessKey())
                .header("contentMD5", contentMD5)
                .header("requestDate", sDate)
                .header("hmac", calculateHMAC(ctyunAccount.getSecret(), string))
                .header("platform", "3");
        if (request.getCustomInfo() != null) {
            connect.header("customInfo", JSON.toJSONString(request.getCustomInfo()));
        }
        if (method == Method.GET) {
            connect.method(Connection.Method.GET);
        } else {
            connect.header("Content-Type", "application/x-www-form-urlencoded");
            connect.method(Connection.Method.POST);
            request.setBody(body.toString());
            connect.requestBody(request.getBody());
        }
        Connection.Response execute = connect.execute();
        String responseString = execute.body();
        if(execute.statusCode()!=200){
            log.warn("天翼云请求失败，状态：{}，返回信息:{}",execute.statusCode(),responseString);
        }
        return responseString;
    }


    /**
     * 将输入字符串转换为MD5
     *
     * @param input 目标字符串
     * @return MD5加密后的字符串
     */
    public static String toMD5Base64(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            String result = new String(Base64.getEncoder().encode(digest));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据HmacSHA1算法生成HMAC信息摘要
     *
     * @param secret 密钥
     * @param data   消息输入
     * @return 信息摘要
     */
    public static String calculateHMAC(String secret, String data) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), HMAC_SHA1_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(secretKeySpec);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            String result = new String(Base64.getEncoder().encode(rawHmac));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    private static String getHMACString(String contentMD5, String sDate, String hostquery) {
        return contentMD5 + "\n" + sDate + "\n" + hostquery;
    }
}
