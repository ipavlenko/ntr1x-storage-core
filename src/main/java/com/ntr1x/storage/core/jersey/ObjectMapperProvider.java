package com.ntr1x.storage.core.jersey;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

@Provider
@Component
public class ObjectMapperProvider  implements ContextResolver<ObjectMapper> {

    private final ObjectMapper objectMapper;

    public ObjectMapperProvider() {
        
    	objectMapper = new ObjectMapper()
            .configure(SerializationFeature.WRAP_ROOT_VALUE, true)
            .configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true)
            .enable(SerializationFeature.INDENT_OUTPUT)
            .setAnnotationIntrospector(
        		AnnotationIntrospector.pair(
            		new JacksonAnnotationIntrospector(),
            		new JaxbAnnotationIntrospector(TypeFactory.defaultInstance())
        		)
    		)
        ;
    }

    @Override
    public ObjectMapper getContext(final Class<?> type) {
    	return objectMapper;
    }
}