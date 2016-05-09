package com.github.saem.serviceme.scaffolding.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

import java.net.URI;

final class LogConfigurationFactory extends ConfigurationFactory {
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
        final String STDOUT = "Stdout";

        builder.setStatusLevel(Level.ALL);
        builder.setConfigurationName(name);

        final AppenderComponentBuilder consoleAppender = builder
                .newAppender(STDOUT, "CONSOLE")
                .addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT)
                .add(builder.newLayout("PatternLayout")
                        .addAttribute("pattern", "[%d] %5p %c %m%n"));
        builder.add(consoleAppender);

        builder.add(builder.newRootLogger(Level.DEBUG)
                .add(builder.newAppenderRef(STDOUT)));

        return builder.build();
    }
}
