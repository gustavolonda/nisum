package com.iverno.gus.userservice.registration.phone.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.iverno.gus.userservice.registration.user.domain.entity.UserEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table
@Entity
public class PhoneEntity {
	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "text")
    private String  id;
	@NotBlank	
	@NotNull
	private String number;
	@NotBlank	
	@NotNull
	private String cityCode;
	@NotBlank	
	@NotNull 
	private String contryCode;

}
