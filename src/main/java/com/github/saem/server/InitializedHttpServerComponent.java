package com.github.saem.server;

import com.github.saem.scaffolding.components.InitializedComponent;
import io.undertow.Undertow;

public class InitializedHttpServerComponent implements InitializedComponent {
    private final Undertow notStartedServer;

    public InitializedHttpServerComponent(final Undertow notStartedServer) {
        this.notStartedServer = notStartedServer;
    }

    @Override
    public InitializedHttpServerComponent start() {
        notStartedServer.start();

        return this;
    }
}
