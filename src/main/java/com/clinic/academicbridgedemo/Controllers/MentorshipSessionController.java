package com.clinic.academicbridgedemo.Controllers;

import com.clinic.academicbridgedemo.Domain.MentorshipSession;
import com.clinic.academicbridgedemo.Domain.SessionStatus;
import com.clinic.academicbridgedemo.services.MentorshipSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/mentorship-sessions")
public class MentorshipSessionController {
    @Autowired
    private MentorshipSessionService mentorshipSessionService;

    @GetMapping(value = "/all")
    public List<MentorshipSession> getMentorshipSession(){
        return mentorshipSessionService.getMentorshipSessions();
    }

    @GetMapping(value = "/by-status")
    public List<MentorshipSession>  getMentorshipSessionByStatus(@RequestParam SessionStatus status){
       return mentorshipSessionService.getMentorshipSessionsByStatus(status);
    }

    @GetMapping(value = "/between")
    public List<MentorshipSession> getMentorshipSessionBetween(@RequestParam LocalDate start, @RequestParam LocalDate end){
        return mentorshipSessionService.getMentorshipSessionsBetween(start, end);
    }
    @PatchMapping("/{id}/status")
    public String changeStatus (@PathVariable UUID id, @RequestParam SessionStatus status){
        return mentorshipSessionService.updateStatus(id,status);
    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveMentorshipsession(@RequestBody MentorshipSession mentorshipSession){
        return mentorshipSessionService.saveMentorshipSession(mentorshipSession);
    }

    @PutMapping(value = "/update/{id}")
    public String updateMentorshipsession(@PathVariable UUID id,@RequestBody MentorshipSession mentorshipSession){
        return mentorshipSessionService.updateMentorshipSession(id,mentorshipSession);
    }
    @DeleteMapping(value = "/delete/{id}")
    public String deleteMentorshipSession(@PathVariable UUID id){
        return mentorshipSessionService.deleteMentorshipSession(id);
    }
}
