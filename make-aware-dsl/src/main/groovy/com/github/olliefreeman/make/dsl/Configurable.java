package com.github.olliefreeman.make.dsl;

import groovy.lang.Closure;
import groovy.lang.DelegatesTo;

/**
 * Interface to provide simple configuration to an implementing class by using build or configure and closures.
 *
 * @since 07/07/2015
 */
public interface Configurable {

    /**
     * Calls the given {@link Closure} on the object. Conceptually use by implementing with a new Object which implements this interface, therefore
     * building the object by using the closure. Should return the built object.
     *
     * @param closure The closure to call on the implementing object.
     *
     * @return The object after the closure has been called.
     */
    Configurable build(@DelegatesTo(Configurable.class) Closure closure);

    /**
     * Calls the given {@link Closure} on the object. Conceptually use by implementing on the current object which implements this interface,
     * therefore reconfiguring the object by using the closure. Should return the configured object.
     *
     * @param closure The closure to call on the implementing object.
     *
     * @return The object after the closure has been called.
     */
    Configurable configure(@DelegatesTo(Configurable.class) Closure closure);
}
