package com.clinic.academicbridgedemo.services;

import com.clinic.academicbridgedemo.Domain.Field;
import com.clinic.academicbridgedemo.Repo.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FieldService {
    @Autowired
    private FieldRepository fieldRepository;

    public List<Field> getFields(){
        return fieldRepository.findAll();
    }
    public Optional<Field> getFieldById(UUID id){
        Field field = fieldRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"The field with that ID doesn't exist"));
        return fieldRepository.findById(id);
    }
    public List<Field> getFreeFields(){
        return fieldRepository.findFreeFields();
    }
    public String saveField(Field field){
        fieldRepository.save(field);
        return "The field was saved successfully";
    }
    public String updatefield(UUID id , Field update){
        Field field = fieldRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"The field with that ID doesn't exist"));
        Field field1 = new Field();
        field1.setName(update.getName());
        field1.setMentors(update.getMentors());
        fieldRepository.save(field1);
        return "Field updated successfully";
    }

    public String deleteField(UUID id){
        Field field = fieldRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"The field with that ID doesn't exist"));
        fieldRepository.deleteById(id);
        return "Field deleted successfully";
    }
}