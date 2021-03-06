package com.iverno.gus.userservice.registration.phone.domain.entity;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.iverno.gus.commonservice.endpoint.domain.entity.BaseGeneralEntity;
import com.iverno.gus.userservice.registration.user.domain.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "phones")
@Entity
public class PhoneEntity extends BaseGeneralEntity{
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", columnDefinition = "varchar(255)")
	private String id;
	@NotBlank
	@NotNull
	private String number;
	@NotBlank
	@NotNull
	@Column(name = "city_code")
	private String cityCode;
	@NotBlank
	@NotNull
	@Column(name = "contry_code")
	private String contryCode;
	@NotBlank
	@NotNull
	@Column(name = "user_id")
	private String userId;

}
