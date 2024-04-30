package exercicecdaauth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import exercicecdaauth.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	
	Optional <Role> findByLabel(String label);

}
