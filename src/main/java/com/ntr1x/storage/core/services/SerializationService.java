package com.ntr1x.storage.core.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SerializationService implements ISerializationService {
    
//    @Inject
//    private ServiceLocatorProvider locator;
    
	@Inject
	private ObjectMapper mapper;
	
    @Override
    public String toJSONStringMOXY(Object object, Class<?>... context) {
        
        try {
            
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            
//            Object graph = locator.get().getService(ObjectProvider.class).getFilteringObject(object.getClass(), false, ResourceProperty.Factory.get());
            
            Map<String, Object> jaxbProperties = new HashMap<String, Object>();
            jaxbProperties.put(JAXBContextProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);
            jaxbProperties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
            jaxbProperties.put(JAXBContextProperties.JSON_ATTRIBUTE_PREFIX, "@");
            
            JAXBContext jc = JAXBContext.newInstance(context(object.getClass(), context), jaxbProperties);
            Marshaller marshaller = jc.createMarshaller();
//            marshaller.setProperty(MarshallerProperties.OBJECT_GRAPH, graph);
            
            marshaller.marshal(object, stream);
            
            return new String(stream.toByteArray());
            
        } catch (JAXBException e) {
            
            throw new IllegalArgumentException(e);
        }
    }
    
    @Override
	public String toXMLStringMOXY(Object object, Class<?>... context) {
    	
    	try {
            
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            
//            Object graph = locator.get().getService(ObjectProvider.class).getFilteringObject(object.getClass(), false, ResourceProperty.Factory.get());
            
            Map<String, Object> jaxbProperties = new HashMap<String, Object>();
            jaxbProperties.put(JAXBContextProperties.MEDIA_TYPE, MediaType.APPLICATION_XML);
            
            JAXBContext jc = JAXBContext.newInstance(context(object.getClass(), context), jaxbProperties);
            Marshaller marshaller = jc.createMarshaller();
//            marshaller.setProperty(MarshallerProperties.OBJECT_GRAPH, graph);
            
            marshaller.marshal(object, stream);
            
            return new String(stream.toByteArray());
            
        } catch (JAXBException e) {
            
            throw new IllegalArgumentException(e);
        }
	}

    @Override
    public <T> T parseJSONStringMOXY(Class<T> clazz, String string, Class<?>... context) {
        
        try {
            
            StringReader reader = new StringReader(string);
            
//            Object graph = locator.get().getService(ObjectProvider.class).getFilteringObject(clazz, false, ResourceProperty.Factory.get());
            
            Map<String, Object> jaxbProperties = new HashMap<String, Object>();
            jaxbProperties.put(JAXBContextProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);
            jaxbProperties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
            jaxbProperties.put(JAXBContextProperties.JSON_ATTRIBUTE_PREFIX, "@");
            
            JAXBContext jc = JAXBContext.newInstance(context(clazz, context), jaxbProperties);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
//            unmarshaller.setProperty(UnmarshallerProperties.OBJECT_GRAPH, graph);
            
            T result = unmarshaller.unmarshal(new StreamSource(reader), clazz).getValue();
            
            return result;
        
        } catch (JAXBException e) {
            
            throw new IllegalArgumentException(e);
        }
    }

    @Override
	public <T> T parseXMLStringMOXY(Class<T> clazz, String string, Class<?>... context) {
    	
    	try {
            
            StringReader reader = new StringReader(string);
            
//            Object graph = locator.get().getService(ObjectProvider.class).getFilteringObject(clazz, false, ResourceProperty.Factory.get());
            
            Map<String, Object> jaxbProperties = new HashMap<String, Object>();
            jaxbProperties.put(JAXBContextProperties.MEDIA_TYPE, MediaType.APPLICATION_XML);
            
            JAXBContext jc = JAXBContext.newInstance(context(clazz, context), jaxbProperties);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
//            unmarshaller.setProperty(UnmarshallerProperties.OBJECT_GRAPH, graph);
            
            T result = unmarshaller.unmarshal(new StreamSource(reader), clazz).getValue();
            
            return result;
        
        } catch (JAXBException e) {
            
            throw new IllegalArgumentException(e);
        }
	}
    
    private Class<?>[] context(Class<?> clazz, Class<?>... context) {
    	Class<?>[] result = Arrays.copyOf(context, context.length + 1);
    	result[context.length] = clazz;
    	return result;
    }
    
    @Override
	public String toJSONStringJackson(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException();
		}
	}
    
    @Override
	public <T> T parseJSONStringJackson(Class<T> clazz, String string) {
		
    	try {
			return mapper.readValue(string, clazz);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
    
    @Override
	public <T> T parseJSONNodeJackson(Class<T> clazz, JsonNode node) {
    	
    	return mapper.convertValue(node, clazz);
	}
}
