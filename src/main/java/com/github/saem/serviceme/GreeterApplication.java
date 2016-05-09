package com.github.saem.serviceme;

import com.github.saem.serviceme.scaffolding.Application;

public final class GreeterApplication extends Application<GreeterConfig> {
    GreeterApplication(final GreeterConfig configuration) {
        super(configuration, new GreeterComponents(configuration));
    }

    public static void main(final String[] args) {
        new GreeterApplication(new GreeterConfig()).run();
    }
}

