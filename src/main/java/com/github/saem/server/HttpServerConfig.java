package com.github.saem.server;

import com.github.saem.scaffolding.components.ComponentConfig;

public final class HttpServerConfig implements ComponentConfig {
    public final String host;
    public final int port;

    public HttpServerConfig(final String host, final int port) {
        this.host = host;
        this.port = port;
    }
}
