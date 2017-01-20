package com.ntr1x.storage.core.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
public class RendererService implements IRendererService {

	@Inject
    private VelocityEngine engine;
	
	@Override
	public ITemplateRenderer renderer(String name) {
		
		return new TemplateRenderer(name, engine, new VelocityContext());
	}
	
	@RequiredArgsConstructor
	public static class TemplateRenderer implements ITemplateRenderer {
		
		private final String name;
		private final VelocityEngine engine;
		private final VelocityContext context;
		
		@Override
		public TemplateRenderer with(String key, Object value) {
			context.put(key, value);
			return this;
		}
		
		@Override
		public String render(String content) {
			
			StringWriter writer = new StringWriter();
			engine.evaluate(context, writer, name, content);
			return writer.toString();
		}
		
		@Override
		public String render(URL url) {
			
			try (InputStream input = url.openStream()) {
				
				StringWriter writer = new StringWriter();
				IOUtils.copy(input, writer, "UTF-8");
				
				return render(writer.toString());
				
			} catch (IOException e) {
				
				throw new IllegalArgumentException(e);
			}
		}
	}
}
