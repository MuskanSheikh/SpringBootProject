package com.Spring.SpringBoot.Controller;

import com.Spring.SpringBoot.entity.Course;
import com.Spring.SpringBoot.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {
    @Autowired
    private CourseService courseService;


   //get all courses
   @GetMapping("/courses")
    public List<Course> getCourses()
    {
        return this.courseService.getCourses();
    }

    //get single course
    @GetMapping("/courses/{courseId}")
    public List<Course> getCourse(@PathVariable String courseId)
    {
        return this.courseService.getCourse(Long.parseLong(courseId));
    }

    //add course
    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course course)
    {
        return this.courseService.addCourse(course);
    }

    //delete course
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
