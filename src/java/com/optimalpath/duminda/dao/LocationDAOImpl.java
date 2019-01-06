/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.dao;

import com.optimalpath.duminda.model.GoogleMapLocation;
import com.optimalpath.duminda.model.Location;
import com.optimalpath.duminda.model.LocationCategory;
import com.optimalpath.duminda.util.BackendConstants;
import com.optimalpath.duminda.util.EJBConstants;
import com.optimalpath.duminda.util.GoogleMapLocationDetails;
import com.optimalpath.duminda.util.LocationDetails;
import com.optimalpath.duminda.util.LoggerFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 * The LocationDAOImpl is the direct implementation of the Locationfacade remote
 interface. It overrides all the methods of Locationfacade interface to deal
 * with the googleMapLocation table.
 *
 * @author Duminda
 */
public class LocationDAOImpl implements LocationDAO {

    private StringBuffer SYSTEM_LOGS;

    private EntityManager em;
    private EntityManagerFactory entityManagerFactory;

    public LocationDAOImpl() {

       entityManagerFactory = Persistence.createEntityManagerFactory(BackendConstants.PERSISTENT_UNIT_NAME);

        em = entityManagerFactory.createEntityManager();

        SYSTEM_LOGS = new StringBuffer(" ");

    }

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private List<LocationDetails> copyLocationsToDetails(List<Location> locationList) {
        List<LocationDetails> locationDetailList = new ArrayList<LocationDetails>();
        Iterator<Location> i = locationList.iterator();
        while (i.hasNext()) {
            Location location = (Location) i.next();

            LocationDetails details = new LocationDetails(location.getLocationId(), location.getLocationName(),
                    location.getLocationDescription(),
                    location.getLocationCategoryId().getLocationCategoryId(), location.getLocationCity(),
                    location.getLocationDistrict(), location.getMainImageName(), location.getMainImageUrl(),
                    location.getApproval(), location.getApprovedBy(),
                    location.getEnteredBy(), location.getEnteredDate(), location.getVisitingDuration());
            details.setLocationCategoryType(location.getLocationCategoryId().getLocationCategoryType());
            locationDetailList.add(details);
        }
        return locationDetailList;
    }

    private List<LocationDetails> copyGoogleMapLocationToDetails(List< GoogleMapLocation> hotLocationList) {
        List<LocationDetails> locationDetailsList = new ArrayList<LocationDetails>();
        Iterator<GoogleMapLocation> i = hotLocationList.iterator();
        while (i.hasNext()) {
            GoogleMapLocation hotLocation = (GoogleMapLocation) i.next();
            Location location = hotLocation.getLocationId();

            LocationDetails details = new LocationDetails(location.getLocationId(), location.getLocationName(),
                    location.getLocationDescription(),
                    location.getLocationCategoryId().getLocationCategoryId(), location.getLocationCity(),
                    location.getLocationDistrict(), location.getMainImageName(), location.getMainImageUrl(),
                    location.getApproval(), location.getApprovedBy(),
                    location.getEnteredBy(), location.getEnteredDate(), location.getVisitingDuration());
            locationDetailsList.add(details);

        }
        return locationDetailsList;
    }

    private List<GoogleMapLocationDetails> copyGoogleMapLocationsToDetails(List<GoogleMapLocation> googleMapLocationList) {
        List<GoogleMapLocationDetails> googleMapLocationDetailList = new ArrayList<GoogleMapLocationDetails>();
        Iterator<GoogleMapLocation> i = googleMapLocationList.iterator();
        while (i.hasNext()) {
            GoogleMapLocation googleMapLocation = (GoogleMapLocation) i.next();
            GoogleMapLocationDetails details = new GoogleMapLocationDetails(googleMapLocation.getGoogleMapLocationId(),
                    googleMapLocation.getGoogleMapLocationName(),
                    googleMapLocation.getLatitude(), googleMapLocation.getLongitude(),
                    googleMapLocation.getLocationId().getLocationId());
            googleMapLocationDetailList.add(details);
        }
        return googleMapLocationDetailList;
    }

