package com.github.olliefreeman.make.util

import groovy.transform.TypeChecked
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Simple util class to set the closure delegate and then call the closure on the delegate. All methods return the delegate after it has been
 * configured. The default resolution strategy is {@link Closure#DELEGATE_FIRST}
 *
 * @since 24/06/2015
 */
@Singleton
@TypeChecked
class ConfigureUtil {

    private static final Logger logger = LoggerFactory.getLogger(ConfigureUtil)

    /**
     * Configure the delegate using the given closure using the default resolution strategy of {@link Closure#DELEGATE_FIRST}.
     *
     * Returns the delegate after configuring
     *
     * @param theDelegate Object to be configured
     * @param closure Closure to be applied to the delegate object
     * @return the configured delegate
     */
    static <T> T configure(@DelegatesTo.Target T theDelegate, @DelegatesTo Closure closure) {
        configure(theDelegate, Closure.DELEGATE_FIRST, closure)
    }

    /**
     * Configure the delegate using the given closure using the provided resolution strategy
     *
     * Returns the delegate after configuring
     *
     * @param theDelegate Object to be configured
     * @param resolutionStrategy Closure resolution strategy should be one of <ul>
     *     <li>{@link Closure#DELEGATE_FIRST}
     *     <li>{@link Closure#DELEGATE_ONLY}
     *     <li>{@link Closure#OWNER_FIRST}
     *     <li>{@link Closure#OWNER_ONLY}
     * @param closure Closure to be applied to the delegate object
     * @return the configured delegate
     */
    static <T> T configure(@DelegatesTo.Target T theDelegate, int resolutionStrategy, @DelegatesTo Closure closure) {
        closure.delegate = theDelegate
        logger.trace("Closure property: Owner: ${closure.owner.class.simpleName} || Delegate: ${closure.delegate.class.simpleName}")
        closure.resolveStrategy = resolutionStrategy
        closure()
        theDelegate
    }
}
