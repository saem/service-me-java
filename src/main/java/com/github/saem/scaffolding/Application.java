package com.github.saem.scaffolding;

import com.github.saem.scaffolding.components.AppComponents;
import com.github.saem.scaffolding.logging.Logging;
import com.github.saem.scaffolding.components.logging.LoggingConfig;

public abstract class Application<C extends ApplicationConfig & LoggingConfig> {
    static {
        Logging.bootstrap();
    }

    final C configuration;
    private final AppComponents appComponents;

    protected Application(final C configuration) {
        this.configuration = configuration;
        appComponents = setupComponents(configuration);
    }

    protected abstract AppComponents setupComponents(final C configuration);

    public void run() {
        appComponents.start();
    }
}
