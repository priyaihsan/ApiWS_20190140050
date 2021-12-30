/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.getmymessage;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ican
 */
@Controller
public class ViewControllerMessage {
        @CrossOrigin
    @RequestMapping(value = "/getMessage", produces={MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Mymessage> getdatajson(){
        MymessageJpaController controller = new MymessageJpaController();
        List<Mymessage> buffer = new ArrayList<>();
        try{
            buffer = controller.findMymessageEntities();
        }catch(Exception e){
            
        }
        
        return buffer; 
        
    }
}
