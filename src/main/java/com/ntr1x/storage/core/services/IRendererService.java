package com.ntr1x.storage.core.services;

import java.net.URL;

public interface IRendererService {

    ITemplateRenderer renderer(String name);
    
    public interface ITemplateRenderer {
        
        ITemplateRenderer with(String key, Object value);
        String render(String content);
        String render(URL url);
    }
}
