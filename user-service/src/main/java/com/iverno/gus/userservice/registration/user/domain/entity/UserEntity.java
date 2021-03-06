package com.iverno.gus.userservice.registration.user.domain.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.iverno.gus.commonservice.endpoint.domain.entity.BaseGeneralEntity;
import com.iverno.gus.userservice.registration.phone.domain.entity.PhoneEntity;
import static com.iverno.gus.userservice.registration.user.confing.Constraints.EMAIL_PATTERN;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
public class UserEntity extends BaseGeneralEntity {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", columnDefinition = "varchar(255)")
	private String id;
	@NotBlank
	@NotNull
	private String name;
	@NotBlank
	@NotNull
	private String password;
	@Email(regexp = EMAIL_PATTERN)
	@NotBlank
	@NotNull
	@Column(unique = true)
	private String email;
	@NotBlank
	@NotNull
	private String role;
	@OneToMany(
    	    cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private List<PhoneEntity> phones = new ArrayList<>();
	@Column(name = "last_login", columnDefinition = "timestamp NULL")
    private Timestamp lastLogin;
	
}
