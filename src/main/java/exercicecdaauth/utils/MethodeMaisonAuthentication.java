package exercicecdaauth.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.stereotype.Component;

import exercicecdaauth.model.Role;
import exercicecdaauth.model.Utilisateur;
import exercicecdaauth.repository.UtilisateurRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class MethodeMaisonAuthentication  implements AuthenticationProvider{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired 
	private UtilisateurRepository userRepo;
	
	@Autowired
	private HttpServletRequest request;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		final String name = authentication.getName();
		final String password = authentication.getCredentials().toString();
		final String ip = request.getRemoteAddr();
		final String user = request.getHeader("nom");
		
		if (userRepo.findByLogin(name) == null) {
			log.warn("Unknown user : "+name+" ; IP : "+ip+" ; "+user);
			throw new UtilisateurInconnuException(name);
		}
		
		Utilisateur targetPerson = userRepo.findByLogin(name).get();
		
		if(!passwordEncoder.matches(password, targetPerson.getPasswordHash())) {
			log.warn("Mauvais Mot de passe pour "+name+" , IP:"+ip+" "+user);
			throw new MauvaisPasswordException(name);
		}
		final List<GrantedAuthority> grantedAuths = new ArrayList<>();
		
		for (Role r: targetPerson.getRoles()) {
			grantedAuths.add(new SimpleGrantedAuthority(r.getLabel()));
		}
		
		
		log.info("Authentification OK pour "+name+" ; IP : "+ip+" "+user);
		
		return new UsernamePasswordAuthenticationToken(name, password);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	

}
