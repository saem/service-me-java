package com.github.saem.serviceme;

import com.github.saem.serviceme.greeting.Greeter;
import com.github.saem.serviceme.greeting.GreeterRoutes;
import com.github.saem.serviceme.scaffolding.components.AppComponents;
import com.github.saem.serviceme.scaffolding.components.healthchecks.HealthCheckComponent;
import com.github.saem.serviceme.scaffolding.components.logging.LoggingComponent;
import com.github.saem.serviceme.scaffolding.components.metrics.MetricsComponent;
import com.github.saem.serviceme.server.HttpServerComponent;
import com.github.saem.serviceme.server.admin.AdminHandlerFactory;

class GreeterComponents extends AppComponents<GreeterConfig> {
    public GreeterComponents(final GreeterConfig config) {
        super(config);
    }

    protected void initializeComponents(final GreeterConfig config) {
        final MetricsComponent metrics = addInitializedComponent(
                new MetricsComponent().initialize(config.metrics));

        addInitializedComponent(
                new LoggingComponent(metrics).initialize(config));

        final HealthCheckComponent healthChecks = addInitializedComponent(
                new HealthCheckComponent().initialize(config.healthChecks));

        addInitializedComponent(new HttpServerComponent(
                new GreeterRoutes(new Greeter())).initialize(config.app));

        addInitializedComponent(new HttpServerComponent(
                AdminHandlerFactory.buildHandler(
                        healthChecks,
                        metrics)
                ).initialize(config.admin)
        );
    }
}
