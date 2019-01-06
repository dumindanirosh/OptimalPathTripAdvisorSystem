/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.util;

/**
 *
 * @author Duminda
 */
public class MatrixDetails {

    private int matrixId;
    private double distance;
    private double time;
    private int locationId1;
    private int locationId2;

    public MatrixDetails(int matrixId, double distance, double time) {
        this.matrixId = matrixId;
        this.distance = distance;
        this.time = time;
    }

    public MatrixDetails(double distance, double time, int locationId, int locationId2) {
        this.distance = distance;
        this.time = time;
        this.locationId1 = locationId;
        this.locationId2 = locationId2;
    }
    
    

    public MatrixDetails(int matrixId, double distance, double time, int locationId, int locationId2) {
        this.matrixId = matrixId;
        this.distance = distance;
        this.time = time;
        this.locationId1 = locationId;
        this.locationId2 = locationId2;
    }

    
    
    
    /**
     * @return the matrixId
     */
    public int getMatrixId() {
        return matrixId;
    }

    /**
     * @param matrixId the matrixId to set
     */
    public void setMatrixId(int matrixId) {
        this.matrixId = matrixId;
    }

    /**
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * @return the time
     */
    public double getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(double time) {
        this.time = time;
    }

    /**
     * @return the locationId1
     */
    public int getLocationId1() {
        return locationId1;
    }

    /**
     * @param locationId1 the locationId1 to set
     */
    public void setLocationId1(int locationId) {
        this.locationId1 = locationId;
    }

    /**
     * @return the locationId2
     */
    public int getLocationId2() {
        return locationId2;
    }

    /**
     * @param locationId2 the locationId2 to set
     */
    public void setLocationId2(int locationId2) {
        this.locationId2 = locationId2;
    }
}
