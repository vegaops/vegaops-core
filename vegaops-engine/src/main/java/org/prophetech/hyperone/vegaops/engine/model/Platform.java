package org.prophetech.hyperone.vegaops.engine.model;

import org.apache.commons.lang3.StringUtils;

public enum Platform {

    ALIYUN("aliyun", "阿里云","vegaops","1"),
    TENCENT("tencent", "腾讯云","vegaops","2"),
    AWS("aws", "aws","vegaops", "3"),
    HUAWEI("huawei", "华为云","terraform", "4"),
    AZURE("azure", "Azure","terraform", "5"),
    CTYUN("ctyun", "天翼云","vegaops", "6");

    private String code;
    private String name;
    private String engine;
    private String no;

    Platform(String code, String name,String engine, String no) {
        this.code = code;
        this.name = name;
        this.engine = engine;
        this.no = no;
    }

    public static Platform getPlatform(String code) {
        for (Platform platform : Platform.values()) {
            if (platform.getCode().equals(code)||platform.getNo().equals(code)) {
                return platform;
            }
        }
        return null;
    }

    public static String getCodeByNo(String no) {
        for (Platform platform : Platform.values()) {
            if (platform.getNo().equals(no)) {
                return platform.getCode();
            }
        }
        return null;
    }

    public static void checkPlatform(String vendor) {
        if (StringUtils.isEmpty(vendor)) {
            throw new RuntimeException("vendor.not.null");
        }
        if (getPlatform(vendor) == null) {
            throw new RuntimeException("vendor.invalid");
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}
