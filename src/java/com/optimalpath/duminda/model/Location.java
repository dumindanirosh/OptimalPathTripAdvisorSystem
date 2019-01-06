/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "location")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l"),
    @NamedQuery(name = "Location.listUniqueNames", query = "SELECT DISTINCT l.locationCity FROM Location l"),
    @NamedQuery(name = "Location.findByLocationId", query = "SELECT l FROM Location l WHERE l.locationId = :locationId"),
    @NamedQuery(name = "Location.findByLocationName", query = "SELECT l FROM Location l WHERE l.locationName = :locationName"),
    @NamedQuery(name = "Location.findByLocationDescription", query = "SELECT l FROM Location l WHERE l.locationDescription = :locationDescription"),
    @NamedQuery(name = "Location.findByLocationCity", query = "SELECT l FROM Location l WHERE l.locationCity = :locationCity"),
    @NamedQuery(name = "Location.findByLocationDistrict", query = "SELECT l FROM Location l WHERE l.locationDistrict = :locationDistrict"),
    @NamedQuery(name = "Location.findByMainImageName", query = "SELECT l FROM Location l WHERE l.mainImageName = :mainImageName"),
    @NamedQuery(name = "Location.findByMainImageUrl", query = "SELECT l FROM Location l WHERE l.mainImageUrl = :mainImageUrl"),
    @NamedQuery(name = "Location.findByApproval", query = "SELECT l FROM Location l WHERE l.approval = :approval"),
    @NamedQuery(name = "Location.findByApprovedBy", query = "SELECT l FROM Location l WHERE l.approvedBy = :approvedBy"),
    @NamedQuery(name = "Location.findByEnteredBy", query = "SELECT l FROM Location l WHERE l.enteredBy = :enteredBy"),
    @NamedQuery(name = "Location.findByEnteredDate", query = "SELECT l FROM Location l WHERE l.enteredDate = :enteredDate")
})
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "location_id")
    private Integer locationId;
    @Basic(optional = false)
    @Column(name = "location_name")
    private String locationName;
    @Basic(optional = false)
    @Column(name = "location_description")
    private String locationDescription;
    @Basic(optional = false)
    @Column(name = "location_city")
    private String locationCity;
    @Basic(optional = false)
    @Column(name = "location_district")
    private String locationDistrict;
    @Column(name = "main_image_name")
    private String mainImageName;
    @Column(name = "main_image_url")
    private String mainImageUrl;
    @Column(name = "approval")
    private Boolean approval;
    @Column(name = "approved_by")
    private String approvedBy;
    @Basic(optional = false)
    @Column(name = "entered_by")
    private String enteredBy;
    @Basic(optional = false)
    @Column(name = "entered_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enteredDate;
    @Column(name = "visiting_duration")
    private Double visitingDuration;
    @ManyToMany(mappedBy = "locationList", fetch = FetchType.EAGER)
    private List<TourPlannerList> tourPlannerListList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locationId")
    private List<LocationReview> locationReviewList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    private List<LocationImage> locationImageList;
    @JoinColumn(name = "location_category_id", referencedColumnName = "location_category_id")
    @ManyToOne(optional = false)
    private LocationCategory locationCategoryId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locationId", fetch = FetchType.EAGER)
    private List<GoogleMapLocation> googleMapLocationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locationId")
    private List<HotLocation> hotLocationList;

    public Location() {
    }

    public Location(Integer locationId) {
        this.locationId = locationId;
    }

    public Location(Integer locationId, String locationName, String locationDescription, String locationCity,
            String locationDistrict, String enteredBy, Date enteredDate, Double visitingDuration) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.locationDescription = locationDescription;
        this.locationCity = locationCity;
        this.locationDistrict = locationDistrict;
        this.enteredBy = enteredBy;
        this.enteredDate = enteredDate;
        this.visitingDuration = visitingDuration;
    }

    public Location(String locationName, String locationDescription, String locationCity,
            String locationDistrict, String mainImageName, String mainImageUrl, boolean approval, String approvedBy,
            String enteredBy, Date enteredDate,Double visitingDuration) {
        this.locationName = locationName;
        this.locationDescription = locationDescription;
        this.locationCity = locationCity;
        this.locationDistrict = locationDistrict;
        this.mainImageName = mainImageName;
        this.mainImageUrl = mainImageUrl;
        this.approval = approval;
        this.approvedBy = approvedBy;
        this.enteredBy = enteredBy;
        this.enteredDate = enteredDate;
         this.visitingDuration = visitingDuration;

    }

  

    public Location(Integer locationId, String locationName, String locationDescription,
            String locationCity, String locationDistrict, boolean approval, String approvedBy, 
            String enteredBy, Date enteredDate,Double visitingDuration ) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.locationDescription = locationDescription;
        this.locationCity = locationCity;
        this.locationDistrict = locationDistrict;
        this.approval = approval;
        this.approvedBy = approvedBy;
        this.enteredBy = enteredBy;
        this.enteredDate = enteredDate;
     this.visitingDuration = visitingDuration;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getLocationDistrict() {
        return locationDistrict;
    }

    public void setLocationDistrict(String locationDistrict) {
        this.locationDistrict = locationDistrict;
    }

    public String getMainImageName() {
        return mainImageName;
    }

    public void setMainImageName(String mainImageName) {
        this.mainImageName = mainImageName;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public Boolean getApproval() {
        return approval;
    }

    public void setApproval(Boolean approval) {
        this.approval = approval;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }

    public Date getEnteredDate() {
        return enteredDate;
    }

    public void setEnteredDate(Date enteredDate) {
        this.enteredDate = enteredDate;
    }

    @XmlTransient
    public List<TourPlannerList> getTourPlannerListList() {
        return tourPlannerListList;
    }

    public void setTourPlannerListList(List<TourPlannerList> tourPlannerListList) {
        this.tourPlannerListList = tourPlannerListList;
    }

    @XmlTransient
    public List<LocationReview> getLocationReviewList() {
        return locationReviewList;
    }

    public void setLocationReviewList(List<LocationReview> locationReviewList) {
        this.locationReviewList = locationReviewList;
    }

    @XmlTransient
    public List<LocationImage> getLocationImageList() {
        return locationImageList;
    }

    public void setLocationImageList(List<LocationImage> locationImageList) {
        this.locationImageList = locationImageList;
    }

    public LocationCategory getLocationCategoryId() {
        return locationCategoryId;
    }

    public void setLocationCategoryId(LocationCategory locationCategoryId) {
        this.locationCategoryId = locationCategoryId;
    }

    @XmlTransient
    public List<GoogleMapLocation> getGoogleMapLocationList() {
        return googleMapLocationList;
    }

    public void setGoogleMapLocationList(List<GoogleMapLocation> googleMapLocationList) {
        this.googleMapLocationList = googleMapLocationList;
    }

    @XmlTransient
    public List<HotLocation> getHotLocationList() {
        return hotLocationList;
    }

    public void setHotLocationList(List<HotLocation> hotLocationList) {
        this.hotLocationList = hotLocationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locationId != null ? locationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.locationId == null && other.locationId != null) || (this.locationId != null && !this.locationId.equals(other.locationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tourplanner.arlene.model.Location[ locationId=" + locationId + " ]";
    }

    public void addTourPlannerlist(TourPlannerList tourPlannerList) {
        this.getTourPlannerListList().add(tourPlannerList);
    }

    public void dropTourPlannerlist(TourPlannerList tourPlannerList) {
        this.getTourPlannerListList().remove(tourPlannerList);
    }

//    public Double getCoverUpTime() {
//        return coverUpTime;
//    }
//
//    public void setCoverUpTime(Double coverUpTime) {
//        this.coverUpTime = coverUpTime;
//    }
    public Double getVisitingDuration() {
        return visitingDuration;
    }

    public void setVisitingDuration(Double visitingDuration) {
        this.visitingDuration = visitingDuration;
    }
}
