/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.dao;

import com.optimalpath.duminda.model.GoogleMapLocation;
import com.optimalpath.duminda.model.Location;
import com.optimalpath.duminda.model.TourPlannerList;
import com.optimalpath.duminda.model.User;
import com.optimalpath.duminda.util.BackendConstants;
import com.optimalpath.duminda.util.LocationDetails;
import com.optimalpath.duminda.util.TourPlannerListDetails;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Duminda
 */
public class TourPlannerListDAOImpl implements TourPlannerListDAO {

    private EntityManager em;
    private EntityManagerFactory entityManagerFactory;

    public TourPlannerListDAOImpl() {

        entityManagerFactory = Persistence.createEntityManagerFactory(BackendConstants.PERSISTENT_UNIT_NAME);

        em = entityManagerFactory.createEntityManager();

    }

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private List<TourPlannerListDetails> copyTourPlannerListToDetails(List<TourPlannerList> tourPlannerList) {
        List<TourPlannerListDetails> tourPlannerListDetailsList = new ArrayList<TourPlannerListDetails>();
        Iterator<TourPlannerList> i = tourPlannerList.iterator();
        while (i.hasNext()) {
            TourPlannerList tourPlanner = (TourPlannerList) i.next();
            TourPlannerListDetails tourPlannerListDetails = new TourPlannerListDetails(tourPlanner.getTourPlannerListId(),
                    tourPlanner.getCheckinDate(), tourPlanner.getCheckoutDate(), tourPlanner.getStartLocationId(),
                    tourPlanner.getEndLocationId(), tourPlanner.getUsername().getUsername());
            List<LocationDetails> locationDetailses = new ArrayList<LocationDetails>();
            Iterator<Location> it = tourPlanner.getLocationList().iterator();
            while (it.hasNext()) {
                Location location = it.next();
                LocationDetails details = new LocationDetails(location.getLocationId(), location.getLocationName(),
                        location.getLocationDescription(),
                        location.getLocationCategoryId().getLocationCategoryId(), location.getLocationCity(),
                        location.getLocationDistrict(), location.getMainImageName(), location.getMainImageUrl(),
                        location.getApproval(), location.getApprovedBy(),
                        location.getEnteredBy(), location.getEnteredDate(), location.getVisitingDuration());
                locationDetailses.add(details);
            }
            tourPlannerListDetails.setLocationList(locationDetailses);
            tourPlannerListDetailsList.add(tourPlannerListDetails);
        }
        return tourPlannerListDetailsList;
    }

    private List<LocationDetails> copyLocationsToDetailsForGoogleMap(List<Location> locationList) {
        List<LocationDetails> locationDetailList = new ArrayList<LocationDetails>();
        Iterator<Location> i = locationList.iterator();
        while (i.hasNext()) {
            Location location = (Location) i.next();
            List<GoogleMapLocation> detailses = (List<GoogleMapLocation>) location.getGoogleMapLocationList();
            GoogleMapLocation googleMapLocation = detailses.get(0);

            LocationDetails details = new LocationDetails(location.getLocationId(), location.getLocationName(),
                    location.getLocationDescription(),
                    location.getLocationCategoryId().getLocationCategoryId(), location.getLocationCity(),
                    location.getLocationDistrict(), location.getMainImageName(), location.getMainImageUrl(),
                    location.getApproval(), location.getApprovedBy(),
                    location.getEnteredBy(), location.getEnteredDate(), location.getVisitingDuration());
            details.setLocationCategoryType(location.getLocationCategoryId().getLocationCategoryType());
            details.setGoogleMapId(googleMapLocation.getGoogleMapLocationId());
            details.setGoogleMapName(googleMapLocation.getGoogleMapLocationName());

            locationDetailList.add(details);
        }
        return locationDetailList;
    }

