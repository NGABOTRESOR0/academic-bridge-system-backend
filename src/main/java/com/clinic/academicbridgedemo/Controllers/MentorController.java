package com.clinic.academicbridgedemo.Controllers;

import com.clinic.academicbridgedemo.Domain.Mentor;
import com.clinic.academicbridgedemo.services.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static tools.jackson.databind.jsonFormatVisitors.JsonValueFormat.UUID;

@RestController
@RequestMapping(value = "/api/mentors")
public class MentorController {
    @Autowired
    private MentorService mentorService;

    @GetMapping(value = "/all")
     public List<Mentor> getAllMentors(){
       return mentorService.getAllMentors();
      }
    @GetMapping(value = "/by-field")
    public List<Mentor> getMentorsByField(@RequestParam String name){
        return mentorService.getMentorByFields(name);
    }
    @PutMapping(value = "/{mentorId}/fields/{fieldId}")
    public String addField(@PathVariable UUID mentorId, @PathVariable UUID fieldId){
        return mentorService.addField(mentorId,fieldId);
    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveMentor(@RequestBody Mentor mentor){
        return mentorService.saveMentor(mentor);
    }

    @PutMapping(value = "/update/{id}")
    public String updateMentor(@PathVariable UUID id,@RequestBody Mentor updated){
        mentorService.updateMentor(id,updated);
        return "The Mentor is successfully updated";
    }
    @DeleteMapping(value = "/delete/{id}")
    public String deleteMentor(@PathVariable UUID id){
        mentorService.deleteMentor(id);
        return "The deletion of the mentor completed in success";
    }
    }


