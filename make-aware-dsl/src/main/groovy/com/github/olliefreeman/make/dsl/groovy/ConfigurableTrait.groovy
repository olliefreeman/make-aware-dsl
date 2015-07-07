package com.github.olliefreeman.make.dsl.groovy

import com.github.olliefreeman.make.dsl.Configurable
import com.github.olliefreeman.make.util.ConfigureUtil
import groovy.transform.TypeChecked

/**
 * Trait providing standard DSL build and configure methods.
 *
 * Each method delegates to the implementing class and calls the closure on the class.
 *
 * Both methods are the same just different vocabulary
 *
 * @since 25/06/2015
 */
@TypeChecked
trait ConfigurableTrait implements Configurable{

    @Override
    Configurable build(@DelegatesTo(Configurable) Closure closure) {
        ConfigureUtil.configure(this, closure)
    }

    @Override
    Configurable configure(@DelegatesTo(Configurable) Closure closure) {
        ConfigureUtil.configure(this, closure)
    }

}