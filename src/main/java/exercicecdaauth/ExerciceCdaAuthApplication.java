package exercicecdaauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class ExerciceCdaAuthApplication {
	
	@Bean // Importa,nt de ne pas l'oublier pour rendre dispo PasswordEncoder !
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(ExerciceCdaAuthApplication.class, args);
	}
	
	
	
	/* Ce BEAN me permet de rendre cette ARRAYLIST ROLE disponible PARTOUT !
	 * Disponible avec le Décorateur @Autowired*/
	
	/*
	@Bean
	public List<Role> getAllRole(){
		return new ArrayList<Role>();
	
	}
	
	@Bean
	public List<User> getAllUser(){
		return new ArrayList<User>();
	}
	*/
	

}