    private LocationDetails copyLocationsToDetails(Location location) {
        LocationDetails details = new LocationDetails(location.getLocationId(), location.getLocationName(),
                location.getLocationDescription(), location.getLocationCategoryId().getLocationCategoryId(),
                location.getLocationCity(), location.getLocationDistrict(), location.getMainImageName(),
                location.getMainImageUrl(), location.getApproval(), location.getApprovedBy(),
                location.getEnteredBy(), location.getEnteredDate(), location.getVisitingDuration());
        return details;
    }

    private GoogleMapLocationDetails copyGoogleMapLocationsToDetails(GoogleMapLocation googleMapLocation) {
        GoogleMapLocationDetails details = new GoogleMapLocationDetails(googleMapLocation.getGoogleMapLocationId(),
                googleMapLocation.getGoogleMapLocationName(), googleMapLocation.getLatitude(),
                googleMapLocation.getLongitude(), googleMapLocation.getLocationId().getLocationId());
        return details;
    }

    @Override
    public LocationDetails checkLocationNameExisting(String locationName) {
        Location location = null;
        try {
            location = (Location) em.createNamedQuery("Location.findByLocationName")
                    .setParameter("locationName", locationName).getSingleResult();
            return copyLocationsToDetails(location);
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public GoogleMapLocationDetails checkGoogleMapLocationNameExisting(String googleMapLocationName) {
        GoogleMapLocation googleMapLocation = null;
        try {
            googleMapLocation = (GoogleMapLocation) em.createNamedQuery("GoogleMapLocation.findByGoogleMapLocationName")
                    .setParameter("googleMapLocationName", googleMapLocationName).getSingleResult();
            return copyGoogleMapLocationsToDetails(googleMapLocation);
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public LocationDetails createLocation(LocationDetails details) {
        try {
            SYSTEM_LOGS.append("   public boolean createLocation(LocationDetails details) :");
            if (em != null) {
                Location location = new Location(details.getLocationName(),
                        details.getLocationDescription(),
                        details.getLocationCity(), details.getLocationDistrict(), details.getMainImageName(),
                        details.getMainImageUrl(), details.isApproval(), details.getApprovedBy(),
                        details.getEnteredBy(), details.getEnteredDate(), details.getVisitingDuration());

                LocationDetails availabilityStatus = checkLocationNameExisting(details.getLocationName());
                if (availabilityStatus == null) {
                    em.getTransaction().begin();
                    LocationCategory locationCategory = em.find(LocationCategory.class, details.getLocationCategoryId());
                    location.setLocationCategoryId(locationCategory);
                    em.persist(location);
                    em.getTransaction().commit();
                    details.setLocationId(location.getLocationId());
                    LoggerFile.logFatalMessage(SYSTEM_LOGS.append(location.getLocationName() + " : " + EJBConstants.SUCCESS));
                    return details;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            LoggerFile.logFatalMessage(SYSTEM_LOGS.append(details.getLocationName() + " : " + ex.toString()));
            throw ex;
        } finally {
            SYSTEM_LOGS.delete(0, SYSTEM_LOGS.length() - 1);

        }
    }

    @Override
    public GoogleMapLocationDetails createGoogleLocation(GoogleMapLocationDetails details) {
        try {
            if (em != null) {
                GoogleMapLocation googleMapLocation = new GoogleMapLocation(details.getGoogleMapLocationName(),
                        details.getLatitude(), details.getLongitude());
                GoogleMapLocationDetails availabilityStatus
                        = checkGoogleMapLocationNameExisting(details.getGoogleMapLocationName());
                if (availabilityStatus == null) {
                    em.getTransaction().begin();
                    Location location = em.find(Location.class, details.getLocationId());
                    googleMapLocation.setLocationId(location);
                    em.persist(googleMapLocation);
                    em.getTransaction().commit();
                    details.setGoogleMapLocationId(googleMapLocation.getGoogleMapLocationId());
                    return details;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public LocationDetails createLocationImage(LocationDetails details) {
        try {
            em.getTransaction().begin();
            Location location = em.find(Location.class, details.getLocationId());
            location.setMainImageName(details.getMainImageName());
            location.setMainImageUrl(details.getMainImageUrl());
            em.merge(location);
            em.getTransaction().commit();
            return details;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void editLocation(LocationDetails details) {
        try {
            em.getTransaction().begin();
            Location location = em.find(Location.class, details.getLocationId());
            location.setLocationName(details.getLocationName());
            location.setLocationDescription(details.getLocationDescription());
            location.setLocationCity(details.getLocationCity());
            location.setLocationDistrict(details.getLocationDistrict());
            location.setEnteredBy(details.getEnteredBy());
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void removeLocation(int locationID) {
        try {
            em.getTransaction().begin();
            Location location = em.find(Location.class, locationID);
            em.remove(location);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void removeGoogleMapLocation(int googleMapLocationID) {
        try {
            em.getTransaction().begin();
            GoogleMapLocation googleMapLocation = em.find(GoogleMapLocation.class, googleMapLocationID);
            em.remove(googleMapLocation);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public LocationDetails getLocation(int locationID) {

        LocationDetails details = null;
        try {
            Location location = em.find(Location.class, locationID);
            details = new LocationDetails(
                    location.getLocationId(), location.getLocationName(), location.getLocationDescription(),
                    location.getLocationCategoryId().getLocationCategoryId(), location.getLocationCity(),
                    location.getLocationDistrict(), location.getMainImageName(), location.getMainImageUrl(),
                    location.getApproval(), location.getApprovedBy(),
                    location.getEnteredBy(), location.getEnteredDate(), location.getVisitingDuration());
            details.setLocationCategoryType(location.getLocationCategoryId().getLocationCategoryType());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return details;
    }

    @Override
    public GoogleMapLocationDetails getGoogleMapLocation(int googleMapLocationID) {

        GoogleMapLocationDetails details = null;
        try {
            GoogleMapLocation location = em.find(GoogleMapLocation.class, googleMapLocationID);
            details = new GoogleMapLocationDetails(location.getGoogleMapLocationId(),
                    location.getGoogleMapLocationName(), location.getLatitude(), location.getLongitude(),
                    location.getLocationId().getLocationId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return details;
    }

    @Override
    public List<LocationDetails> getAllLocations() {
        List<Location> locationList = null;
        try {
            locationList = (List<Location>) em.createNamedQuery("Location.findAll").getResultList();

            return copyLocationsToDetails(locationList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> getAllLocationsCities() {

        try {
            return em.createNamedQuery("Location.listUniqueNames", String.class).getResultList();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<GoogleMapLocationDetails> getAllGoogleMapLocations() {
        List<GoogleMapLocation> googleMapLocationList = null;
        try {
            googleMapLocationList = (List<GoogleMapLocation>) em.createNamedQuery("GoogleMapLocation.findAll").getResultList();
            return copyGoogleMapLocationsToDetails(googleMapLocationList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<LocationDetails> getAllLocationsForCategory(int locationCategoryId) {
        List<LocationDetails> locationDetailsList = null;
        try {
            LocationCategory locationCategory = em.find(LocationCategory.class, locationCategoryId);
            locationDetailsList = this.copyLocationsToDetails(
                    (List<Location>) locationCategory.getLocationList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return locationDetailsList;
    }

    @Override
    public List<LocationDetails> getLocationsByCity(String locationCity) {
        List<Location> location = null;
        try {
            location = (List<Location>) em.createNamedQuery(
                    "Location.findByLocationCity")
                    .setParameter("locationCity", locationCity)
                    .getResultList();
            return copyLocationsToDetails(location);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<LocationDetails> viewGoogleMapLocationDetails(int locationId) {
        List<GoogleMapLocation> googleMapLocation = null;
        try {
            googleMapLocation = (List<GoogleMapLocation>) em.createNamedQuery(
                    "GoogleMapLocation.findByLocationId")
                    .setParameter("locationId", locationId)
                    .getResultList();
            return copyGoogleMapLocationToDetails(googleMapLocation);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<LocationDetails> getGoogleMapLocations() {
        List<GoogleMapLocation> googleMapLocationList = null;
        try {
            googleMapLocationList = (List<GoogleMapLocation>) em.createNamedQuery("GoogleMapLocation.findAll").getResultList();
            return copyGoogleMapLocationToDetails(googleMapLocationList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public GoogleMapLocationDetails viewGoogleMapLocationForLocation(int locationId) {
        GoogleMapLocation googleMapLocation = null;
        try {
            Query query = em.createNamedQuery("GoogleMapLocation.findAllGoogleMapLocationByLocationID");
            query.setParameter("locationId", new Location(locationId));
            googleMapLocation = (GoogleMapLocation) query.getSingleResult();
            return copyGoogleMapLocationsToDetails(googleMapLocation);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
