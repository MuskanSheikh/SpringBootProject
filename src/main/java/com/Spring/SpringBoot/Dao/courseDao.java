package com.Spring.SpringBoot.Dao;

import com.Spring.SpringBoot.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface courseDao extends JpaRepository<Course, Long> {
    Page<Course> findByTitle(String title, Pageable pageable);


}
