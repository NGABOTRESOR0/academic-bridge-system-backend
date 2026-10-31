package com.clinic.academicbridgedemo.services;

import com.clinic.academicbridgedemo.Domain.Mentor;
import com.clinic.academicbridgedemo.Domain.Student;
import com.clinic.academicbridgedemo.Repo.MentorRepository;
import com.clinic.academicbridgedemo.Repo.StudentRepository;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private MentorRepository mentorRepo;

    //Method to save a student
    public String saveStudent(Student student) {
         studentRepo.save(student);
        return "Student saved successfully";
    }

    //Methods for retrieval
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student getStudentById(UUID id){
        Student student = studentRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"The Student with that id does not exist"));
        return studentRepo.getById(id);
    }

    public List<Student> getStudentsByLastName(String lastName){
        return studentRepo.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }
    public List<Student> findStudentsByMentor(UUID id){
        Mentor mentor = mentorRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"The mentor with that id does not exist"));
        return studentRepo.findStudentsByMentor();
    }

    //Update method
    public Student updateStudent(UUID id, Student updated){
        Student student = studentRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Student with that ID doesn't exist"));

        student.setFirstName(updated.getFirstName());
        student.setLastName(updated.getLastName());
        student.setEmail(updated.getEmail());
        student.setFaculty(updated.getFaculty());
        student.setRegNumber(updated.getRegNumber());
        return studentRepo.save(student);
    }

    //Delete student
    public String deleteStudent(UUID id){
        if (!studentRepo.existsById(id)){
            System.out.println("This Student doesn't exist");
        }try{
            studentRepo.deleteById(id);
            return "Student successfully deleted";
        }catch (DataAccessException e){
            return "Student cant be deleted";
        }
    }
}


