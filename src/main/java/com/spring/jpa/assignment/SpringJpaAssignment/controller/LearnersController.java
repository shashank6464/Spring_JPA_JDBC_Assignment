package com.spring.jpa.assignment.SpringJpaAssignment.controller;

import com.spring.jpa.assignment.SpringJpaAssignment.entity.Courses;
import com.spring.jpa.assignment.SpringJpaAssignment.entity.Learners;
import com.spring.jpa.assignment.SpringJpaAssignment.service.CoursesService;
import com.spring.jpa.assignment.SpringJpaAssignment.service.LearnersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/learners")
public class LearnersController {

    @Autowired
    LearnersService learnerService;

    @Autowired
    CoursesService cservice;


    // save => add learner
    @PostMapping
    public Learners addLearner(@RequestBody Learners learner) {
        return learnerService.addLearner(learner);
    }

    // get learner by id
    @GetMapping("/getLearnerById/{learner_id}")
    public Learners getLearnerById(@PathVariable int learner_id) {
        return learnerService.getLearnerById(learner_id);
    }

    // return all Learners => path (/learners)
    @GetMapping
    public List<Learners> getAllLearners() {
        return learnerService.getAllLearners();
    }

    // return number of total Learners
    @GetMapping("/getTotalLearners")
    public int getTotalLearners() {
        return learnerService.countLearners();
    }

    // delete learner by id
    @DeleteMapping("/deleteLearner/{learner_id}")
    public void deleteLearner(@PathVariable int learner_id) {
        learnerService.deleteLearner(learner_id);
    }

    // check if Learner exist
    @GetMapping("/checkLearnerExist/{learner_id}")
    public boolean checkLearnerExistence(@PathVariable int learner_id) {
        return learnerService.doesLearnerExists(learner_id);
    }

    // Put Mapping for grabbing Course object into Learner
    @PutMapping("/{learner_id}/courses/{course_id}")
    public Learners assignDetails(@PathVariable int learner_id, @PathVariable int course_id) {

        Courses course = cservice.getCourseById(course_id);

        //System.out.println(course);
        return learnerService.assignCourse(learner_id, course);
    }


    // Additional Queries -->

    @RequestMapping("/findByEmailAddressAndLastname")
    public List<Learners> findByEmailAddressAndLastname(@RequestBody Map<String, Object> fields) {
        return learnerService.findByEmailAddressAndLastname(fields.get("learner_email").toString(), fields.get("learner_last_name").toString());
    }

    @RequestMapping("/findDistinctLearnerByLastnameOrFirstname")
    public List<Learners> findDistinctLearnerByLastnameOrFirstname(@RequestBody Map<String, Object> fields) {
        return learnerService.findDistinctLearnerByLastnameOrFirstname(fields.get("learner_last_name").toString(), fields.get("learner_first_name").toString());
    }

    @RequestMapping("/findByLastnameIgnoreCase")
    public List<Learners> findByLastnameIgnoreCase(@RequestBody Map<String, Object> fields) {
        return learnerService.findByLastnameIgnoreCase(fields.get("learner_last_name").toString());
    }

    @RequestMapping("/findByLastnameOrderByFirstnameAsc")
    public List<Learners> findByLastnameOrderByFirstnameAsc(@RequestBody Map<String, Object> fields) {
        return learnerService.findByLastnameOrderByFirstnameAsc(fields.get("learner_last_name").toString());
    }

}