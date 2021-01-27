package org.prophetech.hyperone;

import com.alibaba.fastjson.JSON;
import org.springframework.util.FileCopyUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) throws Exception {
        String[] jsons=new String[]{
                "E:\\prophetech\\vegaops\\vegaops-provider\\vegaops-provider-aliyun\\src\\main\\resources\\provider\\aliyun\\1.0\\template",
                "E:\\prophetech\\vegaops\\vegaops-provider\\vegaops-provider-aws\\src\\main\\resources\\provider\\aws\\1.0\\template",
                "E:\\prophetech\\vegaops\\vegaops-provider\\vegaops-provider-ctyun\\src\\main\\resources\\provider\\ctyun\\1.0\\template",
                "E:\\prophetech\\vegaops\\vegaops-provider\\vegaops-provider-tencent\\src\\main\\resources\\provider\\tencent\\1.0\\template"
        };
        for(String json:jsons){
            File dir=new File(json);
            File[] files = dir.listFiles((f, n) -> n.endsWith("json"));
            for(File file:files){
                HashMap map = JSON.parseObject(new FileInputStream(file), LinkedHashMap.class);
                String dump = new Yaml().dumpAsMap(map);
                FileCopyUtils.copy(dump.getBytes(),new File(file.getPath().replace(".json",".yaml")));
            }
        }
    }
}
