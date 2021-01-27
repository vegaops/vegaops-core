package org.prophetech.hyperone.vegaops.engine.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Getter
@Setter
public class VegaopsConfig {
    private Boolean cacheTemplate=true;
    private String reloadTemplate="false";
    private String templateFormat="json";

    public void setReloadTemplate(String reloadTemplate) {
        if(Objects.equals(reloadTemplate,"true")){
            CloudTemplateFactory.cleanTemplateCache();
        }
    }
}
