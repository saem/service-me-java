package com.github.saem.serviceme.scaffolding.components.metrics;

import com.codahale.metrics.MetricRegistry;
import com.github.saem.serviceme.scaffolding.components.Component;
import com.github.saem.serviceme.scaffolding.components.InitializedComponent;

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
