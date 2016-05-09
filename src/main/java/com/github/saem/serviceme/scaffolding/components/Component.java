package com.github.saem.serviceme.scaffolding.components;

public interface Component<C extends ComponentConfig> {
    /**
     * Initialize will be called first, allowing the component to go through any
     * setup that it may have, cross-component dependencies should be passed in
     * via the constructor, after the dependencies have been initialized
     *
     * @param componentConfig configuration relevant to the component
     */
    InitializedComponent initialize(final C componentConfig);
}
