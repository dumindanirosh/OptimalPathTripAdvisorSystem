/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.dao;

import com.optimalpath.duminda.util.HotLocationDetails;
import com.optimalpath.duminda.util.LocationDetails;
import java.util.List;


/**
 *
 * @author Duminda
 */

public interface HotLocationDAO {

    public void createHotLocation(HotLocationDetails hotLocationDetails);

    public List<LocationDetails> getAllHotLocations();

    public List<LocationDetails> viewHotLocationDetails(int locationId);

   // public void removeHotLocation(int hotLocationID);

    
    
}
