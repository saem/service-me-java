package com.github.saem.scaffolding.components;

import com.github.saem.scaffolding.ApplicationConfig;

import java.util.ArrayList;
import java.util.List;

public abstract class AppComponents<A extends ApplicationConfig> {
    private final List<InitializedComponent> initializedComponents =
            new ArrayList<>();

    public AppComponents(final A appConfig) { initializeComponents(appConfig); }

    public void start() {
        initializedComponents.stream()
                .forEachOrdered(InitializedComponent::start);
    }

    protected abstract void initializeComponents(final A appConfig);

    protected <C extends InitializedComponent> C addInitializedComponent(
            final C component
    ) {
        initializedComponents.add(component);
        return component;
    }
}
