package com.fullstack.backend.repositories;

import com.fullstack.backend.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {

    List<Roles> findByProfileId(Long id);
}
