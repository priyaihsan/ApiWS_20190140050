/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.getmymessage;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ican
 */
@Entity
@Table(name = "mymessage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mymessage.findAll", query = "SELECT m FROM Mymessage m"),
    @NamedQuery(name = "Mymessage.findByIdMessage", query = "SELECT m FROM Mymessage m WHERE m.idMessage = :idMessage"),
    @NamedQuery(name = "Mymessage.findBySendTo", query = "SELECT m FROM Mymessage m WHERE m.sendTo = :sendTo")})
public class Mymessage implements Serializable{

    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "id_message")
    private Integer idMessage;
    @Lob
    @Column(name = "message")
    private String message;
    @Column(name = "sendTo")
    private String sendTo;

    public Mymessage() {
        super();
    }

    public Mymessage(Integer idMessage, String message, String sendTo) {
        super();
        this.idMessage = idMessage;
        this.message = message;
        this.sendTo = sendTo;
    }
    
    

    public Mymessage(Integer idMessage) {
        this.idMessage = idMessage;
    }

    public Integer getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Integer idMessage) {
        this.idMessage = idMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMessage != null ? idMessage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mymessage)) {
            return false;
        }
        Mymessage other = (Mymessage) object;
        if ((this.idMessage == null && other.idMessage != null) || (this.idMessage != null && !this.idMessage.equals(other.idMessage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.getmymessage.Mymessage[ idMessage=" + idMessage + " ]";
    }
    
}
