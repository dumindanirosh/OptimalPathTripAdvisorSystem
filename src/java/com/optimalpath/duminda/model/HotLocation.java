/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "hot_location")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HotLocation.findAll", query = "SELECT h FROM HotLocation h"),
    @NamedQuery(name = "HotLocation.findLocationByHotLocation", query = "SELECT h FROM HotLocation h WHERE h.locationId = :locationId "),
    @NamedQuery(name = "HotLocation.findByHotLocationId", query = "SELECT h FROM HotLocation h WHERE h.hotLocationId = :hotLocationId")})
public class HotLocation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "hot_location_id")
    private Integer hotLocationId;
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    @ManyToOne(optional = false)
    private Location locationId;

    public HotLocation() {
    }

    public HotLocation(Integer hotLocationId) {
        this.hotLocationId = hotLocationId;
    }

    public Integer getHotLocationId() {
        return hotLocationId;
    }

    public HotLocation(Integer hotLocationId, Location locationId) {
        this.hotLocationId = hotLocationId;
        this.locationId = locationId;
    }
    
    public void setHotLocationId(Integer hotLocationId) {
        this.hotLocationId = hotLocationId;
    }

    public Location getLocationId() {
        return locationId;
    }

    public void setLocationId(Location locationId) {
        this.locationId = locationId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hotLocationId != null ? hotLocationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HotLocation)) {
            return false;
        }
        HotLocation other = (HotLocation) object;
        if ((this.hotLocationId == null && other.hotLocationId != null) || (this.hotLocationId != null && !this.hotLocationId.equals(other.hotLocationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tourplanner.arlene.model.HotLocation[ hotLocationId=" + hotLocationId + " ]";
    }
    
}
