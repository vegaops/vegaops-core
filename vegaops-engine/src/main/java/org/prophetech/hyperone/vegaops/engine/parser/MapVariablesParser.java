package org.prophetech.hyperone.vegaops.engine.parser;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.prophetech.hyperone.vegaops.engine.bean.FastBeanCopier;
import org.prophetech.hyperone.vegaops.engine.utils.ELUtils;
import org.prophetech.hyperone.vegaops.engine.utils.SecretMaskJsonSerializer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class MapVariablesParser {
    public static <T> T parser(Map source, Map<String, Object> variables) {
        if (log.isDebugEnabled()) {
            log.debug("parse sourceMap:{},variables:{}", SecretMaskJsonSerializer.toJSONString(source), SecretMaskJsonSerializer.toJSONString(variables));
        }
        Map dist = new LinkedHashMap<>();
        source.forEach((k, v) -> {
            Map<String, Object> vars = new LinkedHashMap<>(variables);
            vars.putAll(dist);
            if (v instanceof String && StringUtils.isNoneBlank((String) v)) {
                dist.put(k, getValue(((String) v).trim(),vars));
                return;
            } else if (v instanceof Map) {
                Map<String, Object> temp = new LinkedHashMap<>((Map<String, Object>) v);
                dist.put(k, parser(temp, vars));
                return;
            } else if(v instanceof List){
                List vList=(List)v;
                List result=new ArrayList();
                vList.forEach(l->{
                    if(l instanceof String){
                        result.add(getValue(((String) l).trim(),vars));
                    }else if(l instanceof Map){
                        Map<String, Object> temp = new LinkedHashMap<>((Map<String, Object>) l);
                        result.add(parser(temp, vars));
                    }
                });
                dist.put(k, result);
                return;
            }
            dist.put(k, v);
        });
        if (log.isDebugEnabled()) {
            log.debug("parse result:{}", SecretMaskJsonSerializer.toJSONString(dist));
        }
        return (T) mapToObject(dist);
    }
    private static Object getValue(String exp,Map vars){
        if(ELUtils.isSpelExpression(exp)){
            try {
                return ELUtils.getSpelValue(exp, vars);
            }catch (Throwable e){
                log.warn("SpringEL表达式{}运算出错,vars:{}",exp,SecretMaskJsonSerializer.toJSONString(vars));
            }
        }
        return exp;
    }

    private static Object mapToObject(Map source){
        String className = (String) source.get("class");
        if (className != null) {
            try {
                return  FastBeanCopier.copy(source, ClassUtils.getClass(className));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("找不到类:" + className, e);
            }
        }
        return source;
    }

}
