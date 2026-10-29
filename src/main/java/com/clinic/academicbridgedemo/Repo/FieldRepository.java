package com.clinic.academicbridgedemo.Repo;

import com.clinic.academicbridgedemo.Domain.Field;
import com.clinic.academicbridgedemo.Domain.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

import static javax.swing.text.html.HTML.Tag.SELECT;
import static org.hibernate.sql.ast.Clause.WHERE;

public interface FieldRepository extends JpaRepository<Field, UUID> {
    @Query("SELECT f FROM Field f WHERE f.mentors IS EMPTY")
    List<Field> findFreeFields();
}
