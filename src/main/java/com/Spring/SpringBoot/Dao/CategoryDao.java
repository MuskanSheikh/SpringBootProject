package com.Spring.SpringBoot.Dao;

import com.Spring.SpringBoot.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Long> {

}
