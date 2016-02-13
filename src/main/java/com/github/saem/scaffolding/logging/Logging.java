package com.github.saem.scaffolding.logging;

import org.apache.logging.log4j.core.config.ConfigurationFactory;

/**
 * This is likely not what you're looking for, this is only here because Log4j2,
 * due to its use of static methods to lookup loggers requires a very early
 * call to ensure that we've configured it correctly.
 *
 * After that setting up new appenders, such as instrumentation, is taken care
 * of as a Component, like everything else.
 */
public final class Logging {
    public static void bootstrap() {
        ConfigurationFactory.setConfigurationFactory(
                new LogConfigurationFactory());
    }
}
