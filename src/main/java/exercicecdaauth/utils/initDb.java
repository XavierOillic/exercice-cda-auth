package exercicecdaauth.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import exercicecdaauth.model.Role;
import exercicecdaauth.model.Utilisateur;
import exercicecdaauth.repository.RoleRepository;
import exercicecdaauth.repository.UtilisateurRepository;

@Component
public class initDb implements ApplicationRunner{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UtilisateurRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		userRepo.deleteAll();
		roleRepo.deleteAll();
		
		final String mdp = "xavier"; 
		// Je variabilise le mot de passe.
		
		//ROLES :
		Role admin = roleRepo.save(new Role("ROLE_ADMIN"));
		Role user = roleRepo.save(new Role("ROLE_USER"));
		
		roleRepo.save(new Role("ROLE_XMEN"));
		roleRepo.save(new Role("ROLE_DC"));
		
		
		//UTILISATEURS : 
		Utilisateur xavier = userRepo.save(new Utilisateur("zazou", "Xavier", "Oillic", passwordEncoder.encode(mdp)));
		xavier.getRoles().add(user);
		xavier.getRoles().add(admin);
		
		Utilisateur jean = userRepo.save(new Utilisateur("phoenix", "Jean", "Grey", "xmen"));
		jean.getRoles().add(roleRepo.findByLabel("ROLE_XMEN").orElseThrow());
		
		Utilisateur bruce = userRepo.save(new Utilisateur("batman", "Bruce", "Wayne", "dc"));
		bruce.getRoles().add(roleRepo.findByLabel("ROLE_DC").orElseThrow());
	
		
		
		// Je Sauvegarde mes USERS dans la DataBase MongoDB.
		userRepo.save(xavier);
		userRepo.save(jean);
		userRepo.save(bruce);
		
		}
			
}


