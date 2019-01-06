/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.util;

import java.io.Serializable;

/**
 *
 * @author Duminda
 */
public class GoogleMapLocationDetails implements Serializable {
    
    private int googleMapLocationId;
    private String googleMapLocationName;
    private double latitude;
    private double longitude;
    private int locationId;

    public GoogleMapLocationDetails(String googleMapLocationName, double latitude, double longitude, int locationId) {
        this.googleMapLocationName = googleMapLocationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationId = locationId;
    }

    public GoogleMapLocationDetails(int googleMapLocationId, String googleMapLocationName, double latitude, double longitude, int locationId) {
        this.googleMapLocationId = googleMapLocationId;
        this.googleMapLocationName = googleMapLocationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationId = locationId;
    }
    
    
    
    
    /**
     * @return the googleMapLocationId
     */
    public int getGoogleMapLocationId() {
        return googleMapLocationId;
    }

    /**
     * @param googleMapLocationId the googleMapLocationId to set
     */
    public void setGoogleMapLocationId(int googleMapLocationId) {
        this.googleMapLocationId = googleMapLocationId;
    }

    /**
     * @return the googleMapLocationName
     */
    public String getGoogleMapLocationName() {
        return googleMapLocationName;
    }

    /**
     * @param googleMapLocationName the googleMapLocationName to set
     */
    public void setGoogleMapLocationName(String googleMapLocationName) {
        this.googleMapLocationName = googleMapLocationName;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the locationId
     */
    public int getLocationId() {
        return locationId;
    }

    /**
     * @param locationId the locationId to set
     */
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
    
}
