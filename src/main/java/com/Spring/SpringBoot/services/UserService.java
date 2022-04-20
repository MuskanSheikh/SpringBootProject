package com.Spring.SpringBoot.services;

import com.Spring.SpringBoot.Dao.UserDao;
import com.Spring.SpringBoot.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class UserService {

    UserDao userDao;
    PasswordEncoder passwordEncoder;

    public UserService(UserDao userDao) {
        this.userDao=userDao;
        this.passwordEncoder=new BCryptPasswordEncoder();
    }

    public User signUp(User user)
    {
        String encodedPassword=this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userDao.save(user);
    }
}
