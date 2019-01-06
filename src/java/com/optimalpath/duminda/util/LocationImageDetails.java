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
public class LocationImageDetails implements Serializable {
    
    
    private int locationImageId;   
    private String locationImageName;
    private String locationImageUrl;
    private int locationId;

    public LocationImageDetails(int locationId) {
      
        this.locationId = locationId;
    }
    
    

    public LocationImageDetails(String locationImageName, String locationImageUrl) {
        this.locationImageName = locationImageName;
        this.locationImageUrl = locationImageUrl;
    }
    
    public LocationImageDetails(String locationImageName, String locationImageUrl, int locationId) {
        this.locationImageName = locationImageName;
        this.locationImageUrl = locationImageUrl;
        this.locationId = locationId;
    }
    

    /**
     * @return the locationImageId
     */
    public int getLocationImageId() {
        return locationImageId;
    }

    /**
     * @param locationImageId the locationImageId to set
     */
    public void setLocationImageId(int locationImageId) {
        this.locationImageId = locationImageId;
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

    /**
     * @return the locationImageName
     */
    public String getLocationImageName() {
        return locationImageName;
    }

    /**
     * @param locationImageName the locationImageName to set
     */
    public void setLocationImageName(String locationImageName) {
        this.locationImageName = locationImageName;
    }

    /**
     * @return the locationImageUrl
     */
    public String getLocationImageUrl() {
        return locationImageUrl;
    }

    /**
     * @param locationImageUrl the locationImageUrl to set
     */
    public void setLocationImageUrl(String locationImageUrl) {
        this.locationImageUrl = locationImageUrl;
    }

    
}
