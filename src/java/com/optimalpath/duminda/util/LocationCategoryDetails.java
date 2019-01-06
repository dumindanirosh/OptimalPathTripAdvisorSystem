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
public class LocationCategoryDetails implements Serializable {
    
   private Integer locationCategoryId;
   private String locationCategoryType;

    public LocationCategoryDetails(Integer locationCategoryId, String locationCategoryType) {
        this.locationCategoryId = locationCategoryId;
        this.locationCategoryType = locationCategoryType;
    }

    public LocationCategoryDetails(String locationCategoryType) {
        this.locationCategoryType = locationCategoryType;
    }

    
    /**
     * @return the locationCategoryId
     */
    public Integer getLocationCategoryId() {
        return locationCategoryId;
    }

    /**
     * @param locationCategoryId the locationCategoryId to set
     */
    public void setLocationCategoryId(Integer locationCategoryId) {
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
}
