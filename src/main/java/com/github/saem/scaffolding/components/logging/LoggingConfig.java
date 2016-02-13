package com.github.saem.scaffolding.components.logging;

import com.github.saem.scaffolding.components.ComponentConfig;
import org.apache.logging.log4j.Level;

public interface LoggingConfig extends ComponentConfig {
    default Level getLogLevel() { return Level.INFO; }
}
