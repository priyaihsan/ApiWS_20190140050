/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.getmymessage;

/**
 *
 * @author Ican
 */
public class MymessageNotFoundException extends Exception {
    private Integer id;
    public MymessageNotFoundException(Integer id) {
        super(String.format("Book is not found with id : '%s'", id));
    }
    
}
