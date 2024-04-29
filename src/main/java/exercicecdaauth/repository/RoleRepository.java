package exercicecdaauth.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;

import exercicecdaauth.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	
	Optional <Role> findByLabel(String label);
	
	Role findByLabelEquals(String label);

}
