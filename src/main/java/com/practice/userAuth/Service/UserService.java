package com.practice.userAuth.Service;

import com.practice.userAuth.Model.User;

import java.util.List;

public interface UserService {

public User resitorUser(User user);

public boolean findUserName(String username);

public boolean findEmailId(String email);

List<User> getAllUser();

}
