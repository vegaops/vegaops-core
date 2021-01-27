package org.prophetech.hyperone.vegaops.engine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.engine.utils.ExceptionUtil;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InvokeResult {
    private Object result;
    private Throwable throwable;

    public void setThrowable(Throwable throwable) {
        if (throwable != null) {
            this.throwable = ExceptionUtil.unwrapThrowable(throwable);
        }
    }
}
