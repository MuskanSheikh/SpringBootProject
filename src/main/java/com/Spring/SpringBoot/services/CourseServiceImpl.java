package com.Spring.SpringBoot.services;

import com.Spring.SpringBoot.Dao.courseDao;
import com.Spring.SpringBoot.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private courseDao coursedao;



    //get all course
    @Override
    public List<Course> getCourses() {
        return coursedao.findAll();
    }

    // @Override
    //public List<Course> getCourses()
    //{
      //  return coursedao.findAll();
    //}

    @Override
    public Course addCourse(Course course) {
//        list.add(course);
        return coursedao.save(course);

    }


    //get single course
    @Override
    public Course getCourse(long courseId) {
//        return list.stream().filter(g->g.getId()==courseId).collect(Collectors.toList());
       /*Course c = null;
        for (Course course : list) {
            if (course.getId() == courseId) {
                c = course;
                break;
            }
        }*/return coursedao.findById(courseId).get();
    }



    @Override
    public Course updateCourse(Course course, long courseId) {

        coursedao.save(course);
        return course;
//       list=list.stream().map(b->{
//           if (b.getId() == courseId) {
//               b.setTitle(course.getTitle());
//               b.setDescription(course.getDescription());
//           }
//           return b;
//       }).collect(Collectors.toList());

    }

    @Override
    public void deleteCourse(long courseId) {
//        list=list.stream().filter(d->d.getId()!=courseId).collect(Collectors.toList());
        Course entity=coursedao.getOne(courseId);
        coursedao.delete(entity);
    }

}
