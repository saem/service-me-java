package com.github.saem.serviceme.scaffolding;

public final class InvalidHostException extends RuntimeException {
    public InvalidHostException(final String invalidHost) {
        super("This is an invalid host: " + invalidHost);
    }
}
