package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.annotation.IgnoreParam;
import org.springframework.util.ReflectionUtils;

import java.util.Objects;
import java.util.TreeMap;


@Setter
@Getter
public abstract class CtyunRequest<T extends CtyunResponse> {

    protected CustomInfo customInfo;
    protected String body;

    public abstract String getUrl();

    public abstract Method getMethod();

    public abstract BodyType getBodyType();

    public abstract Class<T> getResponseClass();


    public void init() {

    }

    protected TreeMap<String, String> getIgnoreParamMap(String group) {
        TreeMap<String, String> paramMap = new TreeMap<>();
        ReflectionUtils.doWithLocalFields(this.getClass(), field -> {
            field.setAccessible(true);
            if (field.isAnnotationPresent(IgnoreParam.class)
            ) {
                IgnoreParam annotation = field.getAnnotation(IgnoreParam.class);
                Object value = field.get(this);
                if (value != null && Objects.equals(annotation.group(), group)) {
                    paramMap.put(field.getName(), value.toString());
                }
            }
        });

        return paramMap;
    }

    protected TreeMap<String, String> getIgnoreParamMap() {
        return getIgnoreParamMap("");
    }
}
