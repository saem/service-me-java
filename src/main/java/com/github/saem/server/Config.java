package com.github.saem.server;

import org.apache.logging.log4j.Level;

public final class Config {
    public final int appPort    = 8080;
    public final int adminPort  = 8081;
    public final String host    = "localhost";
    public final Level logLevel = Level.DEBUG;
}
