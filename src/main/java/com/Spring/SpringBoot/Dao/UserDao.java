package com.Spring.SpringBoot.Dao;

import com.Spring.SpringBoot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

}
