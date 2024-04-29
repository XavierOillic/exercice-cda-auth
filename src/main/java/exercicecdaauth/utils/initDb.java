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
		
		Role utilisateur = roleRepo.save(new Role("Utilisateur"));
		Role administrateur = roleRepo.save(new Role("Administrateur"));
		Role webMaster = roleRepo.save(new Role("Webmaster"));
		Role developpeur = roleRepo.save(new Role("Développeur"));
		Role infographiste = roleRepo.save(new Role("Infographiste"));
		
		Utilisateur xavier = userRepo.save(new Utilisateur("Xavier", "Oillic", "MotdePasse"));
		xavier.getRoles().add(utilisateur);
		Utilisateur jean = userRepo.save(new Utilisateur("Jean", "Grey", "MotDePasse"));
		jean.getRoles().add(administrateur);
		Utilisateur bruce = userRepo.save(new Utilisateur("Bruce", "Wayne", "MotDePasse"));
		bruce.getRoles().add(webMaster);
		Utilisateur martin = userRepo.save(new Utilisateur("Martin", "Scorcese", "MotDePasse"));
		martin.getRoles().add(developpeur);
		Utilisateur idriss = userRepo.save(new Utilisateur("Idriss", "Elba", "MotDePasse"));
		idriss.getRoles().add(infographiste);
		
		}
			
}


