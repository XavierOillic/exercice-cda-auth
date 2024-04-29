package exercicecdaauth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodb.client.MongoDatabase;

import exercicecdaauth.model.Utilisateur;

public interface UtilisateurRepository extends MongoRepository<Utilisateur, String>{
	
	Optional<Utilisateur> findByLogin (String login);
	
	Utilisateur findByNom (String nom);
	Utilisateur findByPrenom (String prenom);

}
