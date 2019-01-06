/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Duminda
 */
@Entity
@Table(name = "location_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LocationCategory.findAll", query = "SELECT l FROM LocationCategory l"),
    @NamedQuery(name = "LocationCategory.findByLocationCategoryId", query = "SELECT l FROM LocationCategory l WHERE l.locationCategoryId = :locationCategoryId"),
    @NamedQuery(name = "LocationCategory.findByLocationCategoryType", query = "SELECT l FROM LocationCategory l WHERE l.locationCategoryType = :locationCategoryType")})
public class LocationCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "location_category_id")
    private Integer locationCategoryId;
    @Basic(optional = false)
    @Column(name = "location_category_type")
    private String locationCategoryType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locationCategoryId")
    private List<Location> locationList;

    public LocationCategory() {
    }

    public LocationCategory(Integer locationCategoryId) {
        this.locationCategoryId = locationCategoryId;
    }
    
    public LocationCategory(String locationCategoryType) {
        this.locationCategoryType = locationCategoryType;
    }

    public LocationCategory(Integer locationCategoryId, String locationCategoryType) {
        this.locationCategoryId = locationCategoryId;
        this.locationCategoryType = locationCategoryType;
    }

    public Integer getLocationCategoryId() {
        return locationCategoryId;
    }

    public void setLocationCategoryId(Integer locationCategoryId) {
        this.locationCategoryId = locationCategoryId;
    }

    public String getLocationCategoryType() {
        return locationCategoryType;
    }

    public void setLocationCategoryType(String locationCategoryType) {
        this.locationCategoryType = locationCategoryType;
    }

    @XmlTransient
    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locationCategoryId != null ? locationCategoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocationCategory)) {
            return false;
        }
        LocationCategory other = (LocationCategory) object;
        if ((this.locationCategoryId == null && other.locationCategoryId != null) || (this.locationCategoryId != null && !this.locationCategoryId.equals(other.locationCategoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tourplanner.arlene.model.LocationCategory[ locationCategoryId=" + locationCategoryId + " ]";
    }
    
}
