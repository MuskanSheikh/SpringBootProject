package com.Spring.SpringBoot.services;

import com.Spring.SpringBoot.entity.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    List<Course> list;

    public CourseServiceImpl() {
        list = new ArrayList<>();
        list.add(new Course(123, "java core course", "this course is related to java"));
        list.add(new Course(548, "Springboot advance", "this course contain advance java"));

    }

    @Override
    public List<Course> getCourses() {
        return list;
    }

    @Override
    public Course getCourse(long courseId) {
        Course c = null;
        for (Course course : list) {
            if (course.getId() == courseId) {
                c = course;
                break;
            }
        }
        return c;
    }

    @Override
    public Course addCourse(Course course) {
        list.add(course);
        return course;
    }

    @Override
    public void updateCourse(Course course, long courseId)
    {
       list=list.stream().map(b->{
           if (b.getId() == courseId) {
               b.setTitle(course.getTitle());
               b.setDescription(course.getDescription());
           }
           return b;
       }).collect(Collectors.toList());

    }

    @Override
    public void deleteCourse(int courseId) {
        list=list.stream().filter(d->d.getId()!=courseId).collect(Collectors.toList());
    }
}
