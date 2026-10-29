package com.clinic.academicbridgedemo.Domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "mentor")
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

   @Column(name = "company")
    private String company;

   @ManyToOne
   @JoinColumn(name = "learning_hub_id",nullable = true)
   private LearningHub learningHub;

   @ManyToMany
    @JoinTable(name = "mentor_field")
    private List<Field> fields;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public LearningHub getLearningHub() {
        return learningHub;
    }

    public void setLearningHub(LearningHub learningHub) {
        this.learningHub = learningHub;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
