/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.util;

import java.io.Serializable;

/**
 *
 * @author Duminda
 */
public class ContactUsDetails implements Serializable{
    private int contactUsId;
    private String name;
    private String email;
    private String contactNo;
    private String message;

    public ContactUsDetails(String name, String email, String contactNo, String message) {
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.message = message;
    }

    public ContactUsDetails(int contactUsId, String name, String email, String contactNo, String message) {
        this.contactUsId = contactUsId;
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.message = message;
    }

    
    
    
    /**
     * @return the contactUsId
     */
    public int getContactUsId() {
        return contactUsId;
    }

    /**
     * @param contactUsId the contactUsId to set
     */
    public void setContactUsId(int contactUsId) {
        this.contactUsId = contactUsId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the contactNo
     */
    public String getContactNo() {
        return contactNo;
    }

    /**
     * @param contactNo the contactNo to set
     */
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
}
