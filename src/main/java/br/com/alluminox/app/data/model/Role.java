package br.com.alluminox.app.data.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;

import br.com.alluminox.app.util.GeneratorPublicId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role implements Serializable, GrantedAuthority {
	private static final long serialVersionUID = 1L;
	
	
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString());
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name="role_id")
	private Long id;
	
	@Column(name="public_id")
	private String publicId;
	
	private String nome;
	
	@Column(name="registration_date", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyy-MM-dd")
	private Date registrationDate;
	
	private Boolean disabled = false;
	
	@PrePersist
	public void prePersist() {
		this.registrationDate = new Date();
		this.publicId = GeneratorPublicId.random();
	}
	
	@PreUpdate
	public void preUpdate() {}

	@Override
	public String getAuthority() {
		return nome;
	}
	
}
