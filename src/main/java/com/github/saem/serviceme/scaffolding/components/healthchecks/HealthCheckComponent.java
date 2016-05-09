package com.github.saem.serviceme.scaffolding.components.healthchecks;

import com.codahale.metrics.health.HealthCheckRegistry;
import com.github.saem.serviceme.scaffolding.components.Component;
import com.github.saem.serviceme.scaffolding.components.InitializedComponent;

public final class HealthCheckComponent implements Component<HealthCheckConfig>,
        InitializedComponent
{
    public final HealthCheckRegistry registry = new HealthCheckRegistry();

    @Override
    public HealthCheckComponent initialize(
            final HealthCheckConfig componentConfig
    ) {
        return this;
    }

    @Override
    public HealthCheckComponent start() {
        return this;
    }
}
