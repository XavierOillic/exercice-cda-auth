package exercicecdaauth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import exercicecdaauth.model.Role;
import exercicecdaauth.model.User;
import exercicecdaauth.repository.RoleRepository;
import exercicecdaauth.repository.UserRepository;

@SpringBootTest
class ExerciceCdaAuthApplicationTests {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired 
	private RoleRepository roleRepo;
	
	@Autowired
	private MongoTemplate mongoTemp;
	
	@BeforeEach
	private void cleaning() {
		userRepo.deleteAll();
		roleRepo.deleteAll();
		
		Role utilisateur = roleRepo.save(new Role("Utilisateur"));
		Role administrateur = roleRepo.save(new Role("Administrateur"));
		Role webMaster = roleRepo.save(new Role("Webmaster"));
		Role developpeur = roleRepo.save(new Role("Développeur"));
		Role infographiste = roleRepo.save(new Role("Infographiste"));
		
		User xavier = userRepo.save(new User("Xavier", "Oillic", "MotdePasse"));
		xavier.getRoles().add(utilisateur);
		User jean = userRepo.save(new User("Jean", "Grey", "MotDePasse"));
		jean.getRoles().add(administrateur);
		User bruce = userRepo.save(new User("Bruce", "Wayne", "MotDePasse"));
		bruce.getRoles().add(webMaster);
		User martin = userRepo.save(new User("Martin", "Scorcese", "MotDePasse"));
		martin.getRoles().add(developpeur);
		User idriss = userRepo.save(new User("Idriss", "Elba", "MotDePasse"));
		idriss.getRoles().add(infographiste);
		
		userRepo.save(xavier);
	}
	
	

	@Test
	void creaUserTest() {
		//userRepo.save(new User("Paulo", "Paul", "bla bla"));
		assertEquals(5, userRepo.count()); // Ne prend en compte que celui du dessus.
		assertNotEquals(0, userRepo.count());
	}
	
	@Test
	void creaRoleTest() {
		//roleRepo.save(new Role("Administrateur"));
		assertEquals(5, roleRepo.count());
		assertNotEquals(0, roleRepo.count());
	}
	
	@Test
	void findAll() {
		for (User u : userRepo.findAll()) {
			System.out.println(u);
		}
	}
	
	@Test
	void queryTestfindByFirstName() {
		assertNotNull(1, userRepo.findByPrenom("Xavier").getPrenom());
		assertNotNull(1, userRepo.findByPrenom("Idriss").getPrenom());
	}
	@Test
	void queryTestfindByName() {
		assertNotNull(1, userRepo.findByNom("Scorcese").getPrenom());
		assertNotNull(1, userRepo.findByNom("Elba").getPrenom());
	}
	@Test
	void queryTestfindByNameNotEquals() {
		assertNotEquals("Marvel", userRepo.findByNom("Elba").getNom());
		assertNotEquals("Bliblablou", userRepo.findByNom("Wayne").getNom());
	}
	
	@Test
	void queryTestfindByLabel() {
		assertNotEquals("Marvel", userRepo.findByNom("Elba").getLogin());
		assertNotEquals("Bliblablou", userRepo.findByNom("Wayne").getLogin());
	}
	
	@Test
	void testTemplateQuery () {
		Query query = new Query();
		
		query.addCriteria(Criteria.where("Prenom").regex("Xavier").andOperator(Criteria.where("Oillique").ne(false)));
		assertEquals("Xavier", mongoTemp.find(query, User.class).get(0).getPrenom());
	}
	@Test
	void testTemplateQuery2 () {
		Query query = new Query();
		
		query.addCriteria(Criteria.where("Nom").regex("Grey").andOperator(Criteria.where("Grey").ne(true)));
		assertEquals("Grey", mongoTemp.find(query, User.class).get(0).getNom());
	}
	
	@Test
	void testRelation () {
		
		User ironMan = userRepo.save(new User("IronMan", "Stark", "MotDePasse"));
		
		Role r1 = roleRepo.save(new Role("role1"));
		Role r2 = roleRepo.save(new Role("role2"));
		// Je crée 2 rôles, que j'attribue à IronMan avec le getRoles et le ADD.
		
		ironMan.getRoles().add(r2);
		ironMan.getRoles().add(r1);
		
		userRepo.save(ironMan);
		// Je sauvegarde.
		
		assertEquals(2, userRepo.findByPrenom("IronMan").getRoles().size());
	}
	

}
