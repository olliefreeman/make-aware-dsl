package com.github.olliefreeman.make.dsl.java;

import com.github.olliefreeman.make.dsl.Configurable;
import com.github.olliefreeman.make.util.ConfigureUtil;
import groovy.lang.Closure;
import groovy.lang.DelegatesTo;

/**
 * Abstract class providing standard DSL build and configure methods.
 * <p>
 * Each method delegates to the implementing class and calls the closure on the class.
 * <p>
 * Both methods are the same just different vocabulary
 *
 * @since 25/06/2015
 */
public abstract class AbstractConfigurable implements Configurable {

    @Override
    public AbstractConfigurable build(@DelegatesTo(Configurable.class) Closure closure) {
        return ConfigureUtil.configure(this, closure);
    }

    @Override
    public AbstractConfigurable configure(@DelegatesTo(Configurable.class) Closure closure) {
        return ConfigureUtil.configure(this, closure);
    }

}
