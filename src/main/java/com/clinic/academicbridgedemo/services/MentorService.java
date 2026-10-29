package com.clinic.academicbridgedemo.services;

import com.clinic.academicbridgedemo.Domain.Field;
import com.clinic.academicbridgedemo.Domain.Mentor;
import com.clinic.academicbridgedemo.Repo.FieldRepository;
import com.clinic.academicbridgedemo.Repo.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class MentorService {
    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private FieldRepository fieldRepository;

    // Saving method

    public String saveMentor(Mentor mentor){
        mentorRepository.save(mentor);
        return "Mentor saved successfully";
    }

    // Deleting method

    public String deleteMentor(UUID id){
        if (!mentorRepository.existsById(id)){
            throw new IllegalArgumentException("The mentor with that ID doesn't exist");
        }else mentorRepository.deleteById(id);
        return "Mentor deleted successfully";
    }

    //Update method
    public Mentor updateMentor(UUID id, Mentor updated){
        Mentor mentor = mentorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The mentor with that ID doesn't exist"));

        mentor.setFirstName(updated.getFirstName());
        mentor.setLastName(updated.getLastName());
        mentor.setCompany(updated.getCompany());
        mentor.setLearningHub(updated.getLearningHub());
        mentor.setFields(updated.getFields());
        return mentorRepository.save(mentor);
    }
    public String addField(UUID mentorId, UUID fieldId){
        Mentor mentor = mentorRepository.findById(mentorId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "The mentor with that id does not exist"));
        Field field = fieldRepository.findById(fieldId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"That field does not exist"));

        if (mentor.getFields().contains(field)){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"The mentor already has that field");
        }
        mentor.getFields().add(field);
        mentorRepository.save(mentor);
        return "The field is added successfully";
    }
    //Get method
    public List<Mentor> getAllMentors(){
        return mentorRepository.findAll();
    }
    public List<Mentor> getMentorByFields(String name){
       return mentorRepository.findByFieldNameIgnoreCase(name);
    }
}
