package br.com.alluminox.app.io.transform.request;

import java.io.Serializable;

import br.com.alluminox.app.data.model.Genero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class UserRequestModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String email;
	private String password;
	private String cpf;
	private String estado;
	private String dob;
	private Genero genero;
	private String celular;
	private RoleRequestModel role;
	
	public UserRequestModel() {
		this.role = new RoleRequestModel();
	}
}
