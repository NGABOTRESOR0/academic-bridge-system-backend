package com.clinic.academicbridgedemo.Domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "learning_hub")
public class LearningHub {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "hub_Number")
    private int hubNumber;

    @Column(name = "Name")
    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getHubNumber() {
        return hubNumber;
    }

    public void setHubNumber(int hubNumber) {
        this.hubNumber = hubNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
