package com.Spring.SpringBoot.Dao;

import com.Spring.SpringBoot.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public interface UserDao extends JpaRepository<User, Long> {
   // User findByUsername(String username);
    User findByEmailIgnoreCase(String email);
    Page<User> findByEmailContaining (String email, Pageable pageable);

    public User findByResetPasswordToken(String token);
}
