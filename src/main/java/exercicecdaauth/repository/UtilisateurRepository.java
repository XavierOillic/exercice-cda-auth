package exercicecdaauth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import exercicecdaauth.model.Utilisateur;

public interface UtilisateurRepository extends MongoRepository<Utilisateur, String>{
	
	Optional<Utilisateur> findByLogin (String login);
	// On trouve une valeur, OU PAS, l'OPTIONAL contient des méthodes pour gérer les cas particuliers.
	
	Utilisateur findByNom (String nom);
	Utilisateur findByPrenom (String prenom);

}
