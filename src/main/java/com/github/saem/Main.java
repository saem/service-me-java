package com.github.saem;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.github.saem.greeting.Greeter;
import com.github.saem.greeting.GreeterRoutes;
import com.github.saem.server.Configuration;
import com.github.saem.server.ServerFactory;
import io.undertow.Undertow;

public final class Main {
    public static void main(final String[] args) {
        final Configuration config = new Configuration();
        final Undertow server = ServerFactory.build(
                config.host,
                config.appPort,
                new GreeterRoutes(new Greeter()));
        server.start();

        final MetricRegistry metrics = new MetricRegistry();
        final HealthCheckRegistry healthChecks = new HealthCheckRegistry();
        final Undertow adminServer = ServerFactory.build(
                config.host,
                config.adminPort,
                AdminServletServer.buildServletHandler(
                        healthChecks,
                        metrics)
        );
        adminServer.start();
    }
}

