/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.util;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Duminda
 */
public class UserDetails implements Serializable{

    private String username;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String userType;
    private Date enteredDate;
    private String password;

    public UserDetails() {
    }

    public UserDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDetails(String username, String firstName, String lastName, String emailAddress, String userType,
            Date enteredDate, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.userType = userType;
        this.enteredDate = enteredDate;
        this.password = password;
    }

    public UserDetails(String username, String firstName, String lastName, String emailAddress) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @param userType the userType to set
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * @return the enteredDate
     */
    public Date getEnteredDate() {
        return enteredDate;
    }

    /**
     * @param enteredDate the enteredDate to set
     */
    public void setEnteredDate(Date enteredDate) {
        this.enteredDate = enteredDate;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
