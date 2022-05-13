package com.Spring.SpringBoot.Dao;

import com.Spring.SpringBoot.entity.User;
import com.Spring.SpringBoot.entity.cartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface cartItemsDao extends JpaRepository<cartItems,Long> {
    public List<cartItems> findByUser(User user);
}
