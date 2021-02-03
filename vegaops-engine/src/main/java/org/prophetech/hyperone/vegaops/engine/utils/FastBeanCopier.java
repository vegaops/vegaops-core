package org.prophetech.hyperone.vegaops.engine.utils;

import org.apache.commons.beanutils.BeanUtils;


public class FastBeanCopier {
    public static <T, S> T copy(S source, T target){
        try {
            BeanUtils.copyProperties(target,source);
        } catch (Throwable e) {
            throw new RuntimeException("copyProperties error!",e);
        }
        return target;
    }

    public static <T, S> T copy(S source, Class<T> target){
        try {
            return copy(source,target.newInstance());
        } catch (Throwable e) {
            throw new RuntimeException("copyProperties error!",e);
        }
    }
}
