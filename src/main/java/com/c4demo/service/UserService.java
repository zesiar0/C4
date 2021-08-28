package com.c4demo.service;

import com.c4demo.entity.User;

public interface UserService {

    User checkUser(String username, String password);

    int insertUser(User user);
}
