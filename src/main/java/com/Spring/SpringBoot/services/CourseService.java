package com.Spring.SpringBoot.services;

import com.Spring.SpringBoot.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CourseService {

    public List<Course> getCourses();


    public Course getCourse(long courseId);

    public Course addCourse(Course course);

    public Course updateCourse(Course course, long courseId);

    public void deleteCourse(long courseId);


}
