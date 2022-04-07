package com.Spring.SpringBoot.services;
import com.Spring.SpringBoot.entity.Course;

import java.util.List;

public interface CourseService {
    public List<Course> getCourses();

    public Course getCourse(long courseId);

    public Course addCourse(Course course);

    public void updateCourse(Course course, long courseId);

    public void deleteCourse(int courseId);
}
