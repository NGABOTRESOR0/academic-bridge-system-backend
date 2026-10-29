package com.clinic.academicbridgedemo.Controllers;

import com.clinic.academicbridgedemo.Domain.Field;
import com.clinic.academicbridgedemo.services.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/fields")
@RestController
public class FieldController {
    @Autowired
    private FieldService fieldService;
    @GetMapping(value = "/all")
    public List<Field> getFields(){
        return fieldService.getFields();
    }
    @GetMapping(value = "unused")
    public List<Field> getFreeFields(){return fieldService.getFreeFields(); }
    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveField(@RequestBody Field field){
        return fieldService.saveField(field);
    }
    @PutMapping(value = "/update/{id}")
    public String updateField(@PathVariable UUID id,@RequestBody Field field){
        return fieldService.updatefield(id, field);
    }
    @DeleteMapping(value = "/delete/{id}")
    public String deleteField(@PathVariable UUID id){
        return fieldService.deleteField(id);
    }
}
