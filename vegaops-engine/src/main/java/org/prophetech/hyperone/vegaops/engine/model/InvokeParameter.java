package org.prophetech.hyperone.vegaops.engine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Method;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class InvokeParameter {
    private Method method;
    private Object[] objects;
}
