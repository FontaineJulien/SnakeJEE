package com.julfon.metiers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.julfon.beans.User;

public class UpdateUserForm {
	
	private static final String REGEXP_EMAIL = "^[a-zA-Z]([a-zA-Z0-9_]|\\.)*@[a-z]*\\.[a-z]{2,3}$";
	private static final String REGEXP_NAME = "^[A-Z][a-z]{2,30}$";
	
	private static final String ATT_USERNAME = "username";
	private static final String ATT_EMAIL = "email";
	
	private static final String PARAM_ID_PLAYER = "idPlayer";
	private static final String PARAM_USERNAME = "username";
	private static final String PARAM_EMAIL = "email";
	private static final String PARAM_IS_ADMIN = "isAdmin";
	
	private Map<String, String> erreurs = new HashMap<String, String>();
	
	public UpdateUserForm() {
		
	}
	
	public User verifyUpdate(HttpServletRequest request) {
		
		User u = new User();
		
		String email = getValueParameter(request,PARAM_EMAIL);
		String username = getValueParameter(request,PARAM_USERNAME);
		short isAdmin = getValueParameter(request,PARAM_IS_ADMIN) == null ? (short)0 : (short)1;
		long idPlayer = Long.parseLong(getValueParameter(request,PARAM_ID_PLAYER));
		
		u.setId(idPlayer);
		
		try {
			verificationUsername(username);
		} catch(Exception e) {
			setErreur(ATT_USERNAME, e.getMessage());
		}
		u.setUsername(username);
		
		try {
			verificationEmail(email);
		} catch(Exception e) {
			setErreur(ATT_EMAIL, e.getMessage());
		}
		u.setEmail(email);
		
		u.setIsAdmin(isAdmin);
		
		return u;
		
	}
	
	private void verificationUsername(String username) throws Exception {
		if(!username.matches(REGEXP_NAME))
			throw new Exception("Invalid username");
	}

	private void verificationEmail(String email) throws Exception {
		if(!email.matches(REGEXP_EMAIL))
			throw new Exception("Invalid email");
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
