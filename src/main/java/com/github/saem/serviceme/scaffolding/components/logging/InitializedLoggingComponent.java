package com.github.saem.serviceme.scaffolding.components.logging;

import com.github.saem.serviceme.scaffolding.components.InitializedComponent;
import com.github.saem.serviceme.scaffolding.components.metrics.MetricsComponent;
import log4j2.InstrumentedAppender;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.layout.PatternLayout;

public final class InitializedLoggingComponent implements InitializedComponent {
    private final MetricsComponent metrics;
    private final Level level;

    public InitializedLoggingComponent(
            final MetricsComponent metrics,
            final Level level
    ) {
        this.metrics = metrics;
        this.level = level;
    }

    @Override
    public InitializedLoggingComponent start() {
        final Filter filter = null;
        final PatternLayout layout = null;
        final boolean ignoreExceptions = false;

        final InstrumentedAppender metricsAppender = new InstrumentedAppender(
                metrics.registry, filter, layout, ignoreExceptions
        );
        metricsAppender.start();

        final LoggerContext context = (LoggerContext) LogManager.getContext(false);
        final Configuration configuration = context.getConfiguration();

        configuration.getRootLogger()
                .addAppender(metricsAppender, level, filter);

        context.updateLoggers(configuration);

        return this;
    }
}
