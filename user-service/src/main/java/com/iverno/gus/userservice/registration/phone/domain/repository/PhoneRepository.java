package com.iverno.gus.userservice.registration.phone.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iverno.gus.userservice.registration.phone.domain.entity.PhoneEntity;
@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, String> {

}
