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
        logger.trace("Closure property: Owner: ${getClassName(closure.owner)} || Delegate: ${getClassName(closure.delegate)}")
        closure.resolveStrategy = resolutionStrategy
        closure()
        theDelegate
    }

    /**
     * Fixing method for the class name for nests closures. For some reason this occasionally fails using the {@link Class#getSimpleName()},
     * this method provides a fix.
     *
     * @param obj The object to get the class name for
     * @return String representation of the class name
     */
    private static String getClassName(def obj){
        Class clazz = obj.class
        try{
            return clazz.simpleName
        }catch (InternalError ignored){
            try {
                return clazz.getName().substring(clazz.getEnclosingClass().getName().length());
            } catch (IndexOutOfBoundsException ex) {
                throw new InternalError("Malformed class name", ex);
            }
        }

    }
}
