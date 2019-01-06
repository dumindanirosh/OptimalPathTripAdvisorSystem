/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.dao;

import com.optimalpath.duminda.util.GoogleMapLocationDetails;
import com.optimalpath.duminda.util.LocationCategoryDetails;
import com.optimalpath.duminda.util.LocationDetails;
import java.util.List;


/**
 *
 * @author Duminda
 */

public interface LocationDAO {
// Location Facade
    public LocationDetails checkLocationNameExisting(String locationName);


    public GoogleMapLocationDetails checkGoogleMapLocationNameExisting(String googleMapLocationName);

    public LocationDetails createLocation(LocationDetails details);

    public GoogleMapLocationDetails createGoogleLocation(GoogleMapLocationDetails details);

    public LocationDetails createLocationImage(LocationDetails details);

    public void editLocation(LocationDetails details);

    public void removeLocation(int locationID);

    public void removeGoogleMapLocation(int googleMapLocationID);

    public LocationDetails getLocation(int locationID);

    public GoogleMapLocationDetails getGoogleMapLocation(int googleMapLocationID);

    //public LocationDetails getLocationName(String locationName);

    public List<LocationDetails> getAllLocations();

    public List<GoogleMapLocationDetails> getAllGoogleMapLocations();

    public List<LocationDetails> getAllLocationsForCategory(int locationCategoryId);

    public GoogleMapLocationDetails viewGoogleMapLocationForLocation(int locationId);

    public List<LocationDetails> getLocationsByCity(String locationCity);

//    public LocationDetails getLocationsByName(String locationName);

    public List<String> getAllLocationsCities();

    public List<LocationDetails> getGoogleMapLocations();

    //public List<LocationDetails> viewGoogleMapLocationDetails(String locationCity);

    public List<LocationDetails> viewGoogleMapLocationDetails(int locationId);

}
