/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "location_image")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LocationImage.findAll", query = "SELECT l FROM LocationImage l"),
    @NamedQuery(name = "LocationImage.findByLocationImageId", query = "SELECT l FROM LocationImage l WHERE l.locationImagePK.locationImageId = :locationImageId"),
    @NamedQuery(name = "LocationImage.findByLocationImageName", query = "SELECT l FROM LocationImage l WHERE l.locationImageName = :locationImageName"),
    @NamedQuery(name = "LocationImage.findByLocationImageUrl", query = "SELECT l FROM LocationImage l WHERE l.locationImageUrl = :locationImageUrl"),
    @NamedQuery(name = "LocationImage.findByLocationId", query = "SELECT l FROM LocationImage l WHERE l.locationImagePK.locationId = :locationId")})
public class LocationImage implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LocationImagePK locationImagePK;
    @Basic(optional = false)
    @Column(name = "location_image_name")
    private String locationImageName;
    @Basic(optional = false)
    @Column(name = "location_image_url")
    private String locationImageUrl;
    @JoinColumn(name = "location_id", referencedColumnName = "location_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Location location;

    public LocationImage() {
    }

    public LocationImage(LocationImagePK locationImagePK) {
        this.locationImagePK = locationImagePK;
    }

    public LocationImage(LocationImagePK locationImagePK, String locationImageName, String locationImageUrl) {
        this.locationImagePK = locationImagePK;
        this.locationImageName = locationImageName;
        this.locationImageUrl = locationImageUrl;
    }

    public LocationImage(int locationId) {
        this.locationImagePK = new LocationImagePK(locationId);
    }

    public LocationImage(String locationImageName, String locationImageUrl) {
        this.locationImageName = locationImageName;
        this.locationImageUrl = locationImageUrl;
    }

    public LocationImage(int locationImageId, int locationId) {
        this.locationImagePK = new LocationImagePK(locationImageId, locationId);
    }

    public LocationImagePK getLocationImagePK() {
        return locationImagePK;
    }

    public void setLocationImagePK(LocationImagePK locationImagePK) {
        this.locationImagePK = locationImagePK;
    }

    public String getLocationImageName() {
        return locationImageName;
    }

    public void setLocationImageName(String locationImageName) {
        this.locationImageName = locationImageName;
    }

    public String getLocationImageUrl() {
        return locationImageUrl;
    }

    public void setLocationImageUrl(String locationImageUrl) {
        this.locationImageUrl = locationImageUrl;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locationImagePK != null ? locationImagePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocationImage)) {
            return false;
        }
        LocationImage other = (LocationImage) object;
        if ((this.locationImagePK == null && other.locationImagePK != null) || (this.locationImagePK != null && !this.locationImagePK.equals(other.locationImagePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tourplanner.arlene.model.LocationImage[ locationImagePK=" + locationImagePK + " ]";
    }
}
