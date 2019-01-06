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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "google_map_location")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GoogleMapLocation.findAll", query = "SELECT g FROM GoogleMapLocation g"),
    @NamedQuery(name = "GoogleMapLocation.findByGoogleMapLocationId", query = "SELECT g FROM GoogleMapLocation g WHERE g.googleMapLocationId = :googleMapLocationId"),
    @NamedQuery(name = "GoogleMapLocation.findAllGoogleMapLocationByLocationID", query = "SELECT g FROM GoogleMapLocation g WHERE g.locationId = :locationId"),
    @NamedQuery(name = "GoogleMapLocation.findByGoogleMapLocationName", query = "SELECT g FROM GoogleMapLocation g WHERE g.googleMapLocationName = :googleMapLocationName"),
    @NamedQuery(name = "GoogleMapLocation.findByLatitude", query = "SELECT g FROM GoogleMapLocation g WHERE g.latitude = :latitude"),
    @NamedQuery(name = "GoogleMapLocation.findByLongitude", query = "SELECT g FROM GoogleMapLocation g WHERE g.longitude = :longitude")})
public class GoogleMapLocation implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "googlemapLocationId2")
    private List<Matrix> matrixList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "googlemapLocationId1")
    private List<Matrix> matrixList1;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "google_map_location_id")
    private Integer googleMapLocationId;
    @Basic(optional = false)
    @Column(name = "google_map_location_name")
    private String googleMapLocationName;
    @Basic(optional = false)
    @Column(name = "latitude")
    private double latitude;
    @Basic(optional = false)
    @Column(name = "longitude")
    private double longitude;
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    @ManyToOne(optional = false)
    private Location locationId;

    public GoogleMapLocation() {
    }

    public GoogleMapLocation(Integer googleMapLocationId) {
        this.googleMapLocationId = googleMapLocationId;
    }

    public GoogleMapLocation(String googleMapLocationName, double latitude, double longitude) {
        this.googleMapLocationName = googleMapLocationName;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public GoogleMapLocation(Integer googleMapLocationId, String googleMapLocationName, double latitude, double longitude) {
        this.googleMapLocationId = googleMapLocationId;
        this.googleMapLocationName = googleMapLocationName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getGoogleMapLocationId() {
        return googleMapLocationId;
    }

    public void setGoogleMapLocationId(Integer googleMapLocationId) {
        this.googleMapLocationId = googleMapLocationId;
    }

    public String getGoogleMapLocationName() {
        return googleMapLocationName;
    }

    public void setGoogleMapLocationName(String googleMapLocationName) {
        this.googleMapLocationName = googleMapLocationName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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
        hash += (googleMapLocationId != null ? googleMapLocationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GoogleMapLocation)) {
            return false;
        }
        GoogleMapLocation other = (GoogleMapLocation) object;
        if ((this.googleMapLocationId == null && other.googleMapLocationId != null) || (this.googleMapLocationId != null && !this.googleMapLocationId.equals(other.googleMapLocationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tourplanner.arlene.model.GoogleMapLocation[ googleMapLocationId=" + googleMapLocationId + " ]";
    }

    @XmlTransient
    public List<Matrix> getMatrixList() {
        return matrixList;
    }

    public void setMatrixList(List<Matrix> matrixList) {
        this.matrixList = matrixList;
    }

    @XmlTransient
    public List<Matrix> getMatrixList1() {
        return matrixList1;
    }

    public void setMatrixList1(List<Matrix> matrixList1) {
        this.matrixList1 = matrixList1;
    }
    
}
