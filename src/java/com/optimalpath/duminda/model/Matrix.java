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
@Table(name = "matrix")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Matrix.findAll", query = "SELECT m FROM Matrix m"),
    @NamedQuery(name = "Matrix.findByMatrixId", query = "SELECT m FROM Matrix m WHERE m.matrixId = :matrixId"),
    @NamedQuery(name = "Matrix.findByMatrix", query = "SELECT m FROM Matrix m WHERE m.googlemapLocationId1 = :googlemapLocationId1 AND m.googlemapLocationId2 = :googlemapLocationId2"),
    @NamedQuery(name = "Matrix.findByLocationID1", query = "SELECT m FROM Matrix m WHERE m.googlemapLocationId1 = :googlemapLocationId1"),
    @NamedQuery(name = "Matrix.findByDistance", query = "SELECT m FROM Matrix m WHERE m.distance = :distance"),
    @NamedQuery(name = "Matrix.findByTime", query = "SELECT m FROM Matrix m WHERE m.time = :time")})
public class Matrix implements Serializable {
    @JoinColumn(name = "googlemap_location_id2", referencedColumnName = "google_map_location_id")
    @ManyToOne(optional = false)
    private GoogleMapLocation googlemapLocationId2;
    @JoinColumn(name = "googlemap_location_id1", referencedColumnName = "google_map_location_id")
    @ManyToOne(optional = false)
    private GoogleMapLocation googlemapLocationId1;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "matrix_id")
    private Integer matrixId;
   
    @Column(name = "distance")
    private Double distance;
    @Column(name = "time")
    private Double time;


    public Matrix() {
    }

    public Matrix(Integer matrixId) {
        this.matrixId = matrixId;
    }

    public Matrix(Double distance, Double time) {
        this.distance = distance;
        this.time = time;
    }
    
    

    public Integer getMatrixId() {
        return matrixId;
    }

    public void setMatrixId(Integer matrixId) {
        this.matrixId = matrixId;
    }

    public Double getDistance() {
        return distance;
    }

  

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

//  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matrixId != null ? matrixId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matrix)) {
            return false;
        }
        Matrix other = (Matrix) object;
        if ((this.matrixId == null && other.matrixId != null) || (this.matrixId != null && !this.matrixId.equals(other.matrixId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tourplanner.arlene.model.Matrix[ matrixId=" + matrixId + " ]";
    }

    public GoogleMapLocation getGooglemapLocationId2() {
        return googlemapLocationId2;
    }

    public void setGooglemapLocationId2(GoogleMapLocation googlemapLocationId2) {
        this.googlemapLocationId2 = googlemapLocationId2;
    }

    public GoogleMapLocation getGooglemapLocationId1() {
        return googlemapLocationId1;
    }

    public void setGooglemapLocationId1(GoogleMapLocation googlemapLocationId1) {
        this.googlemapLocationId1 = googlemapLocationId1;
    }
    
}
