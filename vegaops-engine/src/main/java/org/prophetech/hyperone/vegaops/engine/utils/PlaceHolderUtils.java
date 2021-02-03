package org.prophetech.hyperone.vegaops.engine.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class PlaceHolderUtils {

    public static Object getPlaceHolder(Object expObject, Object vars, Map... varMap) {
        if (expObject instanceof String) {
            String exp = (String) expObject;
            if (ELUtils.isSpelExpression(exp)) {
                try {
                    return ELUtils.getSpelValue(exp, vars, false,varMap);
                } catch (Throwable e) {
                    log.warn("SpringEL表达式{}运算出错,{}", exp, e.getLocalizedMessage());
                    return null;
                }
            }
        }
        return expObject;
    }
}
