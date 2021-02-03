package org.prophetech.hyperone.vegaops.engine.bean;

@FunctionalInterface
public interface Converter {
    <T> T convert(Object source, Class<T> targetClass, Class[] genericType);
}