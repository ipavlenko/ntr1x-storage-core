package com.ntr1x.storage.core.services;

import java.io.StringWriter;
import java.util.Map;

import javax.inject.Inject;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Service
public class PageService implements IPageService {

	@Value("${app.public.url}")
    private String url;
    
    @Setter
    @Getter
    @Configuration
    @ConfigurationProperties(prefix = "app.page")
    public static class Config {
        
        private Map<String, Template> templates;
        
        @Setter
        @Getter
        public static class Template {
        	
            private String path;
            private Map<String, Object> settings;
        }
    }
    
    @Inject
    private Config config;
	
	@Inject
    private VelocityEngine velocity;
	
	@Override
	public String page(String name, Map<String, ?> model) {
		
		Config.Template template = config.templates.get(name);
		
		VelocityContext context = new VelocityContext(); {
			
			context.put("settings", template.settings);
			context.put("model", model);
        };
        
        StringWriter writer = new StringWriter();
        velocity.mergeTemplate(template.path, "UTF-8", context, writer);
        return writer.toString();
	}
}
