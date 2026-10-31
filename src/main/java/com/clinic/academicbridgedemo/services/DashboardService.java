package com.clinic.academicbridgedemo.services;

import com.clinic.academicbridgedemo.Domain.SessionStatus;
import com.clinic.academicbridgedemo.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
@Service
public class DashboardService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private MentorshipSessionRepository mentorshipSessionRepository;
    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private LearningHubRepository learningHubRepository;
    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;

    public Map<String , Long> getSummary(){
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("students", studentRepository.count());
        summary.put("mentors",mentorRepository.count());
        summary.put("freehubs",(long)learningHubRepository.findFreeLearningHubs().size());
        summary.put("sessionsToday",mentorshipSessionRepository.countBySessionDate(LocalDate.now()));
        summary.put("requested",mentorshipSessionRepository.countByStatus(SessionStatus.REQUESTED));
        summary.put("feedbackEntries",feedbackRepository.count());
        return summary;
    }
}
