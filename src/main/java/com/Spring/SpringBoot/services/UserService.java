package com.Spring.SpringBoot.services;

import com.Spring.SpringBoot.Dao.UserDao;
import com.Spring.SpringBoot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public List<User> getUsers() {
        return userDao.findAll();
    }

    public User createUser(User user)
    {
        String encodedPassword=this.bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userDao.save(user);
    }
}
