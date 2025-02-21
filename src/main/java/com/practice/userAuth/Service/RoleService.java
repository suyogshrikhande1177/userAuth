package com.practice.userAuth.Service;

import com.practice.userAuth.Model.ERole;
import com.practice.userAuth.Model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Optional<Role> findByName(ERole rName);
    Optional<Role> findById(Integer id);

    List<Role> getAllRole();
}
