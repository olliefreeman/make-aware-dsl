package com.github.olliefreeman.make.util

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.joda.JodaModule
import groovy.transform.TypeChecked
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Singleton class to provide Json utils for converting objects to JSON representations.
 *
 * @since 25/06/2015
 */
@Singleton
@TypeChecked
class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class)

    final ObjectMapper mapper = new ObjectMapper()

    private Boolean initialised = false

    void initialise() {
        if (!initialised) {
            // to allow serialization of "empty" POJOs (no properties to serialize)
            // (without this setting, an exception is thrown in those cases)
            mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            // to write java.util.Date, Calendar as number (timestamp):
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            // DeserializationFeature for changing how JSON is read as POJOs:

            // to prevent exception when encountering unknown property:
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            // to allow coercion of JSON empty String ("") to null Object value:
            mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            // to force escaping of non-ASCII characters:
            mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
            mapper.registerModule(new JodaModule());
        }
    }

    static String writeString(Object obj) {
        writeString(obj, true)
    }

    static String writeString(Object obj, Boolean pretty) {
        JsonUtil.instance.writeValueAsString(obj, pretty)
    }

    String writeValueAsString(Object obj, Boolean pretty) throws JsonProcessingException {
        initialise()
        if (pretty) {
            mapper.enable(SerializationFeature.INDENT_OUTPUT)
        }
        else {
            mapper.disable(SerializationFeature.INDENT_OUTPUT)
        }
        mapper.writeValueAsString(obj)
    }

    String writeValueAsStringIgnoreException(Object obj, Boolean pretty) {
        try {
            return writeString(obj, pretty)
        } catch (JsonProcessingException ex) {
            logger.warn("Ignored Json Processing Exception: {}", ex.message)
        }
        return ''
    }
}
