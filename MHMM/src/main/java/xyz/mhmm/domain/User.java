package xyz.mhmm.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(exclude="login")
@Entity
@Table(name = "tb_user")
@EqualsAndHashCode(of = "no")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@Column(length = 15)
	private String phone;

	@Email
	@NotNull
	@Column(length = 50)
	private String email;

	@NotNull
	@Column(length = 10, nullable = false)
	private String name;

	@NotNull
	@Column(nullable = false)
	private char sex;

	
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY, mappedBy = "user")
	private Login login;

}
