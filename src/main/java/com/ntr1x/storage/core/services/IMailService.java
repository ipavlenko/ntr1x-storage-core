package com.ntr1x.storage.core.services;

import java.util.Properties;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.mail.javamail.JavaMailSender;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

public interface IMailService {
    
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Template {
        
        public String from;
        public String subject;
        public String content;
    }
    
    @RequiredArgsConstructor
    public static class MailScope {
        
        public final String proto;
        public final String host;
        public final String portal;
        
        public final Function<String, Template> template;
        public final Supplier<Properties> properties;
    }

    JavaMailSender sender(MailScope scope);
}
