package com.ntr1x.storage.core.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public interface IMailService {

    void sendSignupConfirmation(Lang lang, SignupMessage message);
    void sendPasswdConfirmation(Lang lang, PasswdMessage message);
    void sendPasswdNotification(Lang lang, PasswdNotification message);
	void sendEmailConfirmation(Lang lang, EmailMessage message);
	
	public enum Lang {
		
		en,
		ru
		
		;
	}
	
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class SignupMessage {
        
        public String email;
        public String confirm;
    }
    
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class PasswdMessage {
        
        public String email;
        public String confirm;
    }
    
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class PasswdNotification {
        
        public String email;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    public static final class EmailMessage {
        
        public String email;
        public String confirm;
    }
}
