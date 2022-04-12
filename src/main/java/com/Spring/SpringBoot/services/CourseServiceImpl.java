package com.Spring.SpringBoot.services;

import antlr.StringUtils;
import com.Spring.SpringBoot.Dao.courseDao;
import com.Spring.SpringBoot.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Access;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private courseDao coursedao;
//    List<Course> list;
    public CourseServiceImpl() {
        /*list = new ArrayList<>();
        list.add(new Course(123, "java core course", "this course is related to java"));
        list.add(new Course(548, "Springboot advance", "this course contain advance java"));
*/
    }

    //get all course
    @Override
    public List<Course> getCourses()
    {
        return coursedao.findAll();
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
    public Course addCourse(Course course) {
//        list.add(course);
        return coursedao.save(course);

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
