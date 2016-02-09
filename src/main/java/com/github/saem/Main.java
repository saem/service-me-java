package com.github.saem;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.github.saem.admin.AdminHandlerFactory;
import com.github.saem.greeting.Greeter;
import com.github.saem.greeting.GreeterRoutes;
import com.github.saem.logging.LogConfigurationFactory;
import com.github.saem.server.Config;
import com.github.saem.server.ServerFactory;
import io.undertow.Undertow;
import log4j2.InstrumentedAppender;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

public final class Main {
    public static void main(final String[] args) {
        ConfigurationFactory.setConfigurationFactory(
                new LogConfigurationFactory());

        final MetricRegistry metrics = new MetricRegistry();
        final HealthCheckRegistry healthChecks = new HealthCheckRegistry();
        final Config config = new Config();

        setupLogging(metrics, config.logLevel);

        final Undertow server = ServerFactory.build(
                config.host,
                config.appPort,
                new GreeterRoutes(new Greeter()));
        server.start();

        final Undertow adminServer = ServerFactory.build(
                config.host,
                config.adminPort,
                AdminHandlerFactory.buildHandler(
                        healthChecks,
                        metrics)
        );
        adminServer.start();
    }

    private static void setupLogging(
            final MetricRegistry metrics,
            final Level level
    ) {
        final Filter filter = null;
        final PatternLayout layout = null;
        final boolean ignoreExceptions = false;

        final InstrumentedAppender metricsAppender = new InstrumentedAppender(
                metrics, filter, layout, ignoreExceptions
        );
        metricsAppender.start();

        final ConsoleAppender consoleAppender = ConsoleAppender.newBuilder()
                .setTarget(ConsoleAppender.Target.SYSTEM_OUT)
                .setName("stdout")
                .build();
        consoleAppender.start();

        final LoggerContext context = (LoggerContext) LogManager.getContext(false);
        final Configuration configuration = context.getConfiguration();

        configuration.getRootLogger()
                .addAppender(metricsAppender, level, filter);

        configuration.getRootLogger()
                .addAppender(consoleAppender, level, filter);

        context.updateLoggers(configuration);
    }
}

