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
public class ReviewDetails implements Serializable {

    private int locationReviewId;
    private String comment;
    private int locationId;
    private String username;

    public ReviewDetails(int locationReviewId, String comment, int locationId, String username) {
        this.locationReviewId = locationReviewId;
        this.comment = comment;
        this.locationId = locationId;
        this.username = username;
    }

    public ReviewDetails(String comment, int locationId, String username) {
        this.comment = comment;
        this.username = username;
        this.locationId = locationId;
    }

    /**
     * @return the locationReviewId
     */
    public int getLocationReviewId() {
        return locationReviewId;
    }

    /**
     * @param locationReviewId the locationReviewId to set
     */
    public void setLocationReviewId(int locationReviewId) {
        this.locationReviewId = locationReviewId;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
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
