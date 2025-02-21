package com.practice.userAuth.repository;

import com.practice.userAuth.Model.ERole;
import com.practice.userAuth.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findById(Long id);

    Optional<Role> findByName(ERole name);
}
