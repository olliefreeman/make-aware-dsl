package com.github.olliefreeman.make.dsl.test;

import com.github.olliefreeman.make.dsl.XmlMakeAware;
import groovy.test.GroovyAssert;
import groovy.xml.MarkupBuilder;
import groovy.xml.MarkupBuilderHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @since 24/06/2015
 */
public class XmlMakeAwareTest extends GroovyAssert {

    public final Logger logger = LoggerFactory.getLogger(getClass());
    private StringWriter stringWriter = new StringWriter();
    private MarkupBuilder markupBuilder = new MarkupBuilder(stringWriter);

    public XmlMakeAware testObject;

    /**
     * Gets the XML declaration map.
     *
     * @return Map containing the default XML declaration
     */
    public static Map<String, Object> getXmlDeclaration() {
        Map<String, Object> xmlDeclaration = new LinkedHashMap<>(2);
        xmlDeclaration.put("version", "1.0");
        xmlDeclaration.put("encoding", "UTF-8");
        return xmlDeclaration;
    }

    /**
     * Get the {@link MarkupBuilder} with
     * <p>
     * <ul>
     *     <li>Double Quotes</li>
     *     <li>Empty Attributes Omitted</li>
     *     <li>Nlil Attributed Omitted</li>
     * </ul>
     *
     * @return the markup builder with the above attributes
     */
    public MarkupBuilder getMarkupBuilder() {
        return getMarkupBuilder(true, true, true, false);
    }

    /**
     * Get the {@link MarkupBuilder} with
     * <p>
     * <ul>
     *     <li>Double Quotes</li>
     *     <li>Empty Attributes Omitted</li>
     *     <li>Nlil Attributed Omitted</li>
     *     <li>Xml Declaration</li>
     * </ul>
     *
     * @return the markup builder with the above attributes
     */
    public MarkupBuilder getFullMarkupBuilder() {
        return getMarkupBuilder(true, true, true, true);
    }

    /**
     * Get the {@link MarkupBuilder} with the parameters pre-set.
     *
     * @param doubleQuotes        boolean true if double quotes should be used {@link MarkupBuilder#getDoubleQuotes()}
     * @param omitEmptyAttributes boolean true if empty attributes should be omitted {@link MarkupBuilder#isOmitEmptyAttributes()}
     * @param omitNullAttributes  boolean true if null attributes should be omitted {@link MarkupBuilder#isOmitNullAttributes()}
     * @param addXmlDeclaration   boolean true if the XML declaration should be added {@link MarkupBuilderHelper#xmlDeclaration(Map)}
     *
     * @return the markup builder with the above attributes
     */
    public MarkupBuilder getMarkupBuilder(boolean doubleQuotes, boolean omitEmptyAttributes, boolean omitNullAttributes, boolean addXmlDeclaration) {
        markupBuilder.setDoubleQuotes(doubleQuotes);
        markupBuilder.setOmitEmptyAttributes(omitEmptyAttributes);
        markupBuilder.setOmitNullAttributes(omitNullAttributes);

        if (addXmlDeclaration) {
            markupBuilder.getMkp().xmlDeclaration(getXmlDeclaration());
        }
        return markupBuilder;
    }

    /**
     * Get the {@link StringWriter} the {@link MarkupBuilder} was initialised with.
     *
     * @return {@link StringWriter} the markup builder is using.
     */
    public StringWriter getStringWriter() {
        return stringWriter;
    }

    /**
     * Reset the {@link StringWriter} and reinitialised the {@link MarkupBuilder} with the new stringwriter.
     */
    public void resetGenerators() {
        stringWriter = new StringWriter();
        markupBuilder = new MarkupBuilder(stringWriter);
    }
}
