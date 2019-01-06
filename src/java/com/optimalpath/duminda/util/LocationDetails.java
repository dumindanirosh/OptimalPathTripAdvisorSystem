/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.util;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Duminda
 */
public class LocationDetails implements Serializable {

    private Integer locationId;
    private String locationName;
    private String locationDescription;
    private String locationCity;
    private String locationDistrict;
    private String mainImageName;
    private String mainImageUrl;
    private boolean approval;
    private String approvedBy;
    private String enteredBy;
    private Date enteredDate;
    private int locationCategoryId;
    private String locationCategoryType;
    private int googleMapId;
    private String googleMapName;
    private double visitingDuration;
            
            
    public LocationDetails(Integer locationId, String mainImageName, String mainImageUrl) {
        this.locationId = locationId;
        this.mainImageName = mainImageName;
        this.mainImageUrl = mainImageUrl;
    }

    public LocationDetails(Integer locationId, String locationName, String locationDescription, String locationCity, String locationDistrict, String enteredBy) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.locationDescription = locationDescription;
        this.locationCity = locationCity;
        this.locationDistrict = locationDistrict;
        this.enteredBy = enteredBy;
    }

    public LocationDetails(String locationName, String locationDescription, int locationCategoryId, String locationCity,
            String locationDistrict, String mainImageName, String mainImageUrl, boolean approval, String approvedBy,
            String enteredBy, Date enteredDate, double visitingDuration) {

        this.locationName = locationName;
        this.locationDescription = locationDescription;
        this.locationCategoryId = locationCategoryId;
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

    public LocationDetails(Integer locationId, String locationName, String locationDescription, int locationCategoryId, 
            String locationCity, String locationDistrict, String mainImageName, String mainImageUrl, boolean approval, 
            String approvedBy,  String enteredBy, Date enteredDate, double visitingDuration) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.locationCategoryId = locationCategoryId;
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

    /**
     * @return the locationId
     */
    public Integer getLocationId() {
        return locationId;
    }

    /**
     * @param locationId the locationId to set
     */
    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    /**
     * @return the locationName
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * @param locationName the locationName to set
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    /**
     * @return the locationDescription
     */
    public String getLocationDescription() {
        return locationDescription;
    }

    /**
     * @param locationDescription the locationDescription to set
     */
    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    /**
     * @return the locationCity
     */
    public String getLocationCity() {
        return locationCity;
    }

    /**
     * @param locationCity the locationCity to set
     */
    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    /**
     * @return the locationDistrict
     */
    public String getLocationDistrict() {
        return locationDistrict;
    }

    /**
     * @param locationDistrict the locationDistrict to set
     */
    public void setLocationDistrict(String locationDistrict) {
        this.locationDistrict = locationDistrict;
    }

    /**
     * @return the mainImageName
     */
    public String getMainImageName() {
        return mainImageName;
    }

    /**
     * @param mainImageName the mainImageName to set
     */
    public void setMainImageName(String mainImageName) {
        this.mainImageName = mainImageName;
    }

    /**
     * @return the mainImageUrl
     */
    public String getMainImageUrl() {
        return mainImageUrl;
    }

    /**
     * @param mainImageUrl the mainImageUrl to set
     */
    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    /**
     * @return the approval
     */
    public boolean isApproval() {
        return approval;
    }

    /**
     * @param approval the approval to set
     */
    public void setApproval(boolean approval) {
        this.approval = approval;
    }

    /**
     * @return the approvedBy
     */
    public String getApprovedBy() {
        return approvedBy;
    }

    /**
     * @param approvedBy the approvedBy to set
     */
    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    /**
     * @return the enteredBy
     */
    public String getEnteredBy() {
        return enteredBy;
    }

    /**
     * @param enteredBy the enteredBy to set
     */
    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }

    /**
     * @return the enteredDate
     */
    public Date getEnteredDate() {
        return enteredDate;
    }

    /**
     * @param enteredDate the enteredDate to set
     */
    public void setEnteredDate(Date enteredDate) {
        this.enteredDate = enteredDate;
    }

    /**
     * @return the locationCategoryId
     */
    public int getLocationCategoryId() {
        return locationCategoryId;
    }

    /**
     * @param locationCategoryId the locationCategoryId to set
     */
    public void setLocationCategoryId(int locationCategoryId) {
        this.locationCategoryId = locationCategoryId;
    }

    /**
     * @return the locationCategoryType
     */
    public String getLocationCategoryType() {
        return locationCategoryType;
    }

    /**
     * @param locationCategoryType the locationCategoryType to set
     */
    public void setLocationCategoryType(String locationCategoryType) {
        this.locationCategoryType = locationCategoryType;
    }

    /**
     * @return the googleMapId
     */
    public int getGoogleMapId() {
        return googleMapId;
    }

    /**
     * @param googleMapId the googleMapId to set
     */
    public void setGoogleMapId(int googleMapId) {
        this.googleMapId = googleMapId;
    }

    /**
     * @return the googleMapName
     */
    public String getGoogleMapName() {
        return googleMapName;
    }

    /**
     * @param googleMapName the googleMapName to set
     */
    public void setGoogleMapName(String googleMapName) {
        this.googleMapName = googleMapName;
    }

    /**
     * @return the visitingTime
     */
    public double getVisitingDuration() {
        return visitingDuration;
    }

    /**
     * @param visitingTime the visitingTime to set
     */
    public void setVisitingDuration(double visitingDuration) {
        this.visitingDuration = visitingDuration;
    }
}
