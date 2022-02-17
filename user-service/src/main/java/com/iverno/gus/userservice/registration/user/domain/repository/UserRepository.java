package com.iverno.gus.userservice.registration.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iverno.gus.userservice.registration.user.domain.entity.UserEntity;


public interface UserRepository  extends JpaRepository<UserEntity, String> {

}
