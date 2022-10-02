package com.spring.jpa.assignment.SpringJpaAssignment.service;

import com.spring.jpa.assignment.SpringJpaAssignment.entity.Courses;
import com.spring.jpa.assignment.SpringJpaAssignment.entity.Learners;
import com.spring.jpa.assignment.SpringJpaAssignment.repository.LearnersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LearnersService {

    @Autowired
    LearnersRepository learnerRepo;


    // Saves the learner => add a new learner
    public Learners addLearner(Learners learner){
        return learnerRepo.save(learner);
    }

    // Return the learner with given id
    public Learners getLearnerById(int learner_id){
        Optional<Learners> tempLearner = learnerRepo.findById(learner_id);

        if(tempLearner.isEmpty()) throw new RuntimeException("Learner with id : "+learner_id+" NOT FOUND !!");

        return tempLearner.get();
    }

    // Returns all learners => total rows
    public List<Learners> getAllLearners(){
        return learnerRepo.findAll();
    }


    // Return total count of Learners
    public int countLearners(){
        return (int) learnerRepo.count();
    }


    // Deletes a learner by ID
    public void deleteLearner(int learner_id){
        Optional<Learners> tempLearner = learnerRepo.findById(learner_id);

        if(tempLearner.isEmpty()) throw new RuntimeException("Learner with id : "+learner_id+" NOT FOUND !!");
        learnerRepo.delete(tempLearner.get());
    }

    // whether a learner with given ID exists or not
    public boolean doesLearnerExists(int learner_id){

        Optional<Learners> tempLearner = learnerRepo.findById(learner_id);

        if(tempLearner.isEmpty()) return false;
        return true;
    }

    // Grabbing Course object into Learner)
    public Learners assignCourse(int learner_id, Courses course){
        Learners learner = learnerRepo.findById(learner_id).get();

        learner.setCourse(course);

        return learnerRepo.save(learner);
    }



    //Additional QUERIES -->

    // 1 =>
    @Transactional
    public List<Learners> findByEmailAddressAndLastname(String learner_email, String learner_last_name){
        return learnerRepo.findByEmailAddressAndLastname(learner_email, learner_last_name);
    }

    // 2 =>
    @Transactional
    public List<Learners> findDistinctLearnerByLastnameOrFirstname(String learner_last_name, String learner_first_name){
        return learnerRepo.findDistinctLearnerByLastnameOrFirstname(learner_last_name, learner_first_name);
    }

    // 3 =>
    @Transactional
    public List<Learners> findByLastnameIgnoreCase(String learner_last_name){
        return learnerRepo.findByLastnameIgnoreCase(learner_last_name);
    }

    // 4 =>
    @Transactional
    public List<Learners> findByLastnameOrderByFirstnameAsc(String learner_last_name){
        return learnerRepo.findByLastnameOrderByFirstnameAsc(learner_last_name);
    }

}
