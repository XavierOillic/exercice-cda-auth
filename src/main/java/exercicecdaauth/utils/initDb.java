package exercicecdaauth.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import exercicecdaauth.model.Role;
import exercicecdaauth.model.User;
import exercicecdaauth.repository.RoleRepository;
import exercicecdaauth.repository.UserRepository;

@Component
public class initDb implements ApplicationRunner{
	
	@Autowired
	private UserRepository userRepo;
	
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
		
		User xavier = userRepo.save(new User("Xavier", "Oillic", "MotdePasse"));
		xavier.getRoles().add(utilisateur);
		User jean = userRepo.save(new User("Jean", "Grey", "MotDePasse"));
		jean.getRoles().add(administrateur);
		User bruce = userRepo.save(new User("Bruce", "Wayne", "MotDePasse"));
		bruce.getRoles().add(webMaster);
		User martin = userRepo.save(new User("Martin", "Scorcese", "MotDePasse"));
		martin.getRoles().add(developpeur);
		User idriss = userRepo.save(new User("Idriss", "Elba", "MotDePasse"));
		idriss.getRoles().add(infographiste);
		
		}
			
}


