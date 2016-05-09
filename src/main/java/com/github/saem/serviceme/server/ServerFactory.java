package com.github.saem.serviceme.server;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.ExceptionHandler;
import io.undertow.server.handlers.accesslog.AccessLogHandler;
import io.undertow.util.Headers;
import io.undertow.util.StatusCodes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ServerFactory {
    private final static Logger LOGGER =
            LogManager.getLogger(ServerFactory.class);
    private final static Logger accessLog = LogManager.getLogger("access-log");

    public static Undertow build(final String host,
                                 final int port,
                                 final HttpHandler router) {
        return Undertow.builder()
                .addHttpListener(port, host)
                .setHandler(middlewareHandlers(router))
                .build();
    }

    private static HttpHandler middlewareHandlers(final HttpHandler next) {
        LOGGER.info("start - setting up the logger");

        final ExceptionHandler exceptionHandler =
                Handlers.exceptionHandler(next)
                .addExceptionHandler(
                        Exception.class,
                        exchange -> {
                            exchange.getResponseHeaders().put(
                                    Headers.STATUS,
                                    StatusCodes.INTERNAL_SERVER_ERROR);
                            exchange.getResponseSender().close();
                        }
                );

        final AccessLogHandler logHandler = new AccessLogHandler(
                exceptionHandler,
                x -> {
                    accessLog.debug(x);
                },
                "\"%r\" %s %b \"%{i,Referer}\" \"%{i,User-Agent}\"",
                ServerFactory.class.getClassLoader()
        );

        LOGGER.info("end - setting up the logger");

        return logHandler;
    }
}
