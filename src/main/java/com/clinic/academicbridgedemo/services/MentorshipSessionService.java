package com.clinic.academicbridgedemo.services;

import com.clinic.academicbridgedemo.Domain.MentorshipSession;
import com.clinic.academicbridgedemo.Domain.SessionStatus;
import com.clinic.academicbridgedemo.Repo.MentorshipSessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class MentorshipSessionService {
    @Autowired
    private MentorshipSessionRepository mentorshipSessionRepository;

    public String saveMentorshipSession(MentorshipSession mentorshipSession){
        if (mentorshipSession.getSessionDate().isBefore(LocalDate.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Mentorship session date cannot be in the past");
        }if (mentorshipSessionRepository.existsByMentorIdAndSessionDateAndStatusNot(mentorshipSession.getMentor().getId(),mentorshipSession.getSessionDate(),SessionStatus.DECLINED)){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Mentor already has a session on that date");
        }
        mentorshipSession.setStatus(SessionStatus.REQUESTED);
        mentorshipSessionRepository.save(mentorshipSession);
        return "Mentorship session saved successfully";
    }
    public List<MentorshipSession> getMentorshipSessions(){
        return mentorshipSessionRepository.findAll();
    }


    public List<MentorshipSession> getMentorshipSessionsByStatus(SessionStatus status){
        return mentorshipSessionRepository.findByStatusOrderBySessionDateAsc(status);
    }
    public List<MentorshipSession> getMentorshipSessionsBetween (LocalDate start , LocalDate end){
        return mentorshipSessionRepository.findBySessionDateBetweenOrderBySessionDateAsc(start, end);
    }

    public String deleteMentorshipSession(UUID id){
        MentorshipSession mentorshipSession = mentorshipSessionRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"The mentorship session with that ID doesn't exist"));
        mentorshipSessionRepository.deleteById(id);
        return "The mentorship is successfully deleted";
    }
    public String updateMentorshipSession(UUID id, MentorshipSession update){
        MentorshipSession mentorshipSession = mentorshipSessionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"The mentorship session with that ID doesn't exist"));
        MentorshipSession men = new MentorshipSession();
        men.setSessionDate(update.getSessionDate());
        men.setStatus(update.getStatus());
        men.setTopic(update.getTopic());
        men.setStudent(update.getStudent());
        men.setMentor(update.getMentor());
        mentorshipSessionRepository.save(men);
        return "MentorShip session updated successfully";
    }
    public Boolean isAllowed(SessionStatus oldStatus, SessionStatus newStatus){
        return switch (oldStatus){
            case REQUESTED -> newStatus == SessionStatus.ACCEPTED || newStatus == SessionStatus.DECLINED;
            case ACCEPTED -> newStatus == SessionStatus.HELD || newStatus == SessionStatus.DECLINED;
            default -> false;
        };
    }

    public String updateStatus(UUID id, SessionStatus status){
        MentorshipSession mentorshipSession = mentorshipSessionRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"The session with the given ID isn't found"));
        SessionStatus current = mentorshipSession.getStatus();

        if (!isAllowed(current, status))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot change status from " +current +" to " +status);
        mentorshipSession.setStatus(status);
        mentorshipSessionRepository.save(mentorshipSession);
        return "The Session has been changed successfully";
    }
}
