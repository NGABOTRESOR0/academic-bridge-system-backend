package com.clinic.academicbridgedemo.Repo;

import com.clinic.academicbridgedemo.Domain.LearningHub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LearningHubRepository extends JpaRepository<LearningHub, UUID> {
    @Query("SELECT l FROM LearningHub l WHERE NOT EXISTS (SELECT m FROM Mentor m WHERE m.learningHub = l) ORDER BY l.hubNumber ASC")
    List<LearningHub> findFreeLearningHubs();

    Optional <LearningHub> findByHubNumber(int hubNumber);
}
