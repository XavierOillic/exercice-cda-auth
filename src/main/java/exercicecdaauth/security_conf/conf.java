package exercicecdaauth.security_conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import exercicecdaauth.utils.MethodeMaisonAuthentication;

@Configuration
@EnableMethodSecurity
public class conf {
	
	@Autowired
	private MethodeMaisonAuthentication methodeAuth;
	
	@Bean
	AuthenticationManager authManager (HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(methodeAuth);
		return authenticationManagerBuilder.build();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		//@formatter:off
		
		http
		// désactivation de certaines fonctionnalités mise en place par défaut :
		.csrf(csrf -> csrf.disable())
		.formLogin(formlogin -> formlogin.disable())
		.logout(logout -> logout.disable())
		.httpBasic(Customizer.withDefaults())
		.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests((authorize) -> {authorize.anyRequest().authenticated();});
		
		//@formatter:on
		return http.build();
	}
	
}
