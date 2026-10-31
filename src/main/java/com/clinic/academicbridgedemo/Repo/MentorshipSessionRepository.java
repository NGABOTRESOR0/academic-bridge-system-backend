package com.clinic.academicbridgedemo.Repo;

import com.clinic.academicbridgedemo.Domain.MentorshipSession;
import com.clinic.academicbridgedemo.Domain.SessionStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface MentorshipSessionRepository extends JpaRepository<MentorshipSession, UUID> {
    public List<MentorshipSession> findByStatusOrderBySessionDateAsc(SessionStatus status);
    public List<MentorshipSession> findBySessionDateBetweenOrderBySessionDateAsc(LocalDate start, LocalDate end);
    boolean existsByMentorIdAndSessionDateAndStatusNot(UUID mentorId, LocalDate sessionDate, SessionStatus status);
    long countByStatus(SessionStatus status);
    long countBySessionDate(LocalDate sessionDate);
    @Modifying
    @Transactional
    @Query("UPDATE MentorshipSession m SET m.status = com.clinic.academicbridgedemo.Domain.SessionStatus.DECLINED WHERE m.mentor.id =:mentorId AND m.sessionDate = :date AND m.status <> com.clinic.academicbridgedemo.Domain.SessionStatus.HELD AND m.status<> com.clinic.academicbridgedemo.Domain.SessionStatus.DECLINED")
    int declineByMentorAndDateExceptHeld(@Param("mentorId") UUID mentorID,@Param ("date") LocalDate sessionDate);
}
