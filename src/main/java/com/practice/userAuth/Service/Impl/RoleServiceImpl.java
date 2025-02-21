package com.practice.userAuth.Service.Impl;

import com.practice.userAuth.Model.ERole;
import com.practice.userAuth.Model.Role;
import com.practice.userAuth.Service.RoleService;
import com.practice.userAuth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(ERole rName) {
        return roleRepository.findByName(rName);
    }

    @Override
    public Optional<Role> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Role> getAllRole() {
        return null;
    }
}
