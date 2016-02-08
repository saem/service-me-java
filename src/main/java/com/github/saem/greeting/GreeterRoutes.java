package com.github.saem.greeting;

import com.github.saem.HttpRouting;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public final class GreeterRoutes implements HttpHandler {
    private final Greeter greeter;

    public GreeterRoutes(final Greeter greeter) {
        this.greeter = greeter;
    }

    @Override
    public void handleRequest(final HttpServerExchange exchange) throws Exception {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
        exchange.getResponseSender().send(greeter.phrase);
    }
}
