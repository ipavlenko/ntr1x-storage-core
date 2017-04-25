package com.ntr1x.storage.core.services;

import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailService implements IMailService {
    
    @Override
    public JavaMailSender sender(MailScope scope) {
        
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        
        Properties properties = scope.properties.get();
        
        Properties mailProperties = new Properties(); {
            
            for (Entry<?, ?> entry : properties.entrySet()) {
                
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();
                
                if (key.startsWith("mail.properties.")) {
                    mailProperties.put(key.substring("mail.properties.".length()), value);
                }
            }
        }
        
        mailProperties.put("mail.smtp.connectiontimeout", 3000);
        
        sender.setJavaMailProperties(mailProperties);
        
        sender.setHost(properties.getProperty("mail.host"));
        sender.setPort(Integer.parseInt(properties.getProperty("mail.port")));
        sender.setProtocol(properties.getProperty("mail.protocol"));
        sender.setUsername(properties.getProperty("mail.username"));
        sender.setPassword(properties.getProperty("mail.password"));
        
        return sender;
    }
}
