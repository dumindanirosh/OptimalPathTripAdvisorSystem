/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.optimalroutefinder;

import java.util.Date;


public class Scheduler {

    private String startLocation;
    private String endLocation;
    private String location1;
    private String location2;
    private double travelDuration;
    private double visitingDuration;
    private Date startDate;
    private Date endDate;
    private Date startTime;
    private Date endTime;
    private Date arrivingTime ;
    private Date vistingTime; 
    private double locationDistance;

    public Scheduler(String location1, String location2, double distance,double travelDuration,
            double visitingDuration,Date arrivingTime,Date visitingTime) {
        this.location1 = location1;
        this.location2 = location2;
        this.locationDistance = distance;
        this.travelDuration = travelDuration;
        this.visitingDuration = visitingDuration;
        this.arrivingTime = arrivingTime;
        this.vistingTime = visitingTime;
       }

    /**
     * @return the startLocation
     */
    public String getStartLocation() {
        return startLocation;
    }

    /**
     * @param startLocation the startLocation to set
     */
    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    /**
     * @return the endLocation
     */
    public String getEndLocation() {
        return endLocation;
    }

    /**
     * @param endLocation the endLocation to set
     */
    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    /**
     * @return the location1
     */
    public String getLocation1() {
        return location1;
    }

    /**
     * @param location1 the location1 to set
     */
    public void setLocation1(String location1) {
        this.location1 = location1;
    }

    /**
     * @return the location2
     */
    public String getLocation2() {
        return location2;
    }

    /**
     * @param location2 the location2 to set
     */
    public void setLocation2(String location2) {
        this.location2 = location2;
    }

    /**
     * @return the travelDuration
     */
    public double getTravelDuration() {
        return travelDuration;
    }

    /**
     * @param travelDuration the travelDuration to set
     */
    public void setTravelDuration(double travelDuration) {
        this.travelDuration = travelDuration;
    }

    /**
     * @return the visitingDuration
     */
    public double getVisitingDuration() {
        return visitingDuration;
    }

    /**
     * @param visitingDuration the visitingDuration to set
     */
    public void setVisitingDuration(double visitingDuration) {
        this.visitingDuration = visitingDuration;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the arrivingTime
     */
    public Date getArrivingTime() {
        return arrivingTime;
    }

    /**
     * @param arrivingTime the arrivingTime to set
     */
    public void setArrivingTime(Date arrivingTime) {
        this.arrivingTime = arrivingTime;
    }

    /**
     * @return the vistingTime
     */
    public Date getVistingTime() {
        return vistingTime;
    }

    /**
     * @param vistingTime the vistingTime to set
     */
    public void setVistingTime(Date vistingTime) {
        this.vistingTime = vistingTime;
    }

    /**
     * @return the locationDistance
     */
    public double getLocationDistance() {
        return locationDistance;
    }

    /**
     * @param locationDistance the locationDistance to set
     */
    public void setLocationDistance(double locationDistance) {
        this.locationDistance = locationDistance;
    }
}