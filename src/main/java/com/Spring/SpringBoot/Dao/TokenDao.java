package com.Spring.SpringBoot.Dao;

import com.Spring.SpringBoot.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenDao extends JpaRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
