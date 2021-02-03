package org.prophetech.hyperone.vegaops.engine.bean;

public interface BeanFactory {

    <T> T newInstance(Class<T> beanType);
}
