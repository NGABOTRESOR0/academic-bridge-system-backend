package com.clinic.academicbridgedemo.Repo;

import com.clinic.academicbridgedemo.Domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    public List<Student> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
    @Query("SELECT s FROM Student s WHERE EXISTS (SELECT 1 FROM MentorshipSession ms WHERE ms.student = s)")
    List<Student> findStudentsByMentor();
}
