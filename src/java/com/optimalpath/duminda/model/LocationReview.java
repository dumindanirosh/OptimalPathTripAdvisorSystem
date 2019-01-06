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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Duminda
 */
@Entity
@Table(name = "location_review")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LocationReview.findAll", query = "SELECT l FROM LocationReview l"),
    @NamedQuery(name = "LocationReview.findByReviewByLocationId", query = "SELECT l FROM LocationReview l WHERE l.locationId = :locationId"),
    @NamedQuery(name = "LocationReview.findByLocationReviewId", query = "SELECT l FROM LocationReview l WHERE l.locationReviewId = :locationReviewId"),
    @NamedQuery(name = "LocationReview.findByComment", query = "SELECT l FROM LocationReview l WHERE l.comment = :comment")})
public class LocationReview implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "location_review_id")
    private Integer locationReviewId;
    @Basic(optional = false)
    @Column(name = "comment")
    private String comment;
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    @ManyToOne(optional = false)
    private Location locationId;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private User username;

    public LocationReview() {
    }

    public LocationReview(Integer locationReviewId) {
        this.locationReviewId = locationReviewId;
    }

    public LocationReview(Integer locationReviewId, String comment) {
        this.locationReviewId = locationReviewId;
        this.comment = comment;
    }

    public LocationReview(String comment) {
        this.comment = comment;
    }

    public Integer getLocationReviewId() {
        return locationReviewId;
    }

    public void setLocationReviewId(Integer locationReviewId) {
        this.locationReviewId = locationReviewId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Location getLocationId() {
        return locationId;
    }

    public void setLocationId(Location locationId) {
        this.locationId = locationId;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locationReviewId != null ? locationReviewId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocationReview)) {
            return false;
        }
        LocationReview other = (LocationReview) object;
        if ((this.locationReviewId == null && other.locationReviewId != null) || (this.locationReviewId != null && !this.locationReviewId.equals(other.locationReviewId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tourplanner.arlene.model.LocationReview[ locationReviewId=" + locationReviewId + " ]";
    }
    
}
