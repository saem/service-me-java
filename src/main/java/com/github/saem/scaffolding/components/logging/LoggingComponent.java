package com.github.saem.scaffolding.components.logging;

import com.github.saem.scaffolding.components.Component;
import com.github.saem.scaffolding.components.metrics.MetricsComponent;

public class LoggingComponent implements Component<LoggingConfig> {
    private final MetricsComponent metrics;

    public LoggingComponent(final MetricsComponent metrics) {
        this.metrics = metrics;
    }

    @Override
    public InitializedLoggingComponent initialize(
            final LoggingConfig componentConfig
    ) {
        return new InitializedLoggingComponent(
                metrics,
                componentConfig.getLogLevel()
        );
    }
}
