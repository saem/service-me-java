package com.github.saem.serviceme.scaffolding;

import javaslang.control.Try;

public final class Host {
    private final String hostString;

    public static Try<Host> fromString(final String rawString) {
        final String allowedCharacters = "[a-zA-Z0-9]";
        final String domainRegex = allowedCharacters + "+";
        final String tldRegex = "(\\." + allowedCharacters + "+)?$";
        final String subDomainRegex = "^(" + allowedCharacters + "+\\.)*";
        final String completeRegex = subDomainRegex + domainRegex + tldRegex;

        return rawString.matches(completeRegex) ?
                Try.success(new Host(rawString)) :
                Try.failure(new InvalidHostException(rawString));

    }

    private Host(final String cleanedString) {
        hostString = cleanedString;
    }

    public String toString() { return hostString; }

    public int hashCode() { return hostString.hashCode(); }

    public boolean equals(final Object other) {
        return other instanceof Host &&
                ((Host) other).hostString.equals(hostString);
    }
}
