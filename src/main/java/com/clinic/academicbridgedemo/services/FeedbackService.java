package com.clinic.academicbridgedemo.services;

import com.clinic.academicbridgedemo.Domain.Feedback;
import com.clinic.academicbridgedemo.Domain.MentorshipSession;
import com.clinic.academicbridgedemo.Domain.SessionStatus;
import com.clinic.academicbridgedemo.Repo.FeedbackRepository;
import com.clinic.academicbridgedemo.Repo.MentorshipSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private MentorshipSessionRepository mentorshipSessionRepository;
    private Feedback feedback;

    // Retrieval
    public List<Feedback> getAllFeedBacks(){
        return feedbackRepository.findAll();
    }
    public List<Feedback> getFeedbackHistory(String regNumber){
        return feedbackRepository.findByMentorshipSession_Student_RegNumberOrderByGivenDateDesc(regNumber);
    }
    //Saving
    public String saveFeedBack(UUID mentorshipSessionId, Feedback feedback){
        MentorshipSession mentorshipSession = mentorshipSessionRepository.findById(mentorshipSessionId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"The mentorship session with that id does not exist"));
        if (mentorshipSession.getStatus() != SessionStatus.HELD){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Feedback entries are only for HELD mentorship sessions");
        }

        feedback.setGivenDate(LocalDate.now());
        feedback.setMentorshipSession(mentorshipSession);
        feedbackRepository.save(feedback);
        return "Feedback saved successfully";
    }
    //Delete
    public String deleteFeedBack(UUID id){
        Feedback feedback = feedbackRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"The feedback with that id does not exist"));
        feedbackRepository.deleteById(id);
        return "Feedback deleted successfully";
    }
    //Update
    public String updateFeedBack(UUID id, Feedback feedback) {
        Feedback feedback1 = feedbackRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The feedback with that id does not exist"));

        feedback1.setComment(feedback.getComment());
        feedback1.setScore(feedback.getScore());
        feedback1.setSkillAssessed(feedback.getSkillAssessed());
        feedbackRepository.save(feedback1);
        return "Feedback updated successfully";
    }
}
