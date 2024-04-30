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
		// Je récupère l'ip et le user en plus, mais pas tout le temps util.
		final String ip = request.getRemoteAddr();
		final String user = request.getHeader("nom");
		
		if (userRepo.findByLogin(name).isEmpty()) {
			log.warn("Unknown user : "+name+" ; IP : "+ip+" ; "+user);
			throw new UtilisateurInconnuException(name);
		}
		// Je vérifie que le repo est peuplé. Sinon, message d'erreur.
		
		Utilisateur targetPerson = userRepo.findByLogin(name).get();
		
		if(!passwordEncoder.matches(password, targetPerson.getPasswordHash())) {
			log.warn("Mauvais Mot de passe pour "+name+" , IP:"+ip+" "+user);
			throw new MauvaisPasswordException(name);
		}
		// Je vérifie que le mot de passe est bon "MATCHES" pour CETTE personne.
		
		final List<GrantedAuthority> grantedAuths = new ArrayList<>();
		// Je crée une LIST de person Autorisée
		
		for (Role r: targetPerson.getRoles()) {
			grantedAuths.add(new SimpleGrantedAuthority(r.getLabel()));
		}
		// je vais chercher à LISTER toutes ces personnes autorisées.
		
		
		log.info("Authentification OK pour "+name+" ; IP : "+ip+" "+user);
		
		return new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
		// Je renvoie 
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	

}
