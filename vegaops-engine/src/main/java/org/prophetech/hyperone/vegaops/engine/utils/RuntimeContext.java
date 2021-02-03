package org.prophetech.hyperone.vegaops.engine.utils;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.engine.model.CloudContainer;

import java.util.List;

@Setter
@Getter
public class RuntimeContext {
    private static final ThreadLocal<RuntimeContext> RUNTIME_CONTEXT_THREAD_LOCAL=new ThreadLocal<>();
    private CloudContainer container;
    private List<String> runNodeList;

    public RuntimeContext(CloudContainer container, List<String> runNodeList) {
        this.container = container;
        this.runNodeList = runNodeList;
    }

    public static RuntimeContext getContext(){
        return RUNTIME_CONTEXT_THREAD_LOCAL.get();
    }

    public static void setContext(RuntimeContext context){
        RUNTIME_CONTEXT_THREAD_LOCAL.set(context);
    }

    public static void remove(){
        RUNTIME_CONTEXT_THREAD_LOCAL.remove();
    }

}
