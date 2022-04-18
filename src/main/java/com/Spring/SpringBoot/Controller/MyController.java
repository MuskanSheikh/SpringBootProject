package com.Spring.SpringBoot.Controller;

import com.Spring.SpringBoot.Dao.courseDao;
import com.Spring.SpringBoot.entity.Course;
import com.Spring.SpringBoot.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private CourseService courseService;
    @Autowired
    private courseDao courseD;
    //get all courses
   @GetMapping("/courses")
   public List<Course> getCourses()
   {

       return this.courseService.getCourses();
   }
    @GetMapping("/coursesTD")
    public ResponseEntity<Map<String, Object>> getAllByTitleAndDescription(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try {
            List<Course> course = new ArrayList<Course>();
            Pageable paging = PageRequest.of(page, size);

            Page<Course> pageC;
            if (title == null && description==null)
                pageC = courseD.findAll(paging);
            else
                pageC = courseD.findByTitleOrDescriptionContaining(title,description, paging);
            course = pageC.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("course", course);
            response.put("currentPage", pageC.getNumber());
            response.put("totalItems", pageC.getTotalElements());
            response.put("totalPages", pageC.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
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
    public ResponseEntity<Course> addCourse(@RequestBody Course course)
    {
        return new ResponseEntity<>(courseService.addCourse(course),
                HttpStatus.OK);
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
