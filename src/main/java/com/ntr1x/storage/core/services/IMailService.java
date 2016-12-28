package com.ntr1x.storage.core.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public interface IMailService {

    void sendSignupConfirmation(Lang lang, SignupConfirmation message);
    void sendRecoverConfirmation(Lang lang, PasswdConfirmation message);
    void sendPasswdNotification(Lang lang, PasswdNotification message);
	void sendEmailConfirmation(Lang lang, EmailConfirmation message);
	
	public enum Lang {
		
		en,
		ru
		
		;
	}
	
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class SignupConfirmation {
        
        public String email;
        public String confirm;
    }
    
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class PasswdConfirmation {
        
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
    public static final class EmailConfirmation {
        
        public String email;
        public String confirm;
    }
}
