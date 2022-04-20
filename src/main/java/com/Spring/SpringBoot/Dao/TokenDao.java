package com.Spring.SpringBoot.Dao;

import com.Spring.SpringBoot.entity.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;

public interface TokenDao extends CrudRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