    private List<LocationDetails> copyLocationsTooDetails(List<Location> locationList) {
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

    private TourPlannerListDetails copyTourPlannerListToDetails(TourPlannerList tourPlannerList) {
        TourPlannerListDetails details = new TourPlannerListDetails(tourPlannerList.getTourPlannerListId(), tourPlannerList.getCheckinDate(),
                tourPlannerList.getCheckoutDate(), tourPlannerList.getStartLocationId(),
                tourPlannerList.getEndLocationId(), tourPlannerList.getUsername().getUsername());
        return details;
    }

    @Override
    public TourPlannerListDetails createTourPlannerList(TourPlannerListDetails tourPlannerListDetails) {
        try {
            TourPlannerList tourPlannerList = new TourPlannerList(tourPlannerListDetails.getCheckinDate(),
                    tourPlannerListDetails.getCheckoutDate(),
                    tourPlannerListDetails.getStartLocationId(), tourPlannerListDetails.getEndLocationId());

            em.getTransaction().begin();
            User user = em.find(User.class, tourPlannerListDetails.getUsername());
            tourPlannerList.setUsername(user);
            em.persist(tourPlannerList);
            em.getTransaction().commit();
            tourPlannerListDetails.setTourPlannerListId(tourPlannerList.getTourPlannerListId());
            return tourPlannerListDetails;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public TourPlannerListDetails createTourPlannerLocationList(TourPlannerListDetails tourPlannerListDetails) {
        try {
            TourPlannerList tourPlannerList = new TourPlannerList(tourPlannerListDetails.getCheckinDate(),
                    tourPlannerListDetails.getCheckoutDate(),
                    tourPlannerListDetails.getStartLocationId(), tourPlannerListDetails.getEndLocationId());
            em.getTransaction().begin();
            User user = em.find(User.class, tourPlannerListDetails.getUsername());
            tourPlannerList.setUsername(user);
            em.persist(tourPlannerList);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<LocationDetails> addLocationToTourPlanner(int locationID, int tourPlannerListId) {

        try {
            Location location = em.find(Location.class, locationID);
            TourPlannerList tourPlannerList = em.find(TourPlannerList.class, tourPlannerListId);
            tourPlannerList.addLocation(location);
            List<Location> tourLocationList = (List<Location>) tourPlannerList.getLocationList();
            em.getTransaction().begin();
            em.merge(tourPlannerList);
            em.getTransaction().commit();
            List<LocationDetails> tourLocationDetailsList = (List<LocationDetails>) copyLocationsToDetailsForGoogleMap(tourLocationList);
            return tourLocationDetailsList;

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;

    }

    @Override
    public boolean removeLocationFromTourPlanner(int tourPlannerListId, int locationID) {
        try {

            Query query = em.createNamedQuery("TourPlannerList.findByTourPlannerListId");
            query.setParameter("tourPlannerListId", new Integer(tourPlannerListId));
            TourPlannerList tourPlannerListObj = (TourPlannerList) query.getSingleResult();
            List<Location> tourPlannerList = tourPlannerListObj.getLocationList();
            String locationId = Integer.toString(locationID);
            for (Location location : tourPlannerList) {
                String existLocId = Integer.toString(location.getLocationId());
                if (locationId.equals(existLocId)) {
                    tourPlannerList.remove(location);
                    break;
                }
            }
            em.getTransaction().begin();
            em.merge(tourPlannerListObj);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeTourPlannerList(int tourPlannerListId) {
        try {
            Query query = em.createNamedQuery("TourPlannerList.findByTourPlannerListId");
            query.setParameter("tourPlannerListId", new Integer(tourPlannerListId));
            TourPlannerList tourPlannerListObj = (TourPlannerList) query.getSingleResult();
            List<Location> tourPlannerList = tourPlannerListObj.getLocationList();
            tourPlannerList.clear();
            em.getTransaction().begin();
            em.remove(tourPlannerListObj);
            em.getTransaction().commit();
            return true;
        } catch (NoResultException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<LocationDetails> getAllLocationsForTourPlannerList(int id) {

        try {
            TourPlannerList tourPlannerList = em.find(TourPlannerList.class, id);
            List<Location> tourLocationList = (List<Location>) tourPlannerList.getLocationList();
            List<LocationDetails> tourLocationDetailsList = (List<LocationDetails>) copyLocationsToDetailsForGoogleMap(tourLocationList);
            return tourLocationDetailsList;
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;

        }
    }

    @Override
    public List<TourPlannerListDetails> getTourPlannerListIDForUsername(String username) {
        List<TourPlannerList> tourPlannerLists = null;
        try {
            Query query = em.createNamedQuery("TourPlannerList.findByusername");
            query.setParameter("username", new User(username));
            tourPlannerLists = (List<TourPlannerList>) query.getResultList();

            return copyTourPlannerListToDetails(tourPlannerLists);
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
            //throw new EJBException(ex);
        }
    }

    public TourPlannerListDetails getDetailsForTourPlannerList(int tourPlannerListId) {

        TourPlannerList tourPlannerLists = null;
        try {
            Query query = em.createNamedQuery("TourPlannerList.findByTourPlannerListId");
            query.setParameter("tourPlannerListId", tourPlannerListId);
            tourPlannerLists = (TourPlannerList) query.getSingleResult();

            return copyTourPlannerListToDetails(tourPlannerLists);
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
            //throw new EJBException(ex);
        }
    }
}
