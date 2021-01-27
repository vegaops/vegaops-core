package org.prophetech.hyperone.vegaops.ctyun.model;

public  enum Method {
    GET(false),
    POST(true),
    PUT(true),
    DELETE(false),
    PATCH(true),
    HEAD(false),
    OPTIONS(false),
    TRACE(false);

    private final boolean hasBody;

    private Method(boolean hasBody) {
        this.hasBody = hasBody;
    }

    public final boolean hasBody() {
        return this.hasBody;
    }
}
