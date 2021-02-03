package org.prophetech.hyperone.vegaops.engine.bean;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhouhao
 * @since 3.0.0-RC
 */
public interface ToStringOperator<T> {

    default String toString(T target, String... ignoreProperty) {
        return toString(target, -1, ignoreProperty == null ? new HashSet<>() : new HashSet<>(Arrays.asList(ignoreProperty)));
    }

    String toString(T target, long features, Set<String> ignoreProperty);
}
