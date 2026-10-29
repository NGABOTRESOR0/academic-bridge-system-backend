package com.clinic.academicbridgedemo.Repo;

import com.clinic.academicbridgedemo.Domain.Feedback;
import com.clinic.academicbridgedemo.Domain.MentorshipSession;
import com.clinic.academicbridgedemo.Domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {
    boolean existsByMentorshipSession(MentorshipSession mentorshipSession);
    public List<Feedback> findByMentorshipSession_Student_RegNumberOrderByGivenDateDesc(String regNumber);
}
