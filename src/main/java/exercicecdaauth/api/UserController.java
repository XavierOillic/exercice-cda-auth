package exercicecdaauth.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
// le /user : c'est l'adresse url à laquelle je vais jouer avec curl
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
public class UserController {
	
	@GetMapping
	public String utilisateur() {
		return "Hello user" + SecurityContextHolder.getContext().getAuthentication().getName()+"!";
	}

}
