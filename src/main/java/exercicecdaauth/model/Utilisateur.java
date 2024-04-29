package exercicecdaauth.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Document
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Utilisateur {
	
	@Id
	private String login;
	
	@NonNull
	@Field("Prénom :")
	private String prenom;
	
	@NonNull
	@Field("Nom :")
	private String nom;
	
	@DocumentReference
	//@NonNull ==> Je fais le .GET().ADD("") donc pas besoin du @NonNull.
	private Set<Role> roles = new HashSet<>();
	
	@NonNull
	private String PasswordHash;

}
