package com.github.saem;

import com.github.saem.scaffolding.ApplicationConfig;
import com.github.saem.scaffolding.components.healthchecks.HealthCheckConfig;
import com.github.saem.scaffolding.components.logging.LoggingConfig;
import com.github.saem.scaffolding.components.metrics.MetricsConfig;
import com.github.saem.server.HttpServerConfig;
import org.apache.logging.log4j.Level;

public final class GreeterConfig
        extends ApplicationConfig
        implements LoggingConfig {
    public final String host    = "localhost";
    public final Level logLevel = Level.DEBUG;
    public final HttpServerConfig app = new HttpServerConfig(host, 8080);
    public final HttpServerConfig admin = new HttpServerConfig(host, 8081);
    public final MetricsConfig metrics = new MetricsConfig();
    public final HealthCheckConfig healthChecks = new HealthCheckConfig();

    @Override
    public Level getLogLevel() {
        return logLevel;
    }
}
