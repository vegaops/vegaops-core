package org.prophetech.hyperone.vegaops.engine.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Slf4j(topic = "vegaops")
public class CloudTemplateFactory {
    private static VegaopsConfig vegaopsConfig = new VegaopsConfig();
    private static final PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    private static Map<String, Map<String, Map>> templateMap = new LinkedHashMap<>();

    private static CloudNodeTemplate getCloudNodeTemplate(Resource resource) {
        Map nodes = new LinkedHashMap();
        try (InputStream inputStream = resource.getInputStream()) {
            String originPath = resource.getURL().getPath();
            String path = originPath;
            String flag = "/provider/";
            path = path.substring(path.lastIndexOf(flag) + flag.length(), path.length() - ("."+vegaopsConfig.getTemplateFormat()).length());
            String[] split = path.split("/");
            String vendor = split[0];
            String version = split[1];
            String nodeType = split[3];
            LinkedHashMap map;
            if(Objects.equals("json",vegaopsConfig.getTemplateFormat())){
                map  = JSON.parseObject(StreamUtils.copyToByteArray(inputStream), LinkedHashMap.class, Feature.OrderedField);
            }else {
                map  = new Yaml().loadAs(inputStream, LinkedHashMap.class);
            }
            nodes.putAll(map);
            nodes.put("vendor", vendor);
            nodes.put("nodeType", nodeType);
            nodes.put("path", originPath);
            return new CloudNodeTemplate(vendor, version, nodeType, nodes);
        } catch (IOException e) {
            throw new RuntimeException("读取云引擎模板文件出错", e);
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class CloudNodeTemplate {
        private String vendor;
        private String version;
        private String nodeType;
        private Map nodeTemplate;
    }

    private static Map getNodeTemplateMapFromCache(String vendor, String version, String nodeType) {
        Map<String, Map> cloudNodeMap = templateMap.get(vendor);
        if (cloudNodeMap == null) {
            synchronized (templateMap) {
                cloudNodeMap = new LinkedHashMap<>();
                templateMap.put(vendor, cloudNodeMap);
            }
        }
        Map node = cloudNodeMap.get(nodeType);
        if (node == null) {
            synchronized (templateMap) {
                node = getNodeTemplateMapNoCache(vendor, version, nodeType);
                cloudNodeMap.put(nodeType, node);
            }
        }
        return node;
    }

    private static Map getNodeTemplateMapNoCache(String vendor, String version, String nodeType) {
        Resource resource = resourcePatternResolver.getResource(
                "classpath:/provider/" + vendor + "/" + version + "/template/" + nodeType + "."+vegaopsConfig.getTemplateFormat());
        if (resource == null) {
            throw new RuntimeException("vendor:" + vendor + " template,version:" + version + ",nodeType:" + nodeType + " no exist");
        }
        Map nodeTemplate = getCloudNodeTemplate(resource).getNodeTemplate();
        return nodeTemplate;
    }

    private static Map getNodeTemplateMap(String vendor, String version, String nodeType, boolean cache) {
        return cache ? getNodeTemplateMapFromCache(vendor, version, nodeType) : getNodeTemplateMapNoCache(vendor, version, nodeType);
    }

    public static CloudTemplate getTemplate(String vendor, String version, String nodeType, Map vars) {
        boolean cache = getVegaopsConfig().getCacheTemplate();
        log.info("getTemplate vendor:{},version:{},nodeType:{},cache:{}", vendor, version, nodeType, cache);
        Map map = getNodeTemplateMap(vendor, version, nodeType, cache);
        Assert.notNull(map, "vendor:" + vendor + " template,nodeType:" + nodeType + " no exist");
        CloudTemplate cloudTemplate = new CloudTemplate();
        cloudTemplate.setVersion(version);
        Map temp = new LinkedHashMap(map);
        temp.putAll(vars);
        cloudTemplate.readFormMap(temp);
        return cloudTemplate;
    }

    public static CloudTemplate getTemplate(String vendor, String version, String nodeType) {
        return getTemplate(vendor, version, nodeType, Collections.EMPTY_MAP);
    }

    public static void cleanTemplateCache() {
        synchronized (templateMap) {
            templateMap.clear();
            log.info("vegaops templateMap cleared!");
        }
    }

    public static VegaopsConfig getVegaopsConfig() {
        return vegaopsConfig;
    }
}
