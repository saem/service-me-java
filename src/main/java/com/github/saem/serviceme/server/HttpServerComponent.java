package com.github.saem.serviceme.server;

import com.github.saem.serviceme.scaffolding.components.Component;
import io.undertow.server.HttpHandler;

public final class HttpServerComponent implements Component<HttpServerConfig> {

    private final HttpHandler handler;

    public HttpServerComponent(final HttpHandler handler) {
        this.handler = handler;
    }

    @Override
    public InitializedHttpServerComponent initialize(
            final HttpServerConfig componentConfig
    ) {
        return new InitializedHttpServerComponent(
                ServerFactory.build(
                        componentConfig.host,
                        componentConfig.port,
                        handler
                )
        );
    }
}
