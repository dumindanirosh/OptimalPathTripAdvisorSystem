/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Duminda
 */
@Embeddable
public class LocationImagePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "location_image_id")
    private int locationImageId;
    @Basic(optional = false)
    @Column(name = "location_id")
    private int locationId;

    public LocationImagePK() {
    }

    public LocationImagePK(int locationImageId, int locationId) {
        this.locationImageId = locationImageId;
        this.locationId = locationId;
    }
    
    public LocationImagePK(int locationId) {
        this.locationId = locationId;
    }

    public int getLocationImageId() {
        return locationImageId;
    }

    public void setLocationImageId(int locationImageId) {
        this.locationImageId = locationImageId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) locationImageId;
        hash += (int) locationId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocationImagePK)) {
            return false;
        }
        LocationImagePK other = (LocationImagePK) object;
        if (this.locationImageId != other.locationImageId) {
            return false;
        }
        if (this.locationId != other.locationId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tourplanner.arlene.model.LocationImagePK[ locationImageId=" + locationImageId + ", locationId=" + locationId + " ]";
    }
    
}
