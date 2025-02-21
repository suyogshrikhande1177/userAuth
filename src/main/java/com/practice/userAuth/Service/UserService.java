package com.practice.userAuth.Service;

import com.practice.userAuth.Model.User;

public interface UserService {

public User resitorUser(User user);

public boolean findUserName(String username);

public boolean findEmailId(String email);

}
