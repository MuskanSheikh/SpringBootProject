package com.Spring.SpringBoot.services;
import com.Spring.SpringBoot.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CourseService {
    public Page<Course> getCourses(Pageable page);

    public Course getCourse(long courseId);

    public Course addCourse(Course course);

    public Course updateCourse(Course course, long courseId);

    public void deleteCourse(long courseId);


}
