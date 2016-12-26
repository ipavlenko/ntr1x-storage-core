package com.ntr1x.storage.core.services;

import java.io.StringWriter;
import java.util.Date;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;

import lombok.Getter;
import lombok.Setter;

@Service
public class MailService  implements IMailService {
    
    @Setter
    @Getter
    @Configuration
    @ConfigurationProperties(prefix = "app.mail")
    public static class Config {
        
    	private Map<String, String> params;
        private Map<Lang, Map<String, Template>> templates;
        
        @Setter
        @Getter
        public static class Template {
            
            private String from;
            private String subject;
            private String path;
            private Map<String, String> params;
        }
    }
    
    @Inject
    private Config config;
    
    @Inject
    private JavaMailSender sender;
    
    @Inject
    private VelocityEngine velocity;
    
    public String text(Config.Template template, Map<String, String> params) {
    	
    	VelocityContext context = new VelocityContext();
    	
    	context.put("mail", config.params);
		context.put("template", template.params);
		context.put("message", params);
        
		StringWriter writer = new StringWriter();
        velocity.mergeTemplate(template.path, "UTF-8", context, writer);
        return writer.toString();
    }
    
    @Override
    public void sendSignupConfirmation(Lang lang, SignupMessage message) {
        
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage); {
                    
                    Config.Template template = config.templates.get(lang).get("signup");
                    
                    helper.setTo(message.email);
                    helper.setFrom(template.from);
                    helper.setSubject(template.subject);
                    helper.setSentDate(new Date());
                    
                    helper.setText(text(template, ImmutableMap.of("token", message.confirm)), true);
                }
            }
        };
        
        sender.send(preparator);
    }
    
    @Override
    public void sendPasswdConfirmation(Lang lang, PasswdMessage message) {
        
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage); {
                    
                    Config.Template template = config.templates.get(lang).get("passwd");
                    
                    helper.setTo(message.email);
                    helper.setFrom(template.from);
                    helper.setSubject(template.subject);
                    helper.setSentDate(new Date());
                    
                    helper.setText(text(template, ImmutableMap.of("token", message.confirm)), true);
                }
            }
        };
        
        sender.send(preparator);
    }
    
    @Override
    public void sendPasswdNotification(Lang lang, PasswdNotification message) {
        
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage); {
                    
                    Config.Template template = config.templates.get(lang).get("passwdNotification");
                    
                    helper.setTo(message.email);
                    helper.setFrom(template.from);
                    helper.setSubject(template.subject);
                    helper.setSentDate(new Date());
                    
                    helper.setText(text(template, null), true);
                }
            }
        };
        
        sender.send(preparator);
    }
    
    @Override
    public void sendEmailConfirmation(Lang lang, EmailMessage message) {
        
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage); {
                    
                    Config.Template template = config.templates.get(lang).get("email");
                    
                    helper.setTo(message.email);
                    helper.setFrom(template.from);
                    helper.setSubject(template.subject);
                    helper.setSentDate(new Date());
                    
                    helper.setText(text(template, ImmutableMap.of("token", message.confirm)), true);
                }
            }
        };
        
        sender.send(preparator);
    }
}
