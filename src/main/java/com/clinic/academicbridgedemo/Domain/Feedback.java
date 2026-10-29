package com.clinic.academicbridgedemo.Domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String skillAssessed;

    @Column
    private String comment;

    @Column
    private int score;

    @Column
    private LocalDate givenDate;

    @ManyToOne
    @JoinColumn(name = "mentorship_session_id")
    private MentorshipSession mentorshipSession;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSkillAssessed() {
        return skillAssessed;
    }

    public void setSkillAssessed(String skillAssessed) {
        this.skillAssessed = skillAssessed;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDate getGivenDate() {
        return givenDate;
    }

    public void setGivenDate(LocalDate givenDate) {
        this.givenDate = givenDate;
    }

    public MentorshipSession getMentorshipSession() {
        return mentorshipSession;
    }

    public void setMentorshipSession(MentorshipSession mentorshipSession) {
        this.mentorshipSession = mentorshipSession;
    }
}
