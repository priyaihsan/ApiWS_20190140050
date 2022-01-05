/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.getmymessage;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ican
 */
@RestController
@CrossOrigin(origins = "*")
public class MymessageController {
    
    @Autowired
    MymessageRepository messageRepo;
    
    @GetMapping(value = "/getallMessage")
    public List<Mymessage> all(){
        return messageRepo.findAll();
    }
    
    @GetMapping(value = "/getallMessage/{id}")
    public Mymessage onlyOne(@PathVariable(value = "id") Integer id) throws MymessageNotFoundException{
        return messageRepo.findById(id).orElseThrow(() -> new MymessageNotFoundException(id));
    }
    
    @PostMapping("/getallMessage")
    public Mymessage addMessage(@Valid @RequestBody Mymessage mymessage) {
        return messageRepo.save(mymessage);
    }
    
    @PutMapping("/getallMessage/{id}")
    public Mymessage updateMessage(@PathVariable(value = "id") Integer id,
                           @Valid @RequestBody Mymessage MymessageDetails) throws MymessageNotFoundException {

        Mymessage message = messageRepo.findById(id).orElseThrow(() -> new MymessageNotFoundException(id));

        message.setMessage(MymessageDetails.getMessage());
        message.setSendTo(MymessageDetails.getSendTo());

        Mymessage updatedmessage = messageRepo.save(message);

        return updatedmessage;
    }
    
    @DeleteMapping("/getallMessage/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable(value = "id") Integer id) throws MymessageNotFoundException {
        Mymessage message = messageRepo.findById(id)
                .orElseThrow(() -> new MymessageNotFoundException(id));

        messageRepo.delete(message);

        return ResponseEntity.ok().build();
    }
    
}
