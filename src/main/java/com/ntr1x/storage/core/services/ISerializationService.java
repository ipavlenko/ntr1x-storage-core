package com.ntr1x.storage.core.services;

import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;

public interface ISerializationService {
    
    String toJSONStringMOXY(Object object, Class<?>... context);
    String toXMLStringMOXY(Object object, Class<?>... context);
    
    <T> T parseJSONStringMOXY(Class<T> clazz, String string, Class<?>... context);
    <T> T parseXMLStringMOXY(Class<T> clazz, String string, Class<?>... context);
    
    String toJSONStringJackson(Object object);
    <T> T parseJSONStringJackson(Class<T> clazz, String string);
    
    <T> T parseJSONNodeJackson(Class<T> clazz, JsonNode node);
    
    JsonNode readJSONNodeJackson(String string);
    JsonNode readJSONNodeJackson(URL url);
}
