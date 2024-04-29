package exercicecdaauth.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodb.client.MongoDatabase;

import exercicecdaauth.model.User;

public interface UserRepository extends MongoRepository<User, String>{
	
	User findByLogin (String login);
	User findByNom (String nom);
	User findByPrenom (String prenom);

}
