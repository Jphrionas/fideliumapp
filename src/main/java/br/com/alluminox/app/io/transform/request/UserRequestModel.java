package br.com.alluminox.app.io.transform.request;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.alluminox.app.data.model.Genero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class UserRequestModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String publicId;
	
	@NotEmpty(message = "This field is mandatory")
	private String nome;
	
	@NotEmpty(message = "This field is mandatory")
	@Email(message="This e-mail is not valid, example: email@email.com")
	private String email;
	
	@NotBlank(message = "This field is mandatory")
	private String password;
	
	@NotBlank(message="This field is mandatory")
	private String cpf;
	
	@NotBlank(message="This field is mandatory")
	private String estado;
	
	@NotBlank(message="This field is mandatory")
	private String dob;
	
	@NotNull(message="This field is mandatory")
	private Genero genero;
	
	@NotBlank(message="This field is mandatory")
	private String celular;
	
	@NotNull(message="This field is mandatory")
	private RoleRequestModel role;
	
	public UserRequestModel() {
		this.role = new RoleRequestModel();
	}
}
