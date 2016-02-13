package com.github.saem;

import com.github.saem.scaffolding.Application;

public final class GreeterApplication extends Application<GreeterConfig> {
    GreeterApplication(final GreeterConfig configuration) {
        super(configuration);
    }

    @Override
    protected GreeterComponents setupComponents(
            final GreeterConfig greeterConfig
    ) {
        return new GreeterComponents(greeterConfig);
    }

    public static void main(final String[] args) {
        final GreeterApplication app = new GreeterApplication(new GreeterConfig());

        app.run();
    }
}

