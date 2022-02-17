package com.iverno.gus.userservice.registration.phone.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iverno.gus.userservice.registration.phone.domain.entity.PhoneEntity;

public interface PhoneRepository extends JpaRepository<PhoneEntity, String> {

}
