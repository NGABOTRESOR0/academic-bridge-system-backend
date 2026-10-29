package com.clinic.academicbridgedemo.Repo;

import com.clinic.academicbridgedemo.Domain.Mentor;
import com.clinic.academicbridgedemo.Domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MentorRepository extends JpaRepository<Mentor, UUID> {
    @Query("SELECT DISTINCT m FROM Mentor m JOIN m.fields f WHERE LOWER(f.name) = LOWER(:fieldName)")
    List<Mentor> findByFieldNameIgnoreCase(@Param("fieldName") String name);

}
