package com.spring.jpa.assignment.SpringJpaAssignment.service;

import com.spring.jpa.assignment.SpringJpaAssignment.entity.Courses;
import com.spring.jpa.assignment.SpringJpaAssignment.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursesService {

    @Autowired
    CoursesRepository courseRepo;


    // Saves => add the course
    public Courses addCourse(Courses course){

        return courseRepo.save(course);
    }

    // Return the course with given id => Get course by ID
    public Courses getCourseById(int course_id){

        Optional<Courses> tempCourse = courseRepo.findById(course_id);
        if(tempCourse.isEmpty()) throw new RuntimeException("Learner with id : "+course_id+" NOT FOUND !!");
        return tempCourse.get();
    }

    // Returns all Courses
    public List<Courses> getAllCourses(){
        return courseRepo.findAll();
    }


    // Return total number of Courses => COUNT
    public int countCourses(){
        return (int) courseRepo.count();
    }


    // Deletes a course by Course ID
    public void deleteCourse(int course_id){
        Optional<Courses> tempCourse = courseRepo.findById(course_id);

        if(tempCourse.isEmpty()) throw new RuntimeException("Course with id : "+course_id+" NOT FOUND !!");
        courseRepo.delete(tempCourse.get());
    }


    // whether a course with given ID exists or not
    public boolean doesCourseExists(int course_id){

        Optional<Courses> tempCourse = courseRepo.findById(course_id);

        if(tempCourse.isEmpty()) return false;
        return true;

    }


}