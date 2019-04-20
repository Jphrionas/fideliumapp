package br.com.alluminox.app.data.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.alluminox.app.util.GeneratorPublicId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class User implements UserDetails, Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name="user_id")
	private Long id;
	
	@Column(name="public_id")
	private String publicId;
	
	@Column(name="registration_date", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyy-MM-dd")
	private Date registrationDate = new Date();
		
	private String nome;
	private String email;
	
	@Column(name="pass")
	private String password;
	
	private String celular;
	private String cpf;
	private String estado;
	private String dob;
	
	@Enumerated(EnumType.STRING)
	private Genero genero;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="user_level")
	private Role role;
	
	@Column(columnDefinition="boolean default false")
	private Boolean disabled = false;
		
	@PrePersist
	public void prePersist() {
		this.registrationDate = new Date();
		this.publicId = GeneratorPublicId.random();
	}
	
	@PreUpdate
	public void preUpdate() {}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(role);
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return this.password;
	}
}
