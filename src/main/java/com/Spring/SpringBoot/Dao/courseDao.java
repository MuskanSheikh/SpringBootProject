package com.Spring.SpringBoot.Dao;

import com.Spring.SpringBoot.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface courseDao extends JpaRepository<Course, Long> {
    Page<Course> findByTitleOrDescriptionContaining( String title,String description, Pageable pageable);
    //Page<Course> findByDescriptionContaining( String description, Pageable pageable);
}
