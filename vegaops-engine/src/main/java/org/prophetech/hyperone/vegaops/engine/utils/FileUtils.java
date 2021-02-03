package org.prophetech.hyperone.vegaops.engine.utils;

import java.io.IOException;
import java.io.InputStream;

public class FileUtils {
    public static InputStream getResourceAsStream(String resource) throws IOException {
        return FileUtils.class.getClassLoader().getResourceAsStream(resource);
    }
}
