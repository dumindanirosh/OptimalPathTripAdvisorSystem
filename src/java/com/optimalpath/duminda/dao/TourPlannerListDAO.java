/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.dao;

import com.optimalpath.duminda.util.LocationDetails;
import com.optimalpath.duminda.util.TourPlannerListDetails;
import java.util.List;


/**
 *
 * @author Duminda
 */

public interface TourPlannerListDAO {

    public TourPlannerListDetails createTourPlannerList(TourPlannerListDetails tourPlannerListDetails);

    public TourPlannerListDetails createTourPlannerLocationList(TourPlannerListDetails tourPlannerListDetails);

    public List<LocationDetails> addLocationToTourPlanner(int locationID, int tourPlannerListId);

    public List<TourPlannerListDetails> getTourPlannerListIDForUsername(String username);

    public List<LocationDetails> getAllLocationsForTourPlannerList(int tourPlannerListID);

    public boolean removeLocationFromTourPlanner(int tourPlannerListId, int locationID);

    public boolean removeTourPlannerList(int tourPlannerListId);

    public TourPlannerListDetails getDetailsForTourPlannerList(int tourPlannerListId);

    
    
}
