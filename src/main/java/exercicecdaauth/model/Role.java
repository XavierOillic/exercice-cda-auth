package exercicecdaauth.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Document
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Role {

	@Id
	private String id;
	
	@NonNull
	@Field("T'es qui toi ?")
	private String label;
	
}

