package com.clinic.academicbridgedemo.Controllers;

import com.clinic.academicbridgedemo.Domain.Feedback;
import com.clinic.academicbridgedemo.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/api/feedback-entries")
@RestController
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveFeedback(@RequestParam UUID mentorshipSessionId, @RequestBody Feedback feedback){
        return feedbackService.saveFeedBack(mentorshipSessionId, feedback);
    }
    @GetMapping(value = "/all")
    public List<Feedback> getFeedback(){
        return feedbackService.getAllFeedBacks();
    }
    @GetMapping(value = "/{id}")
    public Optional<Feedback> getFeedbackById(UUID id){
        return feedbackService.getFeedbackById(id);
    }
    @DeleteMapping(value = "/delete/{id}")
    public String deleteFeedback(@PathVariable UUID id){
        return feedbackService.deleteFeedBack(id);
    }
    @PutMapping(value = "/update/{id}")
    public String updateFeedback(@PathVariable UUID id, @RequestBody Feedback feedback){
        return feedbackService.updateFeedBack(id, feedback);
    }
    @GetMapping(value = "/student")
    public List<Feedback> feedbackHistory(@RequestParam String regNumber){
        return feedbackService.getFeedbackHistory(regNumber);
    }

}
