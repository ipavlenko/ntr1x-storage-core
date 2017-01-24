package com.ntr1x.storage.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.tools.generic.EscapeTool;

public class ResourceLoader {
		
	public static String string(String original) {
		
		return new EscapeTool().java(original);
	}
	
	public static String content(String location) {
		
		try (InputStream input = ResourceLoader.class.getResource(location).openStream()) {
			
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			
			return writer.toString();
			
		} catch (IOException e) {
			
			throw new IllegalArgumentException(e);
		}
	}
}