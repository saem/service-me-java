package com.github.saem.server;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.util.Headers;

public final class ServerFactory {
    public static Undertow build(final String host,
                                 final int port,
                                 final HttpHandler router) {
        return Undertow.builder()
                .addHttpListener(port, host)
                .setHandler(router)
                .build();
    }
}

