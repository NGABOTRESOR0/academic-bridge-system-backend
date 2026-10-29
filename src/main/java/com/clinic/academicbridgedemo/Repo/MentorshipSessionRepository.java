package com.clinic.academicbridgedemo.Repo;

import com.clinic.academicbridgedemo.Domain.MentorshipSession;
import com.clinic.academicbridgedemo.Domain.SessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MentorshipSessionRepository extends JpaRepository<MentorshipSession, UUID> {
    public List<MentorshipSession> findByStatusOrderBySessionDateAsc(SessionStatus status);
    public List<MentorshipSession> findBySessionDateBetweenOrderBySessionDateAsc(LocalDate start, LocalDate end);
    boolean existsByMentorIdAndSessionDateAndStatusNot(UUID mentorId, LocalDate sessionDate, SessionStatus status);
}
