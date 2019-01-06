/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.dao;

import com.optimalpath.duminda.model.HotLocation;
import com.optimalpath.duminda.model.Location;
import com.optimalpath.duminda.util.BackendConstants;
import com.optimalpath.duminda.util.HotLocationDetails;
import com.optimalpath.duminda.util.LocationDetails;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Duminda
 */

public class HotLocationDAOImpl implements HotLocationDAO {

        private EntityManager em;
    private EntityManagerFactory entityManagerFactory;

    public HotLocationDAOImpl() {

         entityManagerFactory = Persistence.createEntityManagerFactory(BackendConstants.PERSISTENT_UNIT_NAME);

        em = entityManagerFactory.createEntityManager();

    }

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private List<LocationDetails> copyHotLocationsToDetails(List< HotLocation> hotLocationList) {
        List<LocationDetails> locationDetailsList = new ArrayList<LocationDetails>();
        Iterator<HotLocation> i = hotLocationList.iterator();
        while (i.hasNext()) {
            HotLocation hotLocation = (HotLocation) i.next();
            Location location = hotLocation.getLocationId();
            LocationDetails details = new LocationDetails(location.getLocationId(), location.getLocationName(),
                    location.getLocationDescription(),
                    location.getLocationCategoryId().getLocationCategoryId(), location.getLocationCity(),
                    location.getLocationDistrict(), location.getMainImageName(), location.getMainImageUrl(),
                    location.getApproval(), location.getApprovedBy(),
                    location.getEnteredBy(), location.getEnteredDate(),location.getVisitingDuration());
            locationDetailsList.add(details);
        }
        return locationDetailsList;
    }



    @Override
    public void createHotLocation(HotLocationDetails hotLocationDetails) {
        try {

            HotLocation hotLocation = new HotLocation();
            Location location = em.find(Location.class, hotLocationDetails.getLocationId());
            hotLocation.setLocationId(location);
            em.persist(hotLocation);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
      
    }

    @Override
    public List<LocationDetails> getAllHotLocations() {
        List<HotLocation> hotLocationList = null;
        try {
            hotLocationList = (List<HotLocation>) em.createNamedQuery("HotLocation.findAll").getResultList();
            return copyHotLocationsToDetails(hotLocationList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<LocationDetails> viewHotLocationDetails(int locationId) {
        List<HotLocation> hotLocation = null;
        try {
            hotLocation = (List<HotLocation>) em.createNamedQuery(
                    "HotLocation.findLocationByHotLocation")
                    .setParameter("locationId", new Location(locationId))
                    .getResultList();
            return copyHotLocationsToDetails(hotLocation);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


}