/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Duminda
 */
@Entity
@Table(name = "tour_planner_list")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TourPlannerList.findAll", query = "SELECT t FROM TourPlannerList t"),
    @NamedQuery(name = "TourPlannerList.findByTourPlannerListId", query = "SELECT t FROM TourPlannerList t WHERE t.tourPlannerListId = :tourPlannerListId"),
    @NamedQuery(name = "TourPlannerList.findByusername", query = "SELECT t FROM TourPlannerList t WHERE t.username = :username"),
    @NamedQuery(name = "TourPlannerList.findByTourPlannerListIdByUsername", query = "SELECT t FROM TourPlannerList t WHERE t.username = :username"),
    @NamedQuery(name = "TourPlannerList.findByCheckinDate", query = "SELECT t FROM TourPlannerList t WHERE t.checkinDate = :checkinDate"),
    @NamedQuery(name = "TourPlannerList.findByCheckoutDate", query = "SELECT t FROM TourPlannerList t WHERE t.checkoutDate = :checkoutDate"),
    @NamedQuery(name = "TourPlannerList.findByStartLocationId", query = "SELECT t FROM TourPlannerList t WHERE t.startLocationId = :startLocationId"),
    @NamedQuery(name = "TourPlannerList.findByEndLocationId", query = "SELECT t FROM TourPlannerList t WHERE t.endLocationId = :endLocationId")})
public class TourPlannerList implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tour_planner_list_id")
    private Integer tourPlannerListId;
    @Basic(optional = false)
    @Column(name = "checkin_date")
    @Temporal(TemporalType.DATE)
    private Date checkinDate;
    @Basic(optional = false)
    @Column(name = "checkout_date")
    @Temporal(TemporalType.DATE)
    private Date checkoutDate;
    @Basic(optional = false)
    @Column(name = "start_location_id")
    private int startLocationId;
    @Basic(optional = false)
    @Column(name = "end_location_id")
    private int endLocationId;
    @JoinTable(name = "tour_planner_list_location", joinColumns = {
        @JoinColumn(name = "tour_planner_list_id", referencedColumnName = "tour_planner_list_id")}, inverseJoinColumns = {
        @JoinColumn(name = "location_id", referencedColumnName = "location_id")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Location> locationList;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private User username;

    public TourPlannerList() {
    }

    public TourPlannerList(Integer tourPlannerListId) {
        this.tourPlannerListId = tourPlannerListId;
    }

    public TourPlannerList(Integer tourPlannerListId, Date checkinDate, Date checkoutDate, int startLocationId, int endLocationId) {
        this.tourPlannerListId = tourPlannerListId;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.startLocationId = startLocationId;
        this.endLocationId = endLocationId;
    }

    public TourPlannerList(Date checkinDate, Date checkoutDate, int startLocationId, int endLocationId) {
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.startLocationId = startLocationId;
        this.endLocationId = endLocationId;
    }

    public Integer getTourPlannerListId() {
        return tourPlannerListId;
    }

    public void setTourPlannerListId(Integer tourPlannerListId) {
        this.tourPlannerListId = tourPlannerListId;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getStartLocationId() {
        return startLocationId;
    }

    public void setStartLocationId(int startLocationId) {
        this.startLocationId = startLocationId;
    }

    public int getEndLocationId() {
        return endLocationId;
    }

    public void setEndLocationId(int endLocationId) {
        this.endLocationId = endLocationId;
    }

    @XmlTransient
    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
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
        hash += (tourPlannerListId != null ? tourPlannerListId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TourPlannerList)) {
            return false;
        }
        TourPlannerList other = (TourPlannerList) object;
        if ((this.tourPlannerListId == null && other.tourPlannerListId != null) || (this.tourPlannerListId != null && !this.tourPlannerListId.equals(other.tourPlannerListId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tourplanner.arlene.model.TourPlannerList[ tourPlannerListId=" + tourPlannerListId + " ]";
    }

    public void addLocation(Location location) {
        this.getLocationList().add(location);
    }

    public void dropLocation(Location location) {
        this.getLocationList().remove(location);
    }
}
