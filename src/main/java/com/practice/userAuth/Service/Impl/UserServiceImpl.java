package com.practice.userAuth.Service.Impl;

import com.practice.userAuth.Model.User;
import com.practice.userAuth.Service.UserService;
import com.practice.userAuth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User resitorUser(User user) {
        return null;
    }

    @Override
    public boolean findUserName(String username) {
        return userRepository.existsByUsername(username) ;
    }

    @Override
    public boolean findEmailId(String email) {
        return userRepository.existsByEmail(email);
    }


}
