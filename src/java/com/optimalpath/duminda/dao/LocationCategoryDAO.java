/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.dao;

import com.optimalpath.duminda.util.LocationCategoryDetails;
import java.util.List;


/**
 *
 * @author Duminda
 */

public interface LocationCategoryDAO {

    public void createLocationCategory(LocationCategoryDetails locationCategoryDetails);

    public void editLocationCategory(LocationCategoryDetails locationCategoryDetails);

    public boolean removeLocationCategory(int locationCategoryID);

    public List<LocationCategoryDetails> getAllLocationCategories();

    //public LocationCategoryDetails getLocationCategoryIdByLocationType(String type);

    public LocationCategoryDetails getLocationCategoryNameIdByLocationID(int locationCategoryId);
    
}
