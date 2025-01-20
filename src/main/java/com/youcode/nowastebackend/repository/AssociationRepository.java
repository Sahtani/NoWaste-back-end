package com.youcode.nowastebackend.repository;

import com.youcode.nowastebackend.entity.Association;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AssociationRepository extends JpaRepository<Association, Long> {

    Optional<Association> findByName(String name);
}
