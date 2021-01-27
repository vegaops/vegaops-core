package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CtyunAccount implements Serializable {
    protected String accessKey;
    protected String secret;
}
