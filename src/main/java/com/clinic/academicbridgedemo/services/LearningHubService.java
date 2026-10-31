package com.clinic.academicbridgedemo.services;

import com.clinic.academicbridgedemo.Domain.LearningHub;
import com.clinic.academicbridgedemo.Repo.LearningHubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class LearningHubService {
    @Autowired
    private LearningHubRepository learningHubRepository;

    public List<LearningHub> getAllLearningHubs(){
        return learningHubRepository.findAll();
    }
    public List<LearningHub> getUnoccupiedLearningHubs(){
        return learningHubRepository.findFreeLearningHubs();
    }
    public Optional<LearningHub> findByid(UUID id){
        LearningHub learningHub = learningHubRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The learning hub with that ID doesn't exist"));
        return learningHubRepository.findById(id);
    }
    public String saveLearningHub(LearningHub learningHub){
        learningHubRepository.save(learningHub);
        return "Successfully created the learning hub";
    }
    public LearningHub updatelearninghub(UUID id, LearningHub update){
        LearningHub learningHub = learningHubRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The learning hub with that ID doesn't exist"));
        learningHub.setName(update.getName());
        learningHub.setHubNumber(update.getHubNumber());
        return learningHubRepository.save(learningHub);
    }
    public String deletelearninghub(UUID id){
        if (!learningHubRepository.existsById(id)){
            throw new IllegalArgumentException("The learning hub with that ID doesn't exist");
        }else learningHubRepository.deleteById(id);
        return "Learning hub deleted successfully";
    }

    public static class FieldRepo {
    }
}
