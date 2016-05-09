package com.github.saem.serviceme.scaffolding;

import org.junit.Assert;
import org.junit.Test;

public final class HostTest {
    @Test
    public void setupLocalhost() {
        Assert.assertTrue(Host.fromString("localhost").isSuccess());
    }
}
