package xyz.mhmm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.core.sym.Name;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude="user")
@Entity
@Table(name = "tb_login")
@EqualsAndHashCode(of="no")
public class Login {

	@Id
	private Long no;

	@MapsId
	@OneToOne
	private User user;

	@NotNull
	@Column(length = 20, nullable = false)
	private String id;

	@NotNull
	@Column(length = 100, nullable = false)
	private String pw;

}
