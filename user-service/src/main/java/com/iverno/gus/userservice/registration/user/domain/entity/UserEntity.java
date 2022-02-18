package com.iverno.gus.userservice.registration.user.domain.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.iverno.gus.commonservice.endpoint.domain.entity.BaseGeneralEntity;
import com.iverno.gus.userservice.registration.phone.domain.entity.PhoneEntity;


@Table
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
	@NotBlank
	@NotNull
	private String email;
	@OneToMany
    @JoinColumn(name = "fk_userphone")
    private Set<PhoneEntity> phones = new HashSet<PhoneEntity>();
	@Column(name = "last_login", columnDefinition = "timestamp NULL")
    private Timestamp lastLogin;
	
}
