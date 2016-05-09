package com.github.saem.serviceme.scaffolding;

import com.github.saem.serviceme.scaffolding.components.AppComponents;
import com.github.saem.serviceme.scaffolding.logging.Logging;
import com.github.saem.serviceme.scaffolding.components.logging.LoggingConfig;

public abstract class Application<C extends ApplicationConfig & LoggingConfig> {
    static {
        Logging.bootstrap();
    }

    final C configuration;
    private final AppComponents appComponents;

    protected Application(
            final C configuration,
            final AppComponents components) {
        this.configuration = configuration;
        this.appComponents = components;
    }

    public void run() {
        appComponents.start();
    }
}
