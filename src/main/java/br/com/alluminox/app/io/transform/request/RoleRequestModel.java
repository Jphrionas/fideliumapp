package br.com.alluminox.app.io.transform.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleRequestModel implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	private String publicId;
	private String nome;
}
