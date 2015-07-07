package com.github.olliefreeman.make.dsl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import groovy.lang.Closure;
import groovy.transform.stc.ClosureParams;
import groovy.transform.stc.SimpleType;
import groovy.xml.MarkupBuilder;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Map;

/**
 * Interface defining a class as capable of generating XML markup using the groovy {@link MarkupBuilder}. Extends {@link MakeAware} as anything needs
 * the methods from this interface to actually perform the make.
 * <p>
 * Given the nature of the MarkupBuilder there is no way to get the generated output from the builder. Therefore without the writer set any possible
 * output from {@link #make(Closure)} will be null.
 * <p>
 * The attributes map is desiigned to give quick access to XML element attributes which can be passed directly to the MarkupBuilder.
 *
 * @since 24/06/2015
 */
public interface XmlMakeAware extends MakeAware {

    /**
     * Apply the collection which must be {@link XmlMakeAware} to the given {@link MarkupBuilder}.
     *
     * This method sets the {@link #builder(MarkupBuilder)} for each element in the collection and then calls {@link #getXmlMarkup()}, returning
     * the markupbuilder afterwards.
     *
     * @param markupBuilder {@link MarkupBuilder} to apply the collection to
     * @param contents {@link XmlMakeAware} collection of elements to apply to the markup builder
     * @return MarkupBuilder which the collection has been applied to.
     */
    @JsonIgnore
    MarkupBuilder applyXmlMakeAwareCollection(MarkupBuilder markupBuilder, Collection<XmlMakeAware> contents);

    /**
     * Set the {@link MarkupBuilder} which will be used to build the XML.
     * <p>
     * This MUST be instantiated with the same {@link Writer} which is passed to the {@link #writer(Writer)} method.
     *
     * @param makeBuilder {@link MarkupBuilder} to use to build the XML.
     */
    void builder(MarkupBuilder makeBuilder);

    /**
     * Get the attributes for the extending class XML element.
     *
     * @return Map of attributes for the element
     */
    @JsonIgnore
    Map<String, String> getAttributes();

    /**
     * Get the markup closure used for the element. This allows other elements to call the markup closure for the implementing class. The markup will
     * be delegated to the {@link MarkupBuilder} set as the builder.
     *
     * @return {@link Closure} defining the markup for the element.
     */
    @JsonIgnore
    Closure getMarkupClosure();

    /**
     * Make the xml markup. [Make method]
     * <p>
     * Requires the builder to be set and the markup closure to be set, wil throw {@link IllegalStateException} if either are null. Will return the
     * string of the XML obtained from the writer, if the writer is set and it is a {@link StringWriter}, will return null otherwise.
     *
     * @return String of XML or null depending on the state of the writer.
     *
     * @throws IllegalStateException if no markup closure or the builder is null.
     */
    @JsonIgnore
    String getXmlMarkup() throws IllegalStateException;

    /**
     * Build method for setting the markup for creating the object as an xml element. The closure will be passed the {@link MarkupBuilder} defined by
     * the builder.
     *
     * @param closure Closure for creating the element as an XML element.
     */
    void markup(@ClosureParams(value = SimpleType.class, options = {"groovy.xml.MarkupBuilder"}) Closure closure);

    /**
     * [Optional] Set the {@link Writer} the builder uses to write the outputted XML.
     * <p>
     * If the writer is set then it MUST be the same one used to instantiate the builder.
     *
     * @param writer {@link Writer} used to output the XML.
     */
    void writer(Writer writer);

}
