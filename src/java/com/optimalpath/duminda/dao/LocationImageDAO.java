/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.dao;

import com.optimalpath.duminda.util.LocationImageDetails;
import java.util.List;


/**
 *
 * @author Duminda
 */

public interface LocationImageDAO {

    public LocationImageDetails createLocationImage(LocationImageDetails details);

    public List<LocationImageDetails> getAllImagesForLocation(int locationId);

       
}
