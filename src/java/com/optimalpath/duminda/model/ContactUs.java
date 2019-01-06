/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Duminda
 */
@Entity
@Table(name = "contact_us")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContactUs.findAll", query = "SELECT c FROM ContactUs c"),
    @NamedQuery(name = "ContactUs.findByContactUsId", query = "SELECT c FROM ContactUs c WHERE c.contactUsId = :contactUsId"),
    @NamedQuery(name = "ContactUs.findByName", query = "SELECT c FROM ContactUs c WHERE c.name = :name"),
    @NamedQuery(name = "ContactUs.findByEmail", query = "SELECT c FROM ContactUs c WHERE c.email = :email"),
    @NamedQuery(name = "ContactUs.findByContactNo", query = "SELECT c FROM ContactUs c WHERE c.contactNo = :contactNo"),
    @NamedQuery(name = "ContactUs.findByMessage", query = "SELECT c FROM ContactUs c WHERE c.message = :message")})
public class ContactUs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "contact_us_id")
    private Integer contactUsId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "contact_no")
    private String contactNo;
    @Basic(optional = false)
    @Column(name = "message")
    private String message;

    public ContactUs() {
    }

    public ContactUs(Integer contactUsId) {
        this.contactUsId = contactUsId;
    }

    public ContactUs(String name, String email, String contactNo, String message) {
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.message = message;
    }
    
    public ContactUs(Integer contactUsId, String name, String email, String contactNo, String message) {
        this.contactUsId = contactUsId;
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.message = message;
    }

    public Integer getContactUsId() {
        return contactUsId;
    }

    public void setContactUsId(Integer contactUsId) {
        this.contactUsId = contactUsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contactUsId != null ? contactUsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContactUs)) {
            return false;
        }
        ContactUs other = (ContactUs) object;
        if ((this.contactUsId == null && other.contactUsId != null) || (this.contactUsId != null && !this.contactUsId.equals(other.contactUsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tourplanner.arlene.model.ContactUs[ contactUsId=" + contactUsId + " ]";
    }
    
}
