package com.Spring.SpringBoot.Controller;

import com.Spring.SpringBoot.Dao.courseDao;
import com.Spring.SpringBoot.entity.Course;
import com.Spring.SpringBoot.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MyController {

    @Autowired
    courseDao coursedao;

    @Autowired
    private CourseService courseService;


   //get all courses
   @GetMapping("/courses")
    public ResponseEntity<Map<String, Object>> getCourses(
            @RequestParam(required = false) String title,
            @RequestParam() int page,
            @RequestParam() int size
   ){
        try{
            List<Course> course=new ArrayList<Course>();
            Pageable paging= PageRequest.of(page, size);

            Page<Course> pageC;
            if(title==null)
                pageC=coursedao.findAll(paging);
            else
                pageC=coursedao.findByTitle(title,paging);
            course=pageC.getContent();
           // Map<String, Object> response=new HashMap<>();
            //response.put("course",course);
            //response.put("current page", pageC.getNumber());
            //response.put("total course", pageC.getTotalElements());
            //response.put("total pages", pageC.getTotalPages());
            return new ResponseEntity<>(null,HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //get single course
    @GetMapping("/courses/{courseId}")
    public Course getCourse(@PathVariable Long courseId)
    {
        return this.courseService.getCourse(courseId);
    }

    //add course
    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course course)
    {
        return this.courseService.addCourse(course);
    }

    //delete course handler
    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String courseId)
    {
        try {
            this.courseService.deleteCourse(Long.parseLong(courseId));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //update course
    @PutMapping("/courses/{courseId}")
    public Course updateCourse(@RequestBody Course course,@PathVariable String courseId)
    {
       this.courseService.updateCourse(course,Long.parseLong(courseId));
       return course;
    }


}
