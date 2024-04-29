package exercicecdaauth.utils;

import org.springframework.security.core.AuthenticationException;

public class UtilisateurInconnuException extends AuthenticationException {
	
	private static final long serialVersionUID = 1L;
	
	public UtilisateurInconnuException(String msg) {
		super(msg);
	}

}
