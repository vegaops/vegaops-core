package org.prophetech.hyperone.vegaops.engine.utils;

import java.util.function.Supplier;

public class ObjectSupplier<T> implements Supplier<T> {
    private T object;

    public ObjectSupplier() {
    }

    public ObjectSupplier(T object) {
        this.object = object;
    }

    public void set(T object){
        this.object=object;
    }

    @Override
    public T get() {
        return object;
    }
}
