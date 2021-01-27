package parser;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.hswebframework.utils.file.FileUtils;
import org.junit.Test;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapVariablesParser {
    @Test
    @SneakyThrows
    public void parse() {
        InputStream inputStream = FileUtils.getResourceAsStream("parser/template.json");
        String json = new String(FileCopyUtils.copyToByteArray(inputStream));
        Map<String, Object> vars = new HashMap<>();
        vars.put("v", "hyb");
        Map parser = (Map) org.prophetech.hyperone.vegaops.engine.parser.MapVariablesParser.parser(JSON.parseObject(json, LinkedHashMap.class), vars);
        System.out.println(JSON.toJSONString(parser));
    }
}
