package exercicecdaauth.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
	
	@GetMapping
	public String AdminRight() {
		return " ===> Hello Administrateur , attrapé par ton login : " + SecurityContextHolder.getContext().getAuthentication().getName() + "  => Que nous avons définit dans le fichier INITDB !";
	}

}
