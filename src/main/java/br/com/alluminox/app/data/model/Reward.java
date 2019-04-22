package br.com.alluminox.app.data.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.validation.constraints.NotNull;

import br.com.alluminox.app.util.GeneratorPublicId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@Entity
@Table(name="reward")
public class Reward implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reward_id")
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotNull(message="This field is mandatory")
	@Column(name="public_id")
	private String publicId;
	
	@NotNull(message="This field is mandatory")
	@Column(name="reward_type")
	private String rewardType;
	
	private String descricao;

	@NotNull(message="This field is mandatory")
	@Column(name="qtd_pontos")
	private Integer quantidadePontos;
	
//	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="expiration_date")
	private Date expirationDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="registration_date")
	private Date registrationDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date")
	private Date updateDate;
	
	@Column(columnDefinition="boolean default false")
	private boolean disabled = false;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@PrePersist
	public void prePersist() {
		this.registrationDate = new Date();
		this.expirationDate = new Date();
		this.updateDate = new Date();
		this.publicId = GeneratorPublicId.random();
	}
	
	@PreUpdate
	public void preUpdate() {
		this.updateDate = new Date();
	}
	

}
