package com.spring.jpa.assignment.SpringJpaAssignment.controller;

import com.spring.jpa.assignment.SpringJpaAssignment.entity.Courses;
import com.spring.jpa.assignment.SpringJpaAssignment.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CoursesController {

    @Autowired
    CoursesService courseService;


    // save ("/addCourse")
    @PostMapping
    public Courses addCourse(@RequestBody Courses course){
        return courseService.addCourse(course);
    }

    // get by ID
    @GetMapping("/getCourseById/{course_id}")
    public Courses getCourseById(@PathVariable int course_id){
        return courseService.getCourseById(course_id);
    }

    // return All Courses
    @GetMapping
    public List<Courses> getAllCourses(){
        return courseService.getAllCourses();
    }

    // return number Of Courses
    @GetMapping("/getTotalCourses")
    public int getTotalCourses(){
        return courseService.countCourses();
    }

    // delete course by ID
    @DeleteMapping("/deleteCourse/{course_id}")
    public void deleteCourse(@PathVariable int course_id){
        courseService.deleteCourse(course_id);
    }

    // 6.check Course exist
    @GetMapping("/checkCourseExist/{course_id}")
    public boolean checkCourseExistence(@PathVariable int course_id){
        return courseService.doesCourseExists(course_id);
    }
}