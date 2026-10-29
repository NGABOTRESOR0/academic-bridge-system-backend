package com.clinic.academicbridgedemo.Controllers;


import com.clinic.academicbridgedemo.Domain.Student;
import com.clinic.academicbridgedemo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/students")
public class StudentController {

    @Autowired
   private StudentService studentService;

   @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
   @ResponseStatus(HttpStatus.CREATED)
    public String saveStudent(@RequestBody Student student){
       return studentService.saveStudent(student);
   }
   @GetMapping(value = "/all")
    public List<Student> getAllStudents(){
       return studentService.getAllStudents();
   }
   @PutMapping(value = "/update/{id}")
    public String updateStudent(@PathVariable UUID id,@RequestBody Student updated){
       studentService.updateStudent(id, updated);
       return "Student updated successfully";
   }
   @GetMapping(value = "/by-last-name")
   public List<Student> getStudentsByLastName(@RequestParam String lastName){
       return studentService.getStudentsByLastName(lastName);
   }
   @GetMapping(value = "/of-mentor/{mentorId}")
   public List<Student> getStudentByMentor(@PathVariable UUID mentorId){
       return studentService.findStudentsByMentor(mentorId);
   }
   @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable UUID id){
       studentService.deleteStudent(id);
       return "Student deletion completed with success";
   }
}
