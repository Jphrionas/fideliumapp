package br.com.alluminox.app.io.transform.response;

import java.io.Serializable;

import br.com.alluminox.app.data.model.Genero;
import br.com.alluminox.app.data.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String publicId;
	private String nome;
	private String email;	
	private String celular;
	private String cpf;
	private String estado;
	private String dob;
	private Genero genero;	
	private Role role;
}
