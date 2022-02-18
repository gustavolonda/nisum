package com.iverno.gus.userservice.registration.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iverno.gus.userservice.registration.user.domain.entity.UserEntity;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, String> {

}
