package com.julfon.metiers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.julfon.beans.User;
import com.julfon.dao.UserDAO;
import com.julfon.security.HashPassword;

//import com.julfon.beans.User;
//import com.julfon.security.HashPassword;

public class LoginForm {
	
	private Map<String, String> erreurs = new HashMap<String, String>();
	
	private static final String PARAM_USERNAME = "username";
	private static final String PARAM_PASSWORD = "password";
	
	private static final String ATT_PASSWORD = "password";
	private static final String ATT_USERNAME = "username";
	
	public LoginForm() {
		
	}
	
	public String login(HttpServletRequest request, UserDAO userdao) {
		
		String username = getValueParameter(request, PARAM_USERNAME);
		String password = getValueParameter(request, PARAM_PASSWORD);
		
		String passwordHash = HashPassword.get_SHA_512_SecurePassword(password);
		
		User u = userdao.find(username);
		
		if(u != null) {
			if(!u.getPassword().equals(passwordHash)) {
				setErreur(ATT_PASSWORD, "Invalid password");
			}
		} else {
			setErreur(ATT_USERNAME, "Unknown username");
		}	
		
		return username;
	}
	
	public Map<String, String> getErreurs(){
		return erreurs;
	}
	
	private void setErreur(String key, String message) {
		erreurs.put(key,message);
	}
	
	private String getValueParameter(HttpServletRequest request,String param) {
		String value = (String)request.getParameter(param);
		
		if ( value == null || value.trim().length() == 0 ) {
            return null;
        }
		
		return value;
	}
}
