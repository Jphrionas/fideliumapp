package br.com.alluminox.app.io.transform;

import java.io.Serializable;

import br.com.alluminox.app.data.model.Genero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String publicId;
	private String nome;
	private String email;
	private String password;
	private String passwordEncrypted;
	private String cpf;
	private String estado;
	private String dob;	
	private Genero genero;
	private String celular;
	private RoleDTO role;
	
	public UserDTO() {
		role = new RoleDTO();
	}
}
