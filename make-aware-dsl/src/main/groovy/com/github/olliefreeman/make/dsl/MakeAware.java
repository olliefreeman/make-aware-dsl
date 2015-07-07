package com.github.olliefreeman.make.dsl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import groovy.lang.Closure;
import groovy.lang.DelegatesTo;

/**
 * Interface to define a class as aware of the ability to make various types of output. The defaults outputs are json, text, each of these can be
 * called using the make closure (in groovy format)
 * <pre>
 *      make{
 *          json
 *      }
 *
 *      make{
 *          text
 *      }
 * </pre>
 * Json output is pretty printed by default, use the {@link #prettyPrint} method to change this
 *
 * @since 23/06/2015
 */
public interface MakeAware {

    /**
     * Make the object into JSON form. [Make method]
     *
     * @return String of the object in JSON representation
     */
    @JsonIgnore
    String getJson();

    /**
     * Make the object into text form. This is most likely the same output as JSON. [Make method]
     *
     * @return String of the object in text form
     */
    @JsonIgnore
    String getText();

    /**
     * Whether the JSON be pretty printed or not.
     *
     * @return True if JSON pretty printed, false otherwise
     */
    @JsonIgnore
    boolean isPrettyPrint();

    /**
     * Make the object using the provided closure to configure the object. Will return whatever the closure returns, this should be the output of the
     * called make method.
     *
     * @param closure The closure to be run against the object
     * @param <T>     the type of optional object to be returned. The closure must return this type.
     *
     * @return Optional output from the closure
     */
    <T> T make(@DelegatesTo(MakeAware.class) Closure<T> closure);

    /**
     * Set the json to be pretty printed or not.
     *
     * @param prettyPrint Boolean defining whether the JSON should be pretty printed.
     */
    void prettyPrint(boolean prettyPrint);
}
