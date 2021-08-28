package com.c4demo.dao;

import com.c4demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {

    User findByUsernameAmdPassword(@Param("username") String username, @Param("password") String password);

    int insertUser(User user);

    int findUser(@Param("username") String username);
}
