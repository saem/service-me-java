package com.github.saem.scaffolding.components.metrics;

import com.codahale.metrics.MetricRegistry;
import com.github.saem.scaffolding.components.Component;
import com.github.saem.scaffolding.components.InitializedComponent;

public class MetricsComponent implements Component<MetricsConfig>,
        InitializedComponent {
    public final MetricRegistry registry = new MetricRegistry();

    @Override
    public MetricsComponent initialize(
            final MetricsConfig componentConfig
    ) { return this; }

    @Override
    public MetricsComponent start() { return this; }
}
