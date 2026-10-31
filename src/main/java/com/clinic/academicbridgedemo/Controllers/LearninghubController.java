package com.clinic.academicbridgedemo.Controllers;


import com.clinic.academicbridgedemo.Domain.LearningHub;
import com.clinic.academicbridgedemo.services.LearningHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/learning-hubs")
public class LearninghubController {
    @Autowired
    private LearningHubService learningHubService;
    @GetMapping(value = "/all")
    public List<LearningHub> getLearningHubs(){
        return learningHubService.getAllLearningHubs();
    }
    @GetMapping(value = "/free")
    public List<LearningHub> getFreeLearninghubs(){
        return learningHubService.getUnoccupiedLearningHubs();
    }
    @GetMapping(value = "/{id}")
    public Optional<LearningHub> getLearninghubById(@PathVariable UUID id){
        return learningHubService.findByid(id);
    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveLearningHub(@RequestBody LearningHub learningHub){
        return learningHubService.saveLearningHub(learningHub);
    }
    @PutMapping(value = "/update/{id}")
    public String updateLearningHub(@PathVariable UUID id,@RequestBody LearningHub update){
        learningHubService.updatelearninghub(id, update);
        return "The Learning hub was successfully updated";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteLearningHub(@PathVariable UUID id){
        return learningHubService.deletelearninghub(id);
    }
}
