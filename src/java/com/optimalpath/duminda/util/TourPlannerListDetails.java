/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.util;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Duminda
 */
public class TourPlannerListDetails implements Serializable{

    private int tourPlannerListId;
    private Date checkinDate;
    private Date checkoutDate;
    private int startLocationId;
    private int endLocationId;
    private String username;
    private List<LocationDetails> locationList;

    public TourPlannerListDetails(Date checkinDate, Date checkoutDate, int startLocationId, int endLocationId, String username) {
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.startLocationId = startLocationId;
        this.endLocationId = endLocationId;
        this.username = username;
    }

    

    public TourPlannerListDetails(int tourPlannerListId, Date checkinDate, Date checkoutDate, int startLocationId, int endLocationId) {
        this.tourPlannerListId = tourPlannerListId;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.startLocationId = startLocationId;
        this.endLocationId = endLocationId;
    }

    
    
    public TourPlannerListDetails(int tourPlannerListId, Date checkinDate, Date checkoutDate, int startLocationId, int endLocationId, String username) {
        this.tourPlannerListId = tourPlannerListId;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.startLocationId = startLocationId;
        this.endLocationId = endLocationId;
        this.username = username;
    }

    /**
     * @return the tourPlannerListId
     */
    public int getTourPlannerListId() {
        return tourPlannerListId;
    }

    /**
     * @param tourPlannerListId the tourPlannerListId to set
     */
    public void setTourPlannerListId(int tourPlannerListId) {
        this.tourPlannerListId = tourPlannerListId;
    }

    /**
     * @return the checkinDate
     */
    public Date getCheckinDate() {
        return checkinDate;
    }

    /**
     * @param checkinDate the checkinDate to set
     */
    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    /**
     * @return the checkoutDate
     */
    public Date getCheckoutDate() {
        return checkoutDate;
    }

    /**
     * @param checkoutDate the checkoutDate to set
     */
    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    /**
     * @return the startLocationId
     */
    public int getStartLocationId() {
        return startLocationId;
    }

    /**
     * @param startLocationId the startLocationId to set
     */
    public void setStartLocationId(int startLocationId) {
        this.startLocationId = startLocationId;
    }

    /**
     * @return the endLocationId
     */
    public int getEndLocationId() {
        return endLocationId;
    }

    /**
     * @param endLocationId the endLocationId to set
     */
    public void setEndLocationId(int endLocationId) {
        this.endLocationId = endLocationId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the locationList
     */
    public List<LocationDetails> getLocationList() {
        return locationList;
    }

    /**
     * @param locationList the locationList to set
     */
    public void setLocationList(List<LocationDetails> locationList) {
        this.locationList = locationList;
    }
}
