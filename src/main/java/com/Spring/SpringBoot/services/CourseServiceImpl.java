package com.Spring.SpringBoot.services;

import com.Spring.SpringBoot.Dao.courseDao;
import com.Spring.SpringBoot.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private courseDao coursedao;

    @Override
    public List<Course> getCourses() {
        return coursedao.findAll();
    }

    @Override
    public Course addCourse(Course course) {
        return coursedao.save(course);

    }
    @Override
    public Course getCourse(long courseId) {
        return coursedao.findById(courseId).get();
    }
    @Override
    public Course updateCourse(Course course, long courseId) {

        coursedao.save(course);
        return course;
    }
    @Override
    public void deleteCourse(long courseId) {
        Course entity=coursedao.getOne(courseId);
        coursedao.delete(entity);
    }

}
