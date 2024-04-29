package exercicecdaauth.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserController {
	
	@GetMapping
	public String utilisateur() {
		return "Hello user" + SecurityContextHolder.getContext().getAuthentication().getName()+"!";
	}

}
