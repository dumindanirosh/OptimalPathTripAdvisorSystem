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
public class HotLocationDetails implements Serializable{

    private int hotLocationId;
    private int locationId;

    public HotLocationDetails(int locationId) {
        this.locationId = locationId;
    }

    public HotLocationDetails(int hotLocationId, int locationId) {
        this.hotLocationId = hotLocationId;
        this.locationId = locationId;
    }

    /**
     * @return the hotLocationId
     */
    public int getHotLocationId() {
        return hotLocationId;
    }

    /**
     * @param hotLocationId the hotLocationId to set
     */
    public void setHotLocationId(int hotLocationId) {
        this.hotLocationId = hotLocationId;
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

