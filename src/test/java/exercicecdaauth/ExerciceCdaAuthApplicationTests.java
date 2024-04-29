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
import exercicecdaauth.model.Utilisateur;
import exercicecdaauth.repository.RoleRepository;
import exercicecdaauth.repository.UtilisateurRepository;

@SpringBootTest
class ExerciceCdaAuthApplicationTests {
	
	@Autowired
	private UtilisateurRepository userRepo;
	
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
		
		Utilisateur xavier = userRepo.save(new Utilisateur("Xavier", "Oillic", "MotdePasse"));
		xavier.getRoles().add(utilisateur);
		Utilisateur jean = userRepo.save(new Utilisateur("Jean", "Grey", "MotDePasse"));
		jean.getRoles().add(administrateur);
		Utilisateur bruce = userRepo.save(new Utilisateur("Bruce", "Wayne", "MotDePasse"));
		bruce.getRoles().add(webMaster);
		Utilisateur martin = userRepo.save(new Utilisateur("Martin", "Scorcese", "MotDePasse"));
		martin.getRoles().add(developpeur);
		Utilisateur idriss = userRepo.save(new Utilisateur("Idriss", "Elba", "MotDePasse"));
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
		for (Utilisateur u : userRepo.findAll()) {
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
		assertEquals("Xavier", mongoTemp.find(query, Utilisateur.class).get(0).getPrenom());
	}
	@Test
	void testTemplateQuery2 () {
		Query query = new Query();
		
		query.addCriteria(Criteria.where("Nom").regex("Grey").andOperator(Criteria.where("Grey").ne(true)));
		assertEquals("Grey", mongoTemp.find(query, Utilisateur.class).get(0).getNom());
	}
	
	@Test
	void testRelation () {
		
		Utilisateur ironMan = userRepo.save(new Utilisateur("IronMan", "Stark", "MotDePasse"));
		
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
