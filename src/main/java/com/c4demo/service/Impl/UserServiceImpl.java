package com.c4demo.service.Impl;

import com.c4demo.dao.UserDao;
import com.c4demo.entity.User;
import com.c4demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        User user = userDao.findByUsernameAmdPassword(username, password);
        return user;
    }

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

}
