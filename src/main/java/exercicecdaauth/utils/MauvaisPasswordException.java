package exercicecdaauth.utils;

import org.springframework.security.core.AuthenticationException;

public class MauvaisPasswordException extends AuthenticationException {

	public MauvaisPasswordException(String msg) {
		super(msg);
		
	}

}
