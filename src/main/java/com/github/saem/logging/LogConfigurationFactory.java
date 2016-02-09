package com.github.saem.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

import java.net.URI;

public final class LogConfigurationFactory extends ConfigurationFactory {
    @Override
    protected String[] getSupportedTypes() {
        return new String[] {"*"};
    }

    @Override
    public Configuration getConfiguration(final ConfigurationSource source) {
        return createConfiguration(source.toString());
    }

    @Override
    public Configuration getConfiguration(
            final String name,
            final URI configLocation) {
        return createConfiguration(name);
    }

    private static Configuration createConfiguration(final String name) {
        return createConfiguration(name, newConfigurationBuilder());
    }

    private static Configuration createConfiguration(
            final String name,
            final ConfigurationBuilder<BuiltConfiguration> builder
    ) {
        builder.setConfigurationName(name);
        builder.setStatusLevel(Level.DEBUG);

        return builder.build();
    }
}
